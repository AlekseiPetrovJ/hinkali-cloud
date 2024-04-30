package ru.petrov.hinkalicloud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.petrov.hinkalicloud.model.Ingredient;
import ru.petrov.hinkalicloud.repository.IngredientRepository;

import static ru.petrov.hinkalicloud.model.Ingredient.Type;

@SpringBootApplication
public class HinkaliCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(HinkaliCloudApplication.class, args);
	}


	@Bean
	public CommandLineRunner dataLoader(IngredientRepository repo) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {

				repo.save(new Ingredient("FLDO", "Flour Вough", Type.WRAP));
				repo.save(new Ingredient("CODO", "Corn Вough", Type.WRAP));
				repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
				repo.save(new Ingredient("GRCN", "Ground Chiken", Type.PROTEIN));
				repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
				repo.save(new Ingredient("ONIN", "Onion", Type.VEGGIES));
				repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
				repo.save(new Ingredient("CTCH", "Сottage Сheese", Type.CHEESE));
				repo.save(new Ingredient("KTCH", "Ketchup", Type.SAUCE));
				repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
			}
		};
	}
}
