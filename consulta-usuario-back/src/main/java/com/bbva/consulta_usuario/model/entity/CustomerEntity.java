package com.bbva.consulta_usuario.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "customers",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_document_type_number",
                        columnNames = {
                                "identity_document_type_id",
                                "identity_document_number"
                        }
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false, length = 50)
    private String customerId;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "nationality_id", length = 10)
    private String nationalityId;

    @Column(name = "gender_id", length = 10)
    private String genderId;

    @Column(name = "identity_document", length = 10)
    private String identityDocument;

    @Column(
            name = "identity_document_number",
            nullable = false,
            length = 30
    )
    private String identityDocumentNumber;

    @Column(
            name = "identity_document_type_id",
            nullable = false
    )
    private Integer identityDocumentTypeId;
}
