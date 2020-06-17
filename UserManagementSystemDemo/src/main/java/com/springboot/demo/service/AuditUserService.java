package com.springboot.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.demo.model.AuditUser;
import com.springboot.demo.repo.AuditUserRepository;
@Service
public class AuditUserService {

	@Autowired
	AuditUserRepository usersRepository;

	public List<AuditUser> getAuditUsers() {
		return (List<AuditUser>) usersRepository.findAll();
	}

	public AuditUser getAuditUserById(int id) {
		return usersRepository.findById((int) id).get();
	}

	public AuditUser saveOrUpdate(AuditUser user) {
		return usersRepository.save(user);
	}

	public void delete(int id) {
		usersRepository.deleteById((int) id);
	}
}
