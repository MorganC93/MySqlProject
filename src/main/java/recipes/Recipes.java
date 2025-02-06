package recipes;

import java.sql.Connection;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import recipe.Exception.DbException;
import recipes.dao.DbConnection;
import recipes.entity.recipe;
import recipes.sevice.RecipeService;

public class Recipes {
	private Scanner scanner = new Scanner(System.in);
	private RecipeService recipeservice = new RecipeService();

	// @formatter:off
	private List<String> operations = List.of(
			"1) Create and populate all tables", 
			"2) Add a recipe",
			"3) List recipes",
			"4) Select working recipe"
			);
	private Object curRecipe;
	// formatter:on
	
	public static void main(String[] args) {
		DbConnection.getconnection();

		new Recipes().displayMenu();

	}

	private void displayMenu() {
		boolean done = false;

		while (!done) {
			int operation = getOperation();

			try {
				switch (operation) {
				case -1:
					done = exitMenu();
					break;

				case 1:
					createTables();
					break;

				case 2:
					addRecipe();
					break;

				case 3:
					listRecipes();
					break;
					
				case 4:
					setCurrentRecipe();
					break;

				default:
					System.out.println("\n" + operation + "is not valid. Try again.");
					break;

				}
			} catch (Exception e) {
				System.out.println("\nError: " + e.toString() + "Try again.");
			}
		}
	}

	private void setCurrentRecipe() {
		List<recipe> recipes = listRecipes();
		
		Integer recipeId = getIntInput("Select a recipe ID");
		
		curRecipe = null;
		
		for(recipe recipe : recipes) {
			if(recipe.getRecipeId().equals(recipeId));
			recipe = recipeservice.fetchRecipeById(recipeId);
			break;
		}
		
		if(Objects.isNull(curRecipe)) {
			System.out.println("/nInvalid recipe selected"); 
		}
	}

	private List<recipe> listRecipes() {
		List<recipe> recipes = recipeservice.fetchRecipes();

		System.out.println("\nRecipes:");

		recipes.forEach(recipe -> System.out.println(
				"   " + recipe.getRecipeId() + ": " + recipe.getRecipeName()));
		
		return recipes; 
	}

	private void addRecipe() {
		String name = getStringInput("Enter the recipe name");
		String notes = getStringInput("Enter the recipe notes");
		Integer numServings = getIntInput("Enter number of servings");
		Integer prepMinutes = getIntInput("Enter prep time in minutes");
		Integer cookMinutes = getIntInput("Enter cook time in minutes");

		LocalTime pretime = minutesToLocalTime(prepMinutes);
		LocalTime cooktime = minutesToLocalTime(cookMinutes);

		recipe recipe = new recipe();

		recipe.setRecipeName(name);
		recipe.setNotes(notes);
		recipe.setNumServings(numServings);
		recipe.setPrepTime(pretime);
		recipe.setCookTime(cooktime);

		recipe dbRecipe = RecipeService.addRecipe(recipe);
		System.out.println("you added this recipe:\n" + dbRecipe);
		
		curRecipe = recipeservice.fetchRecipeById(dbRecipe.getRecipeId());
	} 

	private LocalTime minutesToLocalTime(Integer numMinutes) {
		int min = Objects.isNull(numMinutes) ? 0 : numMinutes;
		int hours = min / 60;
		int mintues = min % 60;

		return LocalTime.of(hours, min);
	}

	private void createTables() {
		recipeservice.createAndPopulateTables();
		System.out.println("\nTables created and populated!");
	}

	private boolean exitMenu() {
		System.out.println("\nExiting the menu. TTFN!");
		return true;
	}

	private int getOperation() {
		printOperations();
		Integer op = getIntInput("\nEnter an operation number (press enter to quit)");

		return Objects.isNull(op) ? -1 : op;
	}

	private void printOperations() {
		System.out.println();
		System.out.println("Here is what you can do:");

		operations.forEach(op -> System.out.println("   " + op));
	}

	private Double getDoubleInput(String prompt) {
		String input = getStringInput(prompt);

		if (Objects.isNull(input)) {
			return null;
		}
		try {
			return Double.parseDouble(input);
		} catch (NumberFormatException e) {
			throw new DbException(input + " is not a valid number.");
		}
	}

	private Integer getIntInput(String prompt) {
		String input = getStringInput(prompt);

		if (Objects.isNull(input)) {
			return null;
		}
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			throw new DbException(input + " is not a valid number.");
		}
	}

	private String getStringInput(String prompt) {
		System.out.println(prompt + ": ");
		String line = scanner.nextLine();

		return line.isBlank() ? null : line.trim();
	}
}
