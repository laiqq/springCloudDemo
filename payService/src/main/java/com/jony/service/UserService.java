package com.jony.service;

import com.jony.entity.Order;
import com.jony.entity.User;
import java.util.List;

public interface UserService {
	List<User> findUserNmae(String dbKey,User user);
    
    int createUser(String dbKey,User user);
    
    int deleteUser(String dbKey,User user);
    
    int updateUser(String dbKey,User user);
    
    int CreateOrder(String dbKey,Order order);
}
