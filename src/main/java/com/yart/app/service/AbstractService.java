package com.yart.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.IdentifiableType;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.data.repository.support.PageableExecutionUtils.TotalSupplier;
import org.springframework.transaction.annotation.Transactional;

import com.yart.app.dao.AbstractDAO;
import com.yart.util.StringUtil;

/**
 * @author Omid Pourhadi
 *
 */
@Transactional
public abstract class AbstractService<E>
{

    @PersistenceContext(unitName = "entityManagerFactory")
    protected EntityManager entityManager;

    public abstract AbstractDAO<E> getDao();
    
    
    @Transactional(readOnly = true)
    public Page<E> load(Pageable pageable, Class<E> clz, Restriction restriction)
    {
        return load(pageable, clz, restriction, null);
    }

    @Transactional(readOnly = true)
    public Page<E> load(Pageable pageable, Class<E> clz, Restriction restriction, Sort sort)
    {
        // The JPA spec does not allow a alias to be given to a fetch join, so
        // we use hibernate seesion to ignore the spec
        List<E> result = new ArrayList<>();
        Session hibernateSession = (Session) getEntityManager().getDelegate();
        Criteria criteria = hibernateSession.createCriteria(clz);
        if (restriction != null)
            restriction.applyFilter(criteria);
        if (sort != null)
        {
            sort.forEach(s -> {                
                if(StringUtil.isNotEmpty(s.getProperty()))
                {
                    if (s.isAscending())
                        criteria.addOrder(org.hibernate.criterion.Order.asc(s.getProperty()));
                    else
                        criteria.addOrder(org.hibernate.criterion.Order.desc(s.getProperty()));
                }
            });
        }
        criteria.setFirstResult(pageable.getOffset());
        // set 0 for unlimited
        if (pageable.getPageSize() > 0)
            criteria.setMaxResults(pageable.getPageSize());
        result = criteria.list();
        return PageableExecutionUtils.getPage(result, pageable, new TotalSupplier() {

            @Override
            public long get() {
                return count(clz, restriction);
            }
        });
    }

   
    @Transactional(readOnly = true)
    public Long count(Class<E> clz, Restriction restriction)
    {
        Session hibernateSession = (Session) getEntityManager().getDelegate();
        Criteria criteria = hibernateSession.createCriteria(clz);
        if (restriction != null)
            restriction.applyFilter(criteria);
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }

    @Transactional
    public void insert(E entity)
    {
        beforeInsert(entity);
        getDao().save(entity);
        afterInsert(entity);
    }

    public void delete(Long id)
    {
        getDao().delete(id);
    }

    @Transactional
    public void update(E entity)
    {
        beforeUpdate(entity);
        getDao().save(entity);
        afterUpdate(entity);

    }

    protected void afterUpdate(E entity)
    {

    }

    protected void beforeUpdate(E entity)
    {

    }

    private void metaData(Class<E> domainClass)
    {
        Metamodel metamodel = getEntityManager().getMetamodel();
        ManagedType<E> type = metamodel.managedType(domainClass);
        if (!(type instanceof IdentifiableType))
        {
            throw new ServiceException("The given domain class does not contain an id attribute!");
        }
        IdentifiableType<E> source = (IdentifiableType<E>) type;
        if (source.hasSingleIdAttribute())
        {
            SingularAttribute<? super E, ?> idAttribute = source.getId(source.getIdType().getJavaType());
        }
        else
        {
            throw new ServiceException("unsupported operator");
        }
    }

    protected void afterInsert(E entity)
    {

    }

    protected void beforeInsert(E entity)
    {

    }

    @Transactional(readOnly = true)
    public E findById(Class<E> clz, Object id)
    {
        return getEntityManager().find(clz, id);
    }

    public Iterable<E> findAll()
    {
        return getDao().findAll();
    }

    public Iterable<E> findAll(Specification<E> spec)
    {
        return getDao().findAll(spec);
    }

    protected EntityManager getEntityManager()
    {
        return entityManager;
    }

}
