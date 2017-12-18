package com.quizer.controller;

import com.quizer.model.User;
import com.quizer.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author UZAIR
 */
@RestController
public class UserController {
    
    @Autowired
    UserService userService;

    //login user mapping
    @RequestMapping(value="/login/" , method=RequestMethod.POST , headers="Accept=application/json")
    public @ResponseBody User verifyLogin(@RequestBody User user){ 
        
        User validuser = userService.findUser(user);
        
        return validuser;
    }
    
    //user list mapping
    @RequestMapping(value="/readusers/" , method=RequestMethod.GET , headers="Accept=application/json")
    public @ResponseBody List<User> getUserList(){
        List<User> users = userService.getUserList();
        
        return users;
    }

    //user create mapping
    @RequestMapping(value="/createuser/" , method=RequestMethod.POST)
    public @ResponseBody User add(@RequestBody User user){
        userService.saveOrUpdate(user);
        
        return user;
    }
    
    //user update mapping
    @RequestMapping(value="/updateuser/{id}" , method=RequestMethod.PUT)
    public @ResponseBody User update(@PathVariable("id") int id, @RequestBody User user ){
        user.setId(id);
        userService.saveOrUpdate(user);
        
        return user;
    }
    
    //user delete mapping
    @RequestMapping(value="/deleteuser/{id}" , method=RequestMethod.DELETE)
    public @ResponseBody User delete(@PathVariable("id") int id){ 
        User user = userService.findUserById(id);
        userService.deleteUser(id);
        
        return user;
    }
   
}
