package com.silva.rodrigues.marcos.authjwt.dto;

import com.silva.rodrigues.marcos.authjwt.model.Task;
import org.hibernate.validator.constraints.Length;

public record TaskDto(
        Long id,
        String title,

        String description,
        Boolean isCompleted

) {

  public TaskDto(Task task) {
    this(task.getId(),  task.getTitle(), task.getDescription(), task.getIsCompleted());
  }

}
