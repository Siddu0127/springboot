package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class FormController
{
    @GetMapping("sid")
    public String edu()
    {

        return "sid";
    }
    @GetMapping("/details")
    public String viewdetail(@RequestParam("cid") String cid, @RequestParam("cname") String cname, @RequestParam("cemail") String cemail, ModelMap modelMap)
    {
        modelMap.put("cid",cid);
        modelMap.put("cname",cname);
        modelMap.put("cemail",cemail);
        return  "view";
    }
}
