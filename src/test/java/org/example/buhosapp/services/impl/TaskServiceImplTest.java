package org.example.buhosapp.services.impl;

import org.example.buhosapp.common.mappers.TaskMapper;
import org.example.buhosapp.domain.dtos.request.task.CreateTaskRequest;
import org.example.buhosapp.domain.dtos.response.task.TaskResponse;
import org.example.buhosapp.domain.entities.Task;
import org.example.buhosapp.exceptions.ResourceNotFoundException;
import org.example.buhosapp.repositories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskServiceImpl taskService;

    private UUID taskId;
    private CreateTaskRequest request;
    private Task taskEntity;
    private TaskResponse taskResponse;

    @BeforeEach
    void setUp() {
        taskId = UUID.randomUUID();

        request = CreateTaskRequest.builder()
                .title("Buy groceries")
                .build();

        taskEntity = Task.builder()
                .id(taskId)
                .title("Buy groceries")
                .completed(false)
                .build();

        taskResponse = TaskResponse.builder()
                .id(taskId)
                .title("Buy groceries")
                .completed(false)
                .build();
    }

    @Test
    void createTask_shouldSaveAndReturnTaskResponse() {
        when(taskMapper.toEntity(request)).thenReturn(taskEntity);
        when(taskRepository.save(taskEntity)).thenReturn(taskEntity);
        when(taskMapper.toDto(taskEntity)).thenReturn(taskResponse);

        TaskResponse result = taskService.createTask(request);

        assertThat(result).isEqualTo(taskResponse);
    }

    @Test
    void getTaskById_shouldReturnTask_whenExists() {
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(taskEntity));
        when(taskMapper.toDto(taskEntity)).thenReturn(taskResponse);

        TaskResponse result = taskService.getTaskById(taskId);

        assertThat(result).isEqualTo(taskResponse);
    }

    @Test
    void getTaskById_shouldThrow_whenNotFound() {
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> taskService.getTaskById(taskId))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Task not found");
    }
}
