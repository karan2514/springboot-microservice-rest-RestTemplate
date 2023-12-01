package net.javaguides.organizationservice.service.Impl;

import lombok.AllArgsConstructor;
import net.javaguides.organizationservice.DTO.OrganizationDTO;
import net.javaguides.organizationservice.entity.Organization;
import net.javaguides.organizationservice.mapper.OrganizationMapper;
import net.javaguides.organizationservice.repository.OrganizationRepository;
import net.javaguides.organizationservice.service.OrganizationService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;
    @Override
    public OrganizationDTO createOrganization(OrganizationDTO organizationDTO) {
        Organization organization = OrganizationMapper.mapToOrganization(organizationDTO);
        Organization savedOrganization = organizationRepository.save(organization);
       OrganizationDTO organizationDTO1 = OrganizationMapper.mapToOrganizationDTO(savedOrganization);
        return organizationDTO1;
    }

  /*  @Override
    public OrganizationDTO getOrganization(Long orgId) {
       Organization organization = organizationRepository.findById(orgId).get();
       OrganizationDTO organizationDTO = OrganizationMapper.mapToOrganizationDTO(organization);
        return organizationDTO;
    }*/

    @Override
    public OrganizationDTO getOrganizationByCode(String organizationCode) {

        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);

        OrganizationDTO organizationDTO = OrganizationMapper.mapToOrganizationDTO(organization);

        return organizationDTO;
    }



}
