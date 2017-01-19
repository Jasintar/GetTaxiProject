package ru.innopolis.uni.course3.taxiapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.uni.course3.taxiapp.POJO.Car;
import ru.innopolis.uni.course3.taxiapp.POJO.Order;
import ru.innopolis.uni.course3.taxiapp.POJO.User;
import ru.innopolis.uni.course3.taxiapp.services.OrderService;
import ru.innopolis.uni.course3.taxiapp.services.UserService;

import javax.servlet.http.HttpSession;

/**
 * Created on 09.01.2017.
 *
 * @authot Julia Savicheva
 */
@Controller
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private UserService userService;
    private OrderService orderService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userServiceImpl, OrderService orderSrviceImpl, PasswordEncoder passwordEncoder) {
        this.userService = userServiceImpl;
        this.orderService = orderSrviceImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/index")
    public String home() {
        LOG.info("hello. Home method");
        boolean isAuthenticated = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
        String returnPath = (isAuthenticated) ? "redirect:/main" : "index";
        return returnPath;
    }

    @RequestMapping("/main")
    public String mainPageApp() {
        LOG.info("hello. Main app method");
        String redirectPath = getPathToRedirectSecurity();
        return "redirect:".concat(redirectPath);
    }

    @RequestMapping(value = "/registration")
    public String registration() {
        LOG.info("registration method called");
        return "registration";
    }

    @RequestMapping(value = "user/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("User") User user, @ModelAttribute("Car") Car car) {
        LOG.info("add user method called");
        userService.registerUser(user, car);
        return "redirect:/index";
    }

    @RequestMapping(value = "/myLogout")
    public String logout(HttpSession session) {
        LOG.info("user is trying to logout");
        session.invalidate();

        return "redirect:/index";
    }

    private String getPathToRedirectSecurity() {
        String path = "/index";
        String username = ((org.springframework.security.core.userdetails.User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user;

        user = userService.getUserByUsername(username);

        if (user != null) {
            path = "/myOrder";
            Order order = orderService.getCurrentUserOrder(user);
            if (order == null) {
                if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(
                        new SimpleGrantedAuthority("C"))) {
                    path = "/createOrder";
                } else if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(
                        new SimpleGrantedAuthority("D"))) {
                    path = "/newOrders";
                }
            }
        }
        return path;
    }
}
