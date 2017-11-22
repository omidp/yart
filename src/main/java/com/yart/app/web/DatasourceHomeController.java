package com.yart.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yart.app.domain.DatasourceEntity;
import com.yart.app.domain.enums.DatasourceType;
import com.yart.app.service.AbstractService;
import com.yart.app.service.DatasourceService;
import com.yart.framework.web.AbstractHomeController;

@Controller
@RequestMapping("/member/datasourceEdit")
public class DatasourceHomeController extends AbstractHomeController<DatasourceEntity>
{

    public DatasourceHomeController()
    {
        super("ds/dsEdit", "redirect:/member/datasources");
    }

    @Autowired
    @Qualifier("datasourceValidator")
    Validator validator;

    @Autowired
    DatasourceService datasourceService;

    @Override
    protected void init(WebDataBinder binder)
    {
        binder.setValidator(validator);
    }

    @Override
    protected String editParamId()
    {
        return "dsId";
    }

    @Override
    public AbstractService<DatasourceEntity> getService()
    {
        return datasourceService;
    }
    
    @ModelAttribute("dsTypes")
    public DatasourceType[] dsType()
    {
        return DatasourceType.values();
    }

}
