package com.jesusvenegas.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesusvenegas.ems.entity.Employee;

/**
 * EmployeeRepository
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
