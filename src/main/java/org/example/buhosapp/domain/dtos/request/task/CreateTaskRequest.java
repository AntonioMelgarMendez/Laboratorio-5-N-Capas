package org.example.buhosapp.domain.dtos.request.task;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTaskRequest {

    @NotBlank(message = "Title must not be blank")
    private String title;
}
