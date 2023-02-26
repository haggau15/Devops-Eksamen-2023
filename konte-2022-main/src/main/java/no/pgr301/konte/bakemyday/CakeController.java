package no.pgr301.konte.bakemyday;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class CakeController {

    private List<Ingredient> ingredients = new ArrayList<>();

    public CakeController() {
        ingredients.add(new Ingredient("flour", 0.5, 1.0));
        ingredients.add(new Ingredient("sugar", 0.25, 0.75));
        ingredients.add(new Ingredient("butter", 0.125, 0.5));
        ingredients.add(new Ingredient("eggs", 1, 2));
        ingredients.add(new Ingredient("baking powder", 0.05, 0.1));
        ingredients.add(new Ingredient("milk", 0.5, 1.0));
        ingredients.add(new Ingredient("vanilla extract", 0.05, 0.1));
        ingredients.add(new Ingredient("salt", 0.01, 0.05));
    }

    @GetMapping("/cake-ingredients")
        public List<Ingredient> getCakeIngredients(@RequestParam int numberOfIngredients) {
        Random random = new Random();
        List<Ingredient> selectedIngredients = new ArrayList<>();
        for (int i = 0; i < numberOfIngredients; i++) {
            int index = random.nextInt(ingredients.size());
            Ingredient ingredient = ingredients.get(index);
            double weight = ingredient.getMinWeight() + (ingredient.getMaxWeight() - ingredient.getMinWeight()) * random.nextDouble();
            selectedIngredients.add(new Ingredient(ingredient.getName(), weight));
        }
        return selectedIngredients;
    }

    static class Ingredient {
        private String name;
        private double weight;
        private double minWeight;
        private double maxWeight;

        public Ingredient(String name, double weight) {
            this.name = name;
            this.weight = weight;
        }

        public Ingredient(String name, double minWeight, double maxWeight) {
            this.name = name;
            this.minWeight = minWeight;
            this.maxWeight = maxWeight;
        }

        public String getName() {
            return name;
        }

        public double getWeight() {
            return weight;
        }

        public double getMinWeight() {
            return minWeight;
        }

        public double getMaxWeight() {
            return maxWeight;
        }
    }
}
