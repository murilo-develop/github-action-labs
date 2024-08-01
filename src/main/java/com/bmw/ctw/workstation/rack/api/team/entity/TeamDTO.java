package com.bmw.ctw.workstation.rack.api.team.entity;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

public record TeamDTO(UUID id,
                      String name,
                      String product) {

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeamDTO that = (TeamDTO) o;
        return Objects.equals(this.name(), that.name()) && Objects.equals(this.product(), that.product());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name(), this.product());
    }

    public static class Builder {
        public UUID id;
        public String name;
        public String product;

        public TeamDTO.Builder with(Consumer<TeamDTO.Builder> builder) {
            builder.accept(this);
            return this;
        }

        public TeamDTO build() {
            return new TeamDTO(
                this.id,
                this.name,
                this.product
            );
        }
    }
}
