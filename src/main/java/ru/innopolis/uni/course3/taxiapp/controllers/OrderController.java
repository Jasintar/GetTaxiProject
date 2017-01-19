package ru.innopolis.uni.course3.taxiapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.uni.course3.taxiapp.POJO.Order;
import ru.innopolis.uni.course3.taxiapp.POJO.User;
import ru.innopolis.uni.course3.taxiapp.services.OrderService;
import ru.innopolis.uni.course3.taxiapp.services.UserService;

import java.util.List;

/**
 * Created on 10.01.2017.
 *
 * @authot Julia Savicheva
 */

@Controller
public class OrderController {
    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    private OrderService orderService;
    private UserService userService;

    @Autowired
    public OrderController(OrderService orderServiceImpl, UserService userServiceImpl) {
        this.orderService = orderServiceImpl;
        this.userService = userServiceImpl;
    }

    @RequestMapping("/myOrder")
    public String showUserCurrentOrder(Model model) {
        LOG.info("my order page");
        String username = ((org.springframework.security.core.userdetails.User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
//        User user = (User) session.getAttribute("user");
        User user = userService.getUserByUsername(username);

        String view;
        if ("D".equals(user.getUsertype())) {
            view = "view/driver/currentOrder";
        } else {
            view = "view/client/currentOrder";
        }
        Order currentOrder = orderService.getCurrentUserOrder(user);
        model.addAttribute("order", currentOrder);
        model.addAttribute("user", user);
        return view;
    }

    @RequestMapping("/createOrder")
    public String showCreateOrderForm(Model model) {
        LOG.info("show create order form!!!");
        String username = ((org.springframework.security.core.userdetails.User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        return "view/client/createOrder";
    }

    @RequestMapping("/newOrders")
    public String showNewOrdersList(Model model) {
        LOG.info("show new orders");
        String username = ((org.springframework.security.core.userdetails.User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userService.getUserByUsername(username);
        model.addAttribute("user", user);

        List orderList = orderService.getNewOrders();
        model.addAttribute("orders", orderList);
        return "view/driver/ordersList";
    }

    @RequestMapping(value = "/completeOrder", method = RequestMethod.POST)
    public String completeOrder(@ModelAttribute("Order") Order order) {
        String username = ((org.springframework.security.core.userdetails.User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userService.getUserByUsername(username);
//        User user = (User) session.getAttribute("user");
        orderService.updateOrderStatus(order.getId(), user.getId(), "complete");
        return "redirect:/newOrders";
    }

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public String createOrder(@ModelAttribute("Order") Order order) {
        String username = ((org.springframework.security.core.userdetails.User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userService.getUserByUsername(username);
        order.setClientId(user.getId());
        orderService.insertOrder(order);
        return "redirect:/myOrder";
    }

    @RequestMapping(value = "/acceptOrder", method = RequestMethod.POST)
    public String acceptOrder(@ModelAttribute("Order") Order order) {
        String username = ((org.springframework.security.core.userdetails.User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userService.getUserByUsername(username);
        orderService.updateOrderStatus(order.getId(), user.getId(), "progress");
        return "redirect:/myOrder";
    }
}
