package com.example.demo.repository;

import com.example.demo.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorReponsitory extends JpaRepository<Actor, Integer> {
}
