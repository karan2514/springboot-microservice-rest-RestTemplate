package net.javaguides.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class APIResponseDTO {
    private DepartmentDTO department;
    private EmployeeDTO employee;
    private OrganizationDTO organization;
}
