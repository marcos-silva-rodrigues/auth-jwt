package com.silva.rodrigues.marcos.authjwt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 120)
  private String title;

  @Column
  private String description;

  @Column
  private Boolean isCompleted;

  @ManyToOne
  private User user;

  public Task(String title, String description, User user) {
    this.title = title;
    this.description = description;
    this.user = user;
    this.isCompleted = false;
  }

  public void makeComplete() {
    if (!isCompleted) {
      this.setIsCompleted(true);
    }
  }
}
