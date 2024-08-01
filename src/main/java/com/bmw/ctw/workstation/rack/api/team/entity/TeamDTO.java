package com.bmw.ctw.workstation.rack.api.team.entity;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

public record TeamDTO(UUID getId,
                      String getName,
                      String getProduct) {

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeamDTO that = (TeamDTO) o;
        return Objects.equals(this.getName(), that.getName()) && Objects.equals(this.getProduct(), that.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getProduct());
    }

    public static class Builder {
        public UUID getId;
        public String getName;
        public String getProduct;

        public TeamDTO.Builder with(Consumer<TeamDTO.Builder> builder) {
            builder.accept(this);
            return this;
        }

        public TeamDTO build() {
            return new TeamDTO(
                this.getId,
                this.getName,
                this.getProduct
            );
        }
    }
}
