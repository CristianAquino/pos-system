package cris_dev.pos_system.User.Model.DTO.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UserPatchRequest(
        @Positive
        @NotNull
        UUID id,
        @Size(max = 80)
        String name,
        @Size(max = 60)
        String fatherLastName,
        @Size(max = 60)
        String motherLastName,
        String status) {
}
