package com.quizer.dao;

import com.quizer.model.User;
import java.util.List;

public interface UserDao {

    public List<User> getListUser();
    
    public void saveOrUpdate(User user);
    
    public void deleteUser(int id);
    
    public User findUserById(int id);
    
}
