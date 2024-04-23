package com.silva.rodrigues.marcos.authjwt.repository;

import com.silva.rodrigues.marcos.authjwt.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

  @Query("select t from Task t where t.user.id = :userId")
  Page<Task> findAllByUserId(UUID userId, Pageable page);

  @Query("select t from Task t where t.user.id = :userId and t.id = :id")
  Optional<Task> findByIdAndUserId(UUID userId, Long id);
}
