package com.kazachenko.textanalyzer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
class IndexController {

    @RequestMapping("/index")
    String index() {
        return "index";
    }
}