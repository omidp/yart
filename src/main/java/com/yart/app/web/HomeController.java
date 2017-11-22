package com.yart.app.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Omid Pourhadi
 *
 */
@Controller
public class HomeController
{

    @RequestMapping("/")
    public String index()
    {
        return "login";
    }

    @RequestMapping("/home")
    public String home(Map<String, Object> model)
    {
        model.put("message", "Hello");
        return "home";
    }

    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }

}
