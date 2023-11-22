package net.javaguides.employeeservice.controller;

import lombok.AllArgsConstructor;
import net.javaguides.employeeservice.dto.APIResponseDTO;
import net.javaguides.employeeservice.dto.EmployeeDTO;
import net.javaguides.employeeservice.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeeController {
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDTO> saveDepartment(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO savedEmployeeDTO = employeeService.saveEmployee(employeeDTO);
        return new ResponseEntity<>(savedEmployeeDTO, HttpStatus.CREATED);
    }

   @GetMapping("/{employeeId}")
    public ResponseEntity<APIResponseDTO> getDepartment(@PathVariable(name = "employeeId") Long employeeId){
       APIResponseDTO apiResponseDTO = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }

}
