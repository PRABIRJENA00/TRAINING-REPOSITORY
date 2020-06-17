package com.springboot.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.demo.exception.ResourceNotFoundException;
import com.springboot.demo.model.Employee;
import com.springboot.demo.repository.EmployeeRepository;
import com.springboot.demo.service.EmployeeServcie;

@SpringBootTest
public class SpringBootJpaDemoApplicationTests {

	@InjectMocks
    EmployeeServcie employeeServcie;
     
    @Mock
    EmployeeRepository employeeRepository;
 
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
     
    @Test
    public void getAllEmployeesTest()
    {
        List<Employee> list = new ArrayList<Employee>();
        Employee emp1 = new Employee(1, "Prabir", 6);
        Employee emp2 = new Employee(2, "Rakesh", 7);
        Employee emp3 = new Employee(3, "Anupesh", 8);
         
        list.add(emp1);
        list.add(emp2);
        list.add(emp3);
         
        when(employeeRepository.getAllEmployee()).thenReturn(list);
         
        //test
        List<Employee> empList = employeeServcie.getAllEmployee();
         
        assertEquals(3, empList.size());
        verify(employeeRepository, times(1)).getAllEmployee();
    }
     
    @Test
    public void getEmployeeByIdTest() throws ResourceNotFoundException
    {
    	Optional<Employee> e1=Optional.of(new Employee(1, "Prabir", 6));
        when(employeeRepository.findById(1)).thenReturn(e1);
         
        Optional<Employee> emp = employeeServcie.findById(1);
         
        assertEquals("Prabir", emp.get().getEname());
        assertEquals(6, emp.get().getExp().intValue());
        
    }
}     
