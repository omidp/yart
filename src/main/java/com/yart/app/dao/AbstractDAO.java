package com.yart.app.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Omid Pourhadi
 *
 */
@NoRepositoryBean
public interface AbstractDAO<T> extends PagingAndSortingRepository<T, Long>, JpaSpecificationExecutor<T>
{

}