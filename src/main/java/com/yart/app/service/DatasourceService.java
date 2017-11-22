package com.yart.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yart.app.dao.AbstractDAO;
import com.yart.app.dao.DatasourceDao;
import com.yart.app.domain.DatasourceEntity;

/**
 *
 * @author Omid Pourhadi
 *
 */
@Service
public class DatasourceService extends AbstractService<DatasourceEntity>
{

    
    @Autowired
    DatasourceDao datasourceDao;
    
    @Override
    public AbstractDAO<DatasourceEntity> getDao()
    {
        return datasourceDao;
    }

}
