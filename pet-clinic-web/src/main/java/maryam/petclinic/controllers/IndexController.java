package maryam.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    //returns the index page for controller
    @RequestMapping({"", "/", "index", "index.html"})
    public String index(){
        return "index";
    }

}
