package com.jony.mapper;

import com.jony.entity.User;
import java.util.List;

public interface UserMapper {
    List<User> getAllUser();
    List<User> findAllUser();
    List<User> findAllUserByUserName(String userName);
    int createUser(User user);
    
    int deleteUser(User user);
    
    int updateUser(User user);
}
