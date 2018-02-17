package com.example;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(
  classes = DemoApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
  locations = "classpath:application-integrationtest.properties")
public class EmployeeRestControllerTest {
 
    @Autowired
    private MockMvc mvc;
 
    @Autowired
    private EmployeeRepository repository;
 
    // write test cases here
    @Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
      throws Exception {
         
        Employee alex = new Employee(10, "alex");
        repository.save(alex);
     
        mvc.perform(get("/employees")
        	      .contentType(MediaType.APPLICATION_JSON))
        	      .andExpect(status().isOk())
        	      .andExpect(content()
        	      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        	      .andExpect(jsonPath("$[0].name", is("alex")));
    }
}
