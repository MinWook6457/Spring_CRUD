package com.insilicogen.CRUD_PRJ.system.web;

import com.insilicogen.CRUD_PRJ.user.repository.UserRepository;
import com.insilicogen.CRUD_PRJ.user.service.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller

public class SystemController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/system/statistics")
    public String systemStatistics(Model model){
        long maleCount = userRepository.countMaleUsers();
        long femaleCount = userRepository.countFemaleUsers();
        List<Integer> userAges = userRepository.getUsersAge();
        List<User> users = userRepository.findAllByOrderByCreatedAtDesc();


        int[] ageCounts = new int[7];
        for (Integer age : userAges) {
            if (age >= 0 && age <= 10) {
                ageCounts[0]++;
            } else if (age >= 11 && age <= 20) {
                ageCounts[1]++;
            } else if (age >= 21 && age <= 30) {
                ageCounts[2]++;
            } else if (age >= 31 && age <= 40) {
                ageCounts[3]++;
            } else if (age >= 41 && age <= 50) {
                ageCounts[4]++;
            } else if (age >= 51 && age <= 60) {
                ageCounts[5]++;
            } else {
                ageCounts[6]++;
            }
        }

        int[] createdCounts = new int[7];
        for (User user : users) {
            Integer year = user.getCreatedAt().getYear();
            System.out.println(year);
            if (year >= 1971 && year <= 1980) {
                createdCounts[0]++;
            } else if (year >= 1981 && year <= 1990) {
                createdCounts[1]++;
            } else if (year >= 1991 && year <= 2000) {
                createdCounts[2]++;
            } else if (year >= 2001 && year <= 2010) {
                createdCounts[3]++;
            } else if (year >= 2011 && year <= 2020) {
                createdCounts[4]++;
            } else if (year >= 2021 && year <= 2030) {
                createdCounts[5]++;
            } else {
                createdCounts[6]++;
            }
        }

        model.addAttribute("ageCounts", ageCounts);

        model.addAttribute("createdCounts", createdCounts);
        model.addAttribute("maleCount", maleCount);
        model.addAttribute("femaleCount", femaleCount);
        model.addAttribute("userAges", userAges);
        model.addAttribute("users", users);

        return "/system/statistics";
    }
}
