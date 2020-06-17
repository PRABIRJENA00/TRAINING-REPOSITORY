package com.springboot.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.springboot.demo.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("from Employee")
	public List<Employee> getAllEmployee();

	@Query(value = "select * from Employee order by id ASC", nativeQuery = true)
	public List<Employee> getAllSortedEmployee();

	@Query(value = "select * from Employee where exp>=5", nativeQuery = true)
	public List<Employee> getMoreExpEmployees();

	@Query(value = "select * from Employee where exp<5", nativeQuery = true)
	public List<Employee> getLessExpEmployees();
}
