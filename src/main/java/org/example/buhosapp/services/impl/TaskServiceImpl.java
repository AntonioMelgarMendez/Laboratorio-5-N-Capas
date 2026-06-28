package org.example.buhosapp.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.buhosapp.common.mappers.TaskMapper;
import org.example.buhosapp.domain.dtos.request.task.CreateTaskRequest;
import org.example.buhosapp.domain.dtos.response.task.TaskResponse;
import org.example.buhosapp.exceptions.ResourceNotFoundException;
import org.example.buhosapp.repositories.TaskRepository;
import org.example.buhosapp.services.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskResponse createTask(CreateTaskRequest request) {
        return taskMapper.toDto(taskRepository.save(taskMapper.toEntity(request)));
    }

    @Override
    public TaskResponse getTaskById(UUID id) {
        return taskMapper.toDto(
                taskRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Task not found"))
        );
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toDto)
                .toList();
    }
}
