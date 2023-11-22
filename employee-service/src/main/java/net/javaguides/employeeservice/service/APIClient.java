package net.javaguides.employeeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import net.javaguides.employeeservice.dto.DepartmentDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        url = "http://localhost:8080", value = "DEPARTMENT-SERVICE"
)
public interface APIClient {
    @GetMapping("api/departments/{deptCode}")
    DepartmentDTO getDepartment(@PathVariable(name = "deptCode") String departmentCode);

}
