package com.yart.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/datasources")
public class DatasourceController
{

    
    @RequestMapping(method=RequestMethod.GET)
    public String list()
    {
        return "ds/dsList";
    }
    
    
}
