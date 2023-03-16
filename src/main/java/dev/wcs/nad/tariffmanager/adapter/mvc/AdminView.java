package dev.wcs.nad.tariffmanager.adapter.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.util.StringUtils;
import dev.wcs.nad.tariffmanager.identity.entity.User;
import dev.wcs.nad.tariffmanager.identity.user.UserRepository;
import dev.wcs.nad.tariffmanager.identity.user.UserService;

@Controller
@RequestMapping("/public/admin")
public class AdminView {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;


    @GetMapping("/")
    public String editview(Model model, @RequestParam(name = "role", required = false) String role) {
        Iterable<User> users = StringUtils.hasText(role) ? userRepository.findByUsernameContaining(role) : userRepository.findAll();


        model.addAttribute("users", users);
        return "admin";
    }

}
