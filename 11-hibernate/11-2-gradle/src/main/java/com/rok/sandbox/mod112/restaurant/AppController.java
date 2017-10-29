package com.rok.sandbox.mod112.restaurant;

import com.rok.sandbox.mod112.restaurant.model.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by roman.kulikov on 6/14/2017.
 * All rights reserved =D
 */
@Controller
@RequestMapping("/restaurant")
public class AppController {
    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value="", method = RequestMethod.GET)
    public String getRestaurantInfo(ModelMap model) {
        model.addAttribute("restaurant", restaurantService.getRestaurant());
        return "restaurant";
    }

    @RequestMapping(value = "/tables", method = RequestMethod.GET)
    public String getTables(
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "visitorName", required = false) String visitorName,
            ModelMap model) {
        List<Table> tables = restaurantService.getTables(status, visitorName);
        model.addAttribute("tables", tables);
        return "tables";
    }

    @RequestMapping(value = "/tables/add", method = RequestMethod.POST)
    public String addTable(@RequestParam(value = "number") int number,
                           @RequestParam(value = "desc") String description) {
        restaurantService.addTable(number, description);
        return "redirect:/restaurant";
    }

    @RequestMapping(value = "/tables/remove", method = RequestMethod.POST)
    public String removeTable(@RequestParam(value = "number") int number) {
        restaurantService.removeTable(number);
        return "redirect:/restaurant";
    }

    @RequestMapping(value = "/visitors/add", method = RequestMethod.POST)
    public String addVisitor(@RequestParam(value = "name") String visitorName) {
        restaurantService.addVisitor(visitorName);
        return "redirect:/restaurant";
    }

    @RequestMapping(value = "/visitors/remove", method = RequestMethod.POST)
    public String removeVisitor(@RequestParam(value = "name") String visitorName) {
        restaurantService.removeVisitor(visitorName);
        return "redirect:/restaurant";
    }

    @RequestMapping(value = "/tables/book", method = RequestMethod.POST)
    public String checkOutTable(
            @RequestParam(value = "number") int number,
            @RequestParam(value = "visitor") String visitorName) {
        restaurantService.bookTable(number, visitorName);
        return "redirect:/restaurant";
    }

    @RequestMapping(value = "/tables/unbook", method = RequestMethod.POST)
    public String checkInTable(@RequestParam(value = "number") int number) {
        restaurantService.unbookTable(number);
        return "redirect:/restaurant";
    }



}
