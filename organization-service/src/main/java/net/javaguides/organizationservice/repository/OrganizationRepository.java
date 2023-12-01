package net.javaguides.organizationservice.repository;

import net.javaguides.organizationservice.DTO.OrganizationDTO;
import net.javaguides.organizationservice.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization,Long> {

    public Organization findByOrganizationCode(String organizationCode);
}
