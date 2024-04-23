package com.silva.rodrigues.marcos.authjwt.controller;

import com.silva.rodrigues.marcos.authjwt.dto.CreateTaskDto;
import com.silva.rodrigues.marcos.authjwt.dto.TaskDto;
import com.silva.rodrigues.marcos.authjwt.model.User;
import com.silva.rodrigues.marcos.authjwt.model.UserDetailsImpl;
import com.silva.rodrigues.marcos.authjwt.service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

  private TaskService taskService;

  @GetMapping
  public ResponseEntity<Page<TaskDto>> getAllTask(@PageableDefault Pageable page, @AuthenticationPrincipal UserDetailsImpl principal) {
    var list = taskService.getAllByUser(principal.getUser(), page);
    return ResponseEntity.ok(list);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TaskDto> getSingleTask(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl principal) {
    var singleTask = taskService.get(id, principal.getUser());
    return ResponseEntity.ok(singleTask);
  }

  @PostMapping
  public ResponseEntity create( @RequestBody @Valid CreateTaskDto createTaskDto, @AuthenticationPrincipal UserDetailsImpl principal) {
    var taskDto = taskService.create(createTaskDto.title(), createTaskDto.description(), principal.getUser());

    return ResponseEntity.created(URI.create("/task/" + taskDto.id())).body(taskDto);
  }

  @PutMapping("/{id}/completed")
  public ResponseEntity complete(@PathVariable Long id,  @AuthenticationPrincipal UserDetailsImpl principal) {
    taskService.makeComplete(id, principal.getUser());

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
