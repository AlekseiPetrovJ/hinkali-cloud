package ru.petrov.hinkalicloud.model;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.List;

@Data
@UserDefinedType("hinkali")
public class HinkaliUDT {

  private final String name;
  private final List<IngredientUDT> ingredients;

}