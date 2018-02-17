package com.example;

import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {
 
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private EmployeeRepository employeeRepository;
 
    // write test cases here
    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        Employee alex = new Employee(10,"alex");
        entityManager.persist(alex);
        entityManager.flush();
        
        Employee alan = new Employee(20,"alan");
        alan.setManager(alex);
        entityManager.persist(alan);
        entityManager.flush();        
     
        // when
        Employee foundA = employeeRepository.getOne(10);
        Employee foundB = employeeRepository.getOne(20);
     
        // then
        Assert.assertEquals(foundA.getName(), alex.getName());
        Assert.assertEquals(foundB.getManager(), alan.getManager());
        
    }
}

