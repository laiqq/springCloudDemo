package com.jony.controller;

import com.jony.entity.Order;
import com.jony.entity.User;
import com.jony.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;


@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserService userService;

    @GetMapping("/testRead/{userName}")
    @ResponseBody
    public String testRead(@PathVariable String userName){
        return "test";
    }

    @RequestMapping(value="/selectUser", method = RequestMethod.POST)
    @ResponseBody
    public List<User> selectUser(@RequestBody User user){
    	String dbKey = user.getUid();
        return  userService.findUserNmae(dbKey,user);
    }
    

    
    @RequestMapping(value="/createUser", method = RequestMethod.POST)
    @ResponseBody
    public int createUser(@RequestBody User user){
    	String dbKey = user.getUid();
    	int count =  userService.createUser(dbKey,user);
        return count;
    }
    
    @RequestMapping(value="/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public String deleteUser(@RequestBody User user){
    	String dbKey = user.getUid();
    	int count =userService.deleteUser(dbKey,user);
        return count+"";
    }
    
    @RequestMapping(value="/updateUser", method = RequestMethod.POST)
    @ResponseBody
    public String updateUser(@RequestBody User user){
    	String dbKey = user.getUid();
    	int count = userService.updateUser(dbKey,user);
        return count+"";
    }
    
    @RequestMapping(value="/createOrder", method = RequestMethod.POST)
    @ResponseBody
    public int createOrder(@RequestBody Order order){
    	String dbKey = order.getUid();
    	int count =  userService.CreateOrder(dbKey,order);
        return count;
    }
}
