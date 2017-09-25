package com.kadena.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kadena.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
	User save(User user);

	User findByUsername(String email);
}
