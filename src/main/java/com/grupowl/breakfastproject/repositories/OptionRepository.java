package com.grupowl.breakfastproject.repositories;

import org.springframework.data.repository.CrudRepository;

import com.grupowl.breakfastproject.entities.Option;
import com.grupowl.breakfastproject.entities.pk.OptionPk;

public interface OptionRepository extends CrudRepository <Option, OptionPk> {

}