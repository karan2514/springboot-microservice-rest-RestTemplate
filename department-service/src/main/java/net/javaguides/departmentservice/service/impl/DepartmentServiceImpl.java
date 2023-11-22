package net.javaguides.departmentservice.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.departmentservice.dto.DepartmentDTO;
import net.javaguides.departmentservice.entity.Department;
import net.javaguides.departmentservice.repository.DepartmentRepository;
import net.javaguides.departmentservice.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    DepartmentRepository departmentRepository;
    ModelMapper modelMapper;
    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        //convert departmentDTO to Department JPA

        Department department = modelMapper.map(departmentDTO,Department.class);


        Department savedDepartment = departmentRepository.save(department);
DepartmentDTO savedDepartmentDTO = modelMapper.map(department,DepartmentDTO.class);

        //convert Department JPA to DepartmentDTO
        return savedDepartmentDTO;
    }

    @Override
    public DepartmentDTO getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);

        DepartmentDTO departmentDTO = new DepartmentDTO(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
        return departmentDTO;
    }
}
