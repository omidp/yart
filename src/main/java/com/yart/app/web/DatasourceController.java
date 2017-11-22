package com.yart.app.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yart.app.domain.DatasourceEntity;
import com.yart.app.service.DatasourceService;
import com.yart.app.service.Restriction;
import com.yart.util.Pager;
import com.yart.util.ParamOperator;
import com.yart.util.QParam;
import com.yart.util.QueryMapper;

@Controller
@RequestMapping("/datasources")
public class DatasourceController
{

    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int BUTTONS_TO_SHOW = 5;
    private static final int[] PAGE_SIZES = { 5, 10, 20 };

    @Autowired
    DatasourceService datasourceService;


    @ModelAttribute("filter")
    public final DatasourceFilter createFilter()
    {
        DatasourceFilter filter = new DatasourceFilter();
        return filter;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        // binder.setRequiredFields(getRequiredFields());
    }

    public String[] getRequiredFields()
    {
        return new String[] { "" };
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam("pageSize") Optional<Integer> pageSize, @RequestParam("page") Optional<Integer> page, Model model,
            @ModelAttribute("filter") Filter filter, Sort sort)
    {
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.

        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Page<DatasourceEntity> list = datasourceService.load(new PageRequest(evalPage, evalPageSize), DatasourceEntity.class,
                addRestriction(filter), sort);

        Pager pager = new Pager(list.getTotalPages(), list.getNumber(), BUTTONS_TO_SHOW);

        model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pageSizes", PAGE_SIZES);
        model.addAttribute("pager", pager);
        model.addAttribute("result", list);
        model.addAttribute("filterParams", QueryMapper.parameterMap(filter));
        model.addAttribute("sortParams", QueryMapper.parameterFilterMap(sort));

        return "ds/dsList";
    }


    @GetMapping("/reset")
    public String reset()
    {
        return "redirect:/datasources";
    }

    protected Restriction addRestriction(Filter filter)
    {
        return criteria -> {
            QueryMapper.filterMap(filter, criteria);
        };
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
