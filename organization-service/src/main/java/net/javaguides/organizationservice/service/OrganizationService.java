package net.javaguides.organizationservice.service;

import net.javaguides.organizationservice.DTO.OrganizationDTO;
import net.javaguides.organizationservice.entity.Organization;

public interface OrganizationService {

    public OrganizationDTO createOrganization(OrganizationDTO organizationDTO);
    //public OrganizationDTO getOrganization(Long orgId);

    public OrganizationDTO getOrganizationByCode(String organizationCode);
}
