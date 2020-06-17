package com.springboot.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.demo.model.Users;
@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

}
