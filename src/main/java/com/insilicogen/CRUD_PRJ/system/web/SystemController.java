package com.insilicogen.CRUD_PRJ.system.web;

import com.insilicogen.CRUD_PRJ.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class SystemController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/system/statistics")
    public String systemStatistics(Model model){
        long maleCount = userRepository.countMaleUsers();
        long femaleCount = userRepository.countFemaleUsers();

        model.addAttribute("maleCount", maleCount);
        model.addAttribute("femaleCount", femaleCount);

        return "/system/statistics";
    }
}
