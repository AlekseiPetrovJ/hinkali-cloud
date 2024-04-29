package ru.petrov.hinkalicloud.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.petrov.hinkalicloud.model.Ingredient;

import java.util.HashMap;
import java.util.Map;

import static ru.petrov.hinkalicloud.model.Ingredient.*;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    private Map<String, Ingredient> ingredientMap = new HashMap<>();

    public IngredientByIdConverter() {
        ingredientMap.put("FLDO",
        new Ingredient("FLDO", "Flour Вough", Type.WRAP));
        ingredientMap.put("CODO",
                new Ingredient("CODO", "Corn Вough", Type.WRAP));
        ingredientMap.put("GRBF",
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
        ingredientMap.put("GRCN",
                new Ingredient("GRCN", "Ground Chiken", Type.PROTEIN));
        ingredientMap.put("TMTO",
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
        ingredientMap.put("ONIN",
                new Ingredient("ONIN", "Onion", Type.VEGGIES));
        ingredientMap.put("CHED",
                new Ingredient("CHED", "Cheddar", Type.CHEESE));
        ingredientMap.put("CTCH",
                new Ingredient("CTCH", "Сottage Сheese", Type.CHEESE));
        ingredientMap.put("KTCH",
                new Ingredient("KTCH", "Ketchup", Type.SAUCE));
        ingredientMap.put("SRCR",
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
    }
    @Override
    public Ingredient convert(String id) {
        return ingredientMap.get(id);
    }

}
