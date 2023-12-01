package net.javaguides.employeeservice.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import net.javaguides.employeeservice.dto.APIResponseDTO;
import net.javaguides.employeeservice.dto.EmployeeDTO;
import net.javaguides.employeeservice.dto.DepartmentDTO;
import net.javaguides.employeeservice.dto.OrganizationDTO;
import net.javaguides.employeeservice.entity.Employee;
import net.javaguides.employeeservice.mapper.EmployeeMapper;
import net.javaguides.employeeservice.repository.EmployeeRepository;
import net.javaguides.employeeservice.service.APIClient;
import net.javaguides.employeeservice.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;



@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
   private EmployeeRepository employeeRepository;

   private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

   /* private RestTemplate restTemplate;*/
    private WebClient webClient;
    private APIClient apiClient;


    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        //convert departmentDTO to Department JPA

        Employee employee = EmployeeMapper.mapToEmployee(employeeDTO);


        Employee savedEmployee = employeeRepository.save(employee);
EmployeeDTO savedEmployeeDTO = EmployeeMapper.mapToEmployeeDTO(savedEmployee);

        //convert Department JPA to DepartmentDTO
        return savedEmployeeDTO;
    }

    //@CircuitBreaker(name="${spring.application.name}", fallbackMethod = "getDefaultDepartment")
   @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDTO getEmployeeById(Long employeeId) {

        LOGGER.info("inside getEmployeeId() method");
        Employee employee = employeeRepository.findById(employeeId).get();

      /* ResponseEntity<DepartmentDTO> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(), DepartmentDTO.class);

            DepartmentDTO departmentDTO = responseEntity.getBody();*/

       DepartmentDTO departmentDTO = webClient.get()
                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDTO.class)
                .block();

       OrganizationDTO organizationDTO = webClient.get()
               .uri("http://localhost:8083/api/organizations/" + employee.getOrganizationCode())
               .retrieve()
               .bodyToMono(OrganizationDTO.class)
               .block();


       //DepartmentDTO departmentDTO = apiClient.getDepartment(employee.getDepartmentCode());
        EmployeeDTO employeeDTO = EmployeeMapper.mapToEmployeeDTO(employee);

        APIResponseDTO apiResponseDTO = new APIResponseDTO();
                apiResponseDTO.setEmployee(employeeDTO);
                apiResponseDTO.setDepartment(departmentDTO);
                apiResponseDTO.setOrganization(organizationDTO);


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

    public APIResponseDTO getDefaultDepartment(Long employeeId, Exception exception){
        LOGGER.info("inside getDefaultDepartment() method");
        Employee employee = employeeRepository.findById(employeeId).get();

      DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartmentName("R&D Department");
      departmentDTO.setDepartmentCode("RD001");
      departmentDTO.setDepartmentDescription("Research and Development Department");


        //DepartmentDTO departmentDTO = apiClient.getDepartment(employee.getDepartmentCode());
        EmployeeDTO employeeDTO = EmployeeMapper.mapToEmployeeDTO(employee);

        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        apiResponseDTO.setEmployee(employeeDTO);
        apiResponseDTO.setDepartment(departmentDTO);


        return apiResponseDTO;

    }
}
