package com.yart.app.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yart.app.domain.DatasourceEntity;

@Controller
@RequestMapping("/datasourceEdit")
public class DatasourceHomeController
{

    @RequestMapping(method = RequestMethod.GET)
    public String get()
    {
        return "ds/dsEdit";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String post(@Valid @ModelAttribute("instance") DatasourceEntity entity, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "ds/dsEdit";
        }
        System.out.println(entity.getName());
        return "redirect:/member/datasources";
    }

    @ModelAttribute("instance")
    public final DatasourceEntity instance()
    {
        return new DatasourceEntity();
    }

}
