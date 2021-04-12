package com.pcode.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*视图跳转控制器*/

@Controller
public class URLController {
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/forget")
    public String forget(){
        return "forget";
    }
    @RequestMapping("/workspace")
    public String workspace(){
        return "workspace";
    }
    @RequestMapping("/admin")
    public String admin(){return "admin";}
    @RequestMapping("/agile")
    public String agile(){return "Agile";}
    @RequestMapping("/detail/{browesId}")
    public String detail(@PathVariable String browesId, Model model){
        model.addAttribute("browseId",browesId);
        return "detail";
    }
}
