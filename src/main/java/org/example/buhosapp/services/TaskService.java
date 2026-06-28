package org.example.buhosapp.services;

import org.example.buhosapp.domain.dtos.request.task.CreateTaskRequest;
import org.example.buhosapp.domain.dtos.response.task.TaskResponse;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    TaskResponse createTask(CreateTaskRequest request);

    TaskResponse getTaskById(UUID id);

    List<TaskResponse> getAllTasks();
}
