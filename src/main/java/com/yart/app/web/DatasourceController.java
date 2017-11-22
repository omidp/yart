package com.yart.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yart.app.domain.DatasourceEntity;
import com.yart.app.service.AbstractService;
import com.yart.app.service.DatasourceService;
import com.yart.app.service.Restriction;
import com.yart.framework.query.ParamOperator;
import com.yart.framework.query.QParam;
import com.yart.framework.query.QueryMapper;
import com.yart.framework.web.AbstractQueryController;

/**
 *
 * @author Omid Pourhadi
 *
 */
@Controller
@RequestMapping("/member/datasources")
public class DatasourceController extends AbstractQueryController<DatasourceEntity>
{

    @Autowired
    DatasourceService datasourceService;

    public DatasourceController()
    {
        super("ds/dsList");
    }


    @GetMapping("/reset")
    public String reset()
    {
        return "redirect:/member/datasources";
    }

    @Override
    protected Filter createFilter()
    {
        return new DatasourceFilter();
    }

    @Override
    protected Restriction addRestriction(Filter filter)
    {
        return criteria -> {
            QueryMapper.criteriaFilter(filter, criteria);
        };
    }
    
    @Override
    public AbstractService<DatasourceEntity> getService()
    {
        return datasourceService;
    }

    public static class DatasourceFilter implements Filter
    {
        private String name;
        private Boolean active;

        @QParam(operator = ParamOperator.EQ, propertyName = "active")
        public Boolean getActive()
        {
            return active;
        }

        public void setActive(Boolean active)
        {
            this.active = active;
        }

        @QParam(operator = ParamOperator.LIKE, propertyName = "name")
        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

    }

   

}
