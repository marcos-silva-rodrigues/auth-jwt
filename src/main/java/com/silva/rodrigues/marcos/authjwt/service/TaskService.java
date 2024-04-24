package com.silva.rodrigues.marcos.authjwt.service;

import com.silva.rodrigues.marcos.authjwt.dto.TaskDto;
import com.silva.rodrigues.marcos.authjwt.exception.TaskNotFoundException;
import com.silva.rodrigues.marcos.authjwt.model.Task;
import com.silva.rodrigues.marcos.authjwt.model.User;
import com.silva.rodrigues.marcos.authjwt.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class TaskService {

  private TaskRepository repository;

  public TaskDto create(String title, String descrition, User user) {
    var task = new Task(title, descrition, user);

    var taskPersisted = repository.save(task);
    return new TaskDto(taskPersisted);
  }

  public TaskDto get(Long id, User user) throws TaskNotFoundException {
    var task = getTask(id, user);

    return new TaskDto(task);
  }



  public Page<TaskDto> getAllByUser(User user, Pageable page) {
    return repository
            .findAllByUserId(user.getId(), page)
            .map(TaskDto::new);
  }

  public void makeComplete(Long id, User user) throws TaskNotFoundException {
    var task = getTask(id, user);
    task.makeComplete();
    repository.save(task);
  }


  private Task getTask(Long id, User user) throws TaskNotFoundException {
    return repository.findByIdAndUserId(user.getId(), id)
            .orElseThrow(() -> new TaskNotFoundException(id));
  }

}
