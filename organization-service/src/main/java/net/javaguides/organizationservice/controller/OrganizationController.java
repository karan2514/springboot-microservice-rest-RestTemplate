package net.javaguides.organizationservice.controller;

import lombok.AllArgsConstructor;
import net.javaguides.organizationservice.DTO.OrganizationDTO;
import net.javaguides.organizationservice.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/organizations")
public class OrganizationController {

    private OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationDTO> createOrganization(@RequestBody OrganizationDTO organizationDTO){
       OrganizationDTO organizationDTO1 = organizationService.createOrganization(organizationDTO);
        return new ResponseEntity<OrganizationDTO>(organizationDTO1, HttpStatus.CREATED);
    }

    @GetMapping("/{orgCode}")
    public ResponseEntity<OrganizationDTO> getOrganization(@PathVariable("orgCode") String orgCode){
        OrganizationDTO organizationDTO1 = organizationService.getOrganizationByCode(orgCode);
        //OrganizationDTO organizationDTO1 = organizationService.createOrganization(organizationDTO);
        return new ResponseEntity<>(organizationDTO1, HttpStatus.OK);
    }

}
