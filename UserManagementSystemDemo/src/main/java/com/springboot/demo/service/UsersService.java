package com.springboot.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.demo.model.Users;
import com.springboot.demo.repo.UsersRepository;
@Service
public class UsersService {
	
	@Autowired
	UsersRepository usersRepository;

	public List<Users> getAllUsers() {
		return (List<Users>) usersRepository.findAll();
	}

	public Users getUserById(int id) {
		return usersRepository.findById((int) id).get();
	}

	public Users saveOrUpdate(Users user) {
		return usersRepository.save(user);
	}

	public void delete(int id) {
		usersRepository.deleteById((int) id);
	}

}
