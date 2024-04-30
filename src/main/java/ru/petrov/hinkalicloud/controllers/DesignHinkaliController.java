package ru.petrov.hinkalicloud.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.petrov.hinkalicloud.model.Hinkali;
import ru.petrov.hinkalicloud.model.Ingredient;
import ru.petrov.hinkalicloud.model.Order;
import ru.petrov.hinkalicloud.repository.IngredientRepository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static ru.petrov.hinkalicloud.model.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignHinkaliController {
    private final IngredientRepository ingredientRepo;
    @Autowired
    public DesignHinkaliController(
            IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();
        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }
    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }
    @ModelAttribute(name = "hinkali")
    public Hinkali hinkali() {
        return new Hinkali();
    }
    @GetMapping
    public String showDesignForm() {
        log.info("Getted design form");
        return "design";
    }
    private Iterable<Ingredient> filterByType(
            Iterable<Ingredient> ingredients, Type type) {
        return StreamSupport.stream(ingredients.spliterator(), false)
                .filter(i -> i.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processHinkali(@Valid Hinkali hinkali, Errors errors,
                              @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "design";
        }
        order.addTaco(hinkali);
        log.info("Processing hinkali: {}", hinkali);
        return "redirect:/orders/current";
    }
}
