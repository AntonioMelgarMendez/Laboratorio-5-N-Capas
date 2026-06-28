package org.example.buhosapp.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.buhosapp.domain.dtos.request.task.CreateTaskRequest;
import org.example.buhosapp.domain.dtos.response.GeneralResponse;
import org.example.buhosapp.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<GeneralResponse> createTask(@Valid @RequestBody CreateTaskRequest request) {
        return buildResponse("Task created successfully", HttpStatus.CREATED, taskService.createTask(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse> getTaskById(@PathVariable UUID id) {
        return buildResponse("Task found", HttpStatus.OK, taskService.getTaskById(id));
    }

    @GetMapping
    public ResponseEntity<GeneralResponse> getAllTasks() {
        return buildResponse("Tasks retrieved", HttpStatus.OK, taskService.getAllTasks());
    }

    private ResponseEntity<GeneralResponse> buildResponse(String message, HttpStatus status, Object data) {
        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath();
        return ResponseEntity.status(status)
                .body(GeneralResponse.builder()
                        .uri(uri)
                        .message(message)
                        .status(status.value())
                        .time(LocalDateTime.now())
                        .data(data)
                        .build());
    }
}
