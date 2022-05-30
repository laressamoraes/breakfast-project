package com.grupowl.breakfastproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupowl.breakfastproject.entities.Item;

public interface ItemRepository extends JpaRepository <Item, Long> {

}