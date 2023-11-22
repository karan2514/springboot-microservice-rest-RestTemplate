package net.javaguides.employeeservice.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.employeeservice.dto.APIResponseDTO;
import net.javaguides.employeeservice.dto.EmployeeDTO;
import net.javaguides.employeeservice.dto.DepartmentDTO;
import net.javaguides.employeeservice.entity.Employee;
import net.javaguides.employeeservice.repository.EmployeeRepository;
import net.javaguides.employeeservice.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
   private EmployeeRepository employeeRepository;

    private RestTemplate restTemplate;
    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        //convert departmentDTO to Department JPA

        Employee employee = new Employee(
                employeeDTO.getId(),
                employeeDTO.getFirstName(),
                employeeDTO.getLastname(),
                employeeDTO.getEmail(),
                employeeDTO.getDepartmentCode()
        );


        Employee savedEmployee = employeeRepository.save(employee);
EmployeeDTO savedEmployeeDTO = new EmployeeDTO(
        employee.getId(),
        employee.getFirstName(),
        employee.getLastname(),
        employee.getEmail(),
        employeeDTO.getDepartmentCode()
);

        //convert Department JPA to DepartmentDTO
        return savedEmployeeDTO;
    }

    @Override
    public APIResponseDTO getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();

       ResponseEntity<DepartmentDTO> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(), DepartmentDTO.class);

            DepartmentDTO departmentDTO = responseEntity.getBody();

        EmployeeDTO employeeDTO = new EmployeeDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastname(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );

        APIResponseDTO apiResponseDTO = new APIResponseDTO();
                apiResponseDTO.setEmployee(employeeDTO);
                apiResponseDTO.setDepartment(departmentDTO);


        return apiResponseDTO;


    }

   /* @Override
    public DepartmentDTO getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);

        DepartmentDTO departmentDTO = new DepartmentDTO(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
        return departmentDTO;
    }*/
}
