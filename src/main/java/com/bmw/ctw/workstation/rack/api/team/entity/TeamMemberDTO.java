package com.bmw.ctw.workstation.rack.api.team.entity;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

public record TeamMemberDTO(UUID id,
                            String ctwCode,
                            String name,
                            TeamDTO team) {
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeamMemberDTO that = (TeamMemberDTO) o;
        return Objects.equals(this.ctwCode(), that.ctwCode());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.ctwCode());
    }

    public static class Builder {
        public UUID id;
        public String ctwCode;
        public String name;
        public TeamDTO team;

        public TeamMemberDTO.Builder with(Consumer<TeamMemberDTO.Builder> builder) {
            builder.accept(this);
            return this;
        }

        public TeamMemberDTO build() {
            return new TeamMemberDTO(
                this.id,
                this.ctwCode,
                this.name,
                this.team
            );
        }
    }
}
