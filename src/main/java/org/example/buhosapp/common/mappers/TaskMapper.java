package org.example.buhosapp.common.mappers;

import org.example.buhosapp.domain.dtos.request.task.CreateTaskRequest;
import org.example.buhosapp.domain.dtos.response.task.TaskResponse;
import org.example.buhosapp.domain.entities.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task toEntity(CreateTaskRequest request) {
        return Task.builder()
                .title(request.getTitle())
                .completed(false)
                .build();
    }

    public TaskResponse toDto(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .completed(task.isCompleted())
                .build();
    }
}
