package com.campingmoa.dev.controller;

import com.campingmoa.dev.dto.MemberForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {
    @GetMapping("/login")
    public String login(){
        return "members/login";
    }

    @GetMapping("/join")
    public String join(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/join";
    }
}