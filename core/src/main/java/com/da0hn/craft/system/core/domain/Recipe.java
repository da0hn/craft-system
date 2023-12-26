package com.da0hn.craft.system.core.domain;

public class Recipe {

  private final RecipeId recipeId;

  private final WorkbenchId workbenchId;

  private final String name;

  private String description;

  public Recipe(final RecipeId recipeId, final String name, final String description, final WorkbenchId workbenchId) {
    this.recipeId = recipeId;
    this.name = name;
    this.description = description;
    this.workbenchId = workbenchId;
  }

  public static Recipe newRecipe(final String recipeName, final WorkbenchId workbenchId) {
    return new Recipe(RecipeId.newInstance(), recipeName, null, workbenchId);
  }

  public void changeRecipeDescription(final String newDescription) {
    this.description = newDescription;
  }

  public RecipeId recipeId() {
    return this.recipeId;
  }

  public String name() {
    return this.name;
  }

  public String description() {
    return this.description;
  }

  public WorkbenchId workbenchId() {
    return this.workbenchId;
  }

}
