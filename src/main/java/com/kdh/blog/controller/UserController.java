package com.kdh.blog.controller;

import com.kdh.blog.model.User;
import com.kdh.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("user", new User());
        return "user/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute User user) {
        User saveUser = userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user){
        User findUser = userRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        httpSession.setAttribute("saveUser", findUser);
        return "redirect:/";
    }


}
