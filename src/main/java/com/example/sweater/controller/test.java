package com.example.sweater.controller;

import com.example.sweater.service.WeatherParse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class test {

    @GetMapping("/test")
    public String testView(Model model) {
        Map<String, String> weatherMap = WeatherParse.getParseInfoWeather();

        String lol = "its works";
        model.addAttribute("lol", lol);
        System.out.println( weatherMap.get("night"));
       
        model.mergeAttributes(weatherMap);

        return "testView";
    }
}
