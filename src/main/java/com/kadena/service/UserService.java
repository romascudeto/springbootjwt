package com.kadena.service;

import com.kadena.dao.UserDao;
import com.kadena.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  
	@Autowired
	private UserDao userDao;

	public User save(User user) {
		return userDao.save(user);
	}

	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

}
