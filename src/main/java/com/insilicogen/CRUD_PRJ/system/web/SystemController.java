package com.insilicogen.CRUD_PRJ.system.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class SystemController {

    @GetMapping("/system/statistics")
    public String systemStatistics(){
        return "/system/statistics";
    }
}
