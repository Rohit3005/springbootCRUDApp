package com.demo.controller;

import com.demo.exception.RecordNotFoundException;
import com.demo.model.Employee;
import com.demo.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService;

    public ResponseEntity<Employee> saveData(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.saveData(employee), HttpStatus.CREATED);
    }

    public ResponseEntity<List<Employee>> getAllData() {
        return ResponseEntity.ok(employeeService.getAllData());
    }

    public ResponseEntity<Optional<Employee>> getDataById(@PathVariable int empId) {
        return ResponseEntity.ok(employeeService.getDataById(empId));
    }

    public ResponseEntity<Employee> updateData(@PathVariable int empId, @RequestBody Employee employee) {

        Employee employee1 = employeeService.getDataById(empId).orElseThrow(() -> new RecordNotFoundException("ID Doesn't exist"));

        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpSalary(employee.getEmpSalary());

        return new ResponseEntity<>(employeeService.updateData(employee1), HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteData(@PathVariable int empId) {
        employeeService.deleteData(empId);
        return ResponseEntity.ok("Data Deleted");
    }

}

