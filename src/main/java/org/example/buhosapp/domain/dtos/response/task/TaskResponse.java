package org.example.buhosapp.domain.dtos.response.task;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class TaskResponse {

    private UUID id;
    private String title;
    private boolean completed;
}
