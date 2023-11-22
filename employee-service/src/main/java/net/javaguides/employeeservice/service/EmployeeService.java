package net.javaguides.employeeservice.service;

import net.javaguides.employeeservice.dto.APIResponseDTO;
import net.javaguides.employeeservice.dto.EmployeeDTO;

public interface EmployeeService {

    EmployeeDTO saveEmployee(EmployeeDTO departmentDTO);

    APIResponseDTO getEmployeeById(Long id);
}
