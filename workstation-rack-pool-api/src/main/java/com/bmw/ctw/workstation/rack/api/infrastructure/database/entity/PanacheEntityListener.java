package com.bmw.ctw.workstation.rack.api.infrastructure.database.entity;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class PanacheEntityListener {
    private static final String FALLBACK_USER = "applicationUser";

    @PrePersist
    void onCreate(final BaseEntity entity) {
        LocalDateTime now = LocalDateTime.now();
        entity.createdAt = now;
        entity.modifiedAt = now;
        entity.createdBy = FALLBACK_USER;
        entity.modifiedBy = FALLBACK_USER;
    }

    @PreUpdate
    void onUpdate(final BaseEntity entity) {
        entity.modifiedAt = LocalDateTime.now();
        entity.modifiedBy = FALLBACK_USER;
    }
}
