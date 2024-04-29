package ru.petrov.hinkalicloud.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.petrov.hinkalicloud.model.Hinkali;
import ru.petrov.hinkalicloud.model.Ingredient;
import ru.petrov.hinkalicloud.model.Order;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.petrov.hinkalicloud.model.Ingredient.*;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignHinkaliController {
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLDO", "Flour Вough", Type.WRAP),
                new Ingredient("CODO", "Corn Вough", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("GRCN", "Ground Chiken", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("ONIN", "Onion", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("CTCH", "Сottage Сheese", Type.CHEESE),
                new Ingredient("KTCH", "Ketchup", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );
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
        return "design";
    }
    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
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
