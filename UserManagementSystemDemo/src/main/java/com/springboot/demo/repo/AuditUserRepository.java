package com.springboot.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.demo.model.AuditUser;
@Repository
public interface AuditUserRepository extends JpaRepository<AuditUser, Integer> {

}
