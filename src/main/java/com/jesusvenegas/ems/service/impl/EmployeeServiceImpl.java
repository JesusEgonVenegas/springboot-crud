package com.jesusvenegas.ems.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jesusvenegas.ems.dto.EmployeeDto;
import com.jesusvenegas.ems.entity.Employee;
import com.jesusvenegas.ems.exception.ResourceNotFoundException;
import com.jesusvenegas.ems.mapper.EmployeeMapper;
import com.jesusvenegas.ems.repository.EmployeeRepository;
import com.jesusvenegas.ems.service.EmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private EmployeeRepository employeeRepository;

  @Override
  public EmployeeDto createEmployee(EmployeeDto employeeDto) {

    Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
    Employee savedEmployee = employeeRepository.save(employee);
    return EmployeeMapper.mapToEmployeeDto(savedEmployee);
  }

  @Override
  public EmployeeDto getEmployeeById(Long employeeId) {
    Employee employee = employeeRepository.findById(employeeId)
        .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with given id: " + employeeId));
    return EmployeeMapper.mapToEmployeeDto(employee);
  }

  @Override
  public List<EmployeeDto> getAllEmployees() {
    List<Employee> employees = employeeRepository.findAll();
    return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
        .collect(Collectors.toList());
  }

  @Override
  public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
    Employee employee = employeeRepository.findById(employeeId).orElseThrow(
        () -> new ResourceNotFoundException("Employee does not exist with id: " + employeeId));

    if (updatedEmployee.getFirstName() != null) {
      employee.setFirstName(updatedEmployee.getFirstName());
    }

    if (updatedEmployee.getLastName() != null) {
      employee.setLastName(updatedEmployee.getLastName());
    }

    if (updatedEmployee.getEmail() != null) {
      employee.setEmail(updatedEmployee.getEmail());
    }

    Employee updatedEmployeeObj = employeeRepository.save(employee);

    return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
  }

  public void deleteEmployee(Long employeeId) {

    employeeRepository.findById(employeeId).orElseThrow(
        () -> new ResourceNotFoundException("Employee does not exist with id: " + employeeId));

    employeeRepository.deleteById(employeeId);

  }
}
