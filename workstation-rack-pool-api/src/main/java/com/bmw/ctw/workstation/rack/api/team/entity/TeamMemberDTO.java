package com.bmw.ctw.workstation.rack.api.team.entity;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

public record TeamMemberDTO(UUID getId,
                            String getCtwCode,
                            String getName,
                            TeamDTO getTeamDTO) {
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeamMemberDTO that = (TeamMemberDTO) o;
        return Objects.equals(this.getCtwCode(), that.getCtwCode());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getCtwCode());
    }

    public static class Builder {
        public UUID getId;
        public String getCtwCode;
        public String getName;
        public TeamDTO getTeamDTO;

        public TeamMemberDTO.Builder with(Consumer<TeamMemberDTO.Builder> builder) {
            builder.accept(this);
            return this;
        }

        public TeamMemberDTO build() {
            return new TeamMemberDTO(
                this.getId,
                this.getCtwCode,
                this.getName,
                this.getTeamDTO
            );
        }
    }
}
