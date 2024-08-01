package com.bmw.ctw.workstation.rack.api.infrastructure.database.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(PanacheEntityListener.class)
public abstract class BaseEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    public UUID id;

    @JsonbTransient
    @Column(name = "CREATED_AT", updatable = false, nullable = false)
    public LocalDateTime createdAt;

    @JsonbTransient
    @Column(name = "CREATED_BY", updatable = false)
    public String createdBy;

    @JsonbTransient
    @Column(name = "MODIFIED_AT")
    public LocalDateTime modifiedAt;

    @JsonbTransient
    @Column(name = "MODIFIED_BY")
    public String modifiedBy;

    @JsonbTransient
    @Version
    @Column(name = "VERSION", nullable = false)
    public Long version;
}
