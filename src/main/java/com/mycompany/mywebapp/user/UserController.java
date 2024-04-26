package com.mycompany.mywebapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    public UserService userService;

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> listUser = userService.Listll();
        model.addAttribute("listUsers", listUser);
        return "users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user){
        userService.save(user);
        return "redirect:/users";
    }
    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        try {
            User user = userService.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
            return "user_form";
        } catch (userNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", "The user has been saved sucessfully");
            return "redirect:/users";
        }

    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        try {
          userService.delete(id);
          redirectAttributes.addFlashAttribute("message", "The user ID: "+ id + "been deleted successfully");
        } catch (userNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }


}
