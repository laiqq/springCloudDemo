package com.jony.service.impl;

import com.jony.config.DataSourceKey;
import com.jony.entity.Order;
import com.jony.entity.User;
import com.jony.mapper.UserMapper;
import com.jony.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> findUserNmae(String dbKey,User user) {
        List<User> userList = null;
        try {
        	String userName = user.getUserName();
            userList = userMapper.findAllUserByUserName(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }
    

    
	@Override
	@DataSourceKey(value = "dbKey")
	public int createUser(String dbKey,User user) {
		// TODO Auto-generated method stub
		int i = userMapper.createUser(user);
		return  i;
	}

	@Override
	public int deleteUser(String dbKey,User user) {
		// TODO Auto-generated method stub
		int count = userMapper.deleteUser(user);
		return count;
	}

	@Override
	public int updateUser(String dbKey,User user) {
		// TODO Auto-generated method stub
		 int count = userMapper.updateUser(user);
		return count;
				
	}



	@Override
	public int CreateOrder(String dbKey, Order order) {
		// TODO Auto-generated method stub
		return 0;
	}

}
