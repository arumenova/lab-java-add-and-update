package com.ironhack.lab8.controller;

import com.ironhack.lab8.model.Employee;
import com.ironhack.lab8.repository.EmployeeRepository;
import com.ironhack.lab8.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;


    @GetMapping("/initialize")
    public ResponseEntity<List<Employee>> initializeEmployees() {
        log.info("Initializing all employees.");
        List<Employee> employees = employeeService.setEmployees();
        return ResponseEntity.ok(employees);
    }


    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        log.info("Fetching all employees.");
        List<Employee>employees=employeeService.getEmployees();
        return ResponseEntity.ok(employees);

    }


    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
       Employee employee = employeeService.getEmployeeById(id);
       if (employee != null) {
           return ResponseEntity.ok(employee);
       }
       else
           return ResponseEntity.notFound().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<Employee> getEmployeeStatus(@PathVariable String status) {
        log.info("Fetching employee status for status {}", status);
        List<Employee> employees = employeeService.getEmployeeByStatus(status);
        if (employees.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employees.get(0));
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<Employee>> getEmployeeByDepartment(@PathVariable String department) {
        log.info("Fetching employee by department {}", department);
        List<Employee> employees = employeeService.getEmployeeByDepartment(department);
        if (employees.isEmpty()) {
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(employees);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee employee1=employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee1);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        String status = employee.getStatus();
        Employee updatedEmployee = employeeService.updateEmployeeStatus(status, id);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @PatchMapping("/{id}/department")
    public ResponseEntity<Employee> updateEmployeeDepartment(@PathVariable Long id, @RequestBody Employee employee) {
        String department = employee.getDepartment();
        Employee updatedEmployee = employeeService.updateEmployeeDepartment(department, id);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }
}
