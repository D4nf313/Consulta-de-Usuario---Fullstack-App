package com.bbva.consulta_usuario.repository;

import com.bbva.consulta_usuario.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByIdentityDocumentTypeIdAndIdentityDocumentNumber(
            Integer identityDocumentTypeId,
            String identityDocumentNumber
    );
}