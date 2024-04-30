package ru.petrov.hinkalicloud.repository;

import org.springframework.data.repository.CrudRepository;
import ru.petrov.hinkalicloud.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
