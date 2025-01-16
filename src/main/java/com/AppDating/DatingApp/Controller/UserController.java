package com.AppDating.DatingApp.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.AppDating.DatingApp.Entity.User;
import com.AppDating.DatingApp.Repository.UserRepository;
import com.AppDating.DatingApp.Service.DatingAppService;

@Controller
public class UserController {
    private final UserRepository userRepository;
    private final DatingAppService datingAppService;

    public UserController(UserRepository userRepository, DatingAppService datingAppService) {
        this.userRepository = userRepository;
        this.datingAppService = datingAppService;
    }

    // Display all users
    @GetMapping("/api/users/all")
    public String getAllUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    // Add a new user
    @PostMapping("/api/users/add")
    public String addUser(@RequestParam String name,
                          @RequestParam String gender,
                          @RequestParam int age,
                          @RequestParam String interests) {
        List<String> interestsList = Arrays.asList(interests.split(","));
        User user = new User(name, gender, age, interestsList);
        userRepository.save(user);
        return "redirect:/api/users/all";
    }

    
 // Get matches for a specific user
    @GetMapping("/api/users/{id}/matches")
    public String getMatches(@PathVariable Long id,
                             @RequestParam(defaultValue = "2") int topN,
                             Model model) {
        List<User> matches = datingAppService.findMatches(id, topN);
        model.addAttribute("matches", matches);
        return "matches";
    }
}