package com.grupowl.breakfastproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupowl.breakfastproject.entities.User;

public interface UserRepository extends JpaRepository <User, Long> {

}