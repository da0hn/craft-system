package com.da0hn.craft.system.core.domain.workbench;

import com.da0hn.craft.system.core.domain.item.ItemId;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Recipe {

  private final RecipeId recipeId;

  private final WorkbenchId workbenchId;

  private final String name;

  private final ItemId craftedItemId;

  private final List<RecipeItem> recipeItems;

  private String description;

  public Recipe(
    final RecipeId recipeId,
    final WorkbenchId workbenchId,
    final String name,
    final ItemId craftedItemId,
    final String description,
    final List<RecipeItem> recipeItems
  ) {
    this.recipeId = recipeId;
    this.name = name;
    this.craftedItemId = craftedItemId;
    this.description = description;
    this.workbenchId = workbenchId;
    this.recipeItems = Objects.requireNonNull(recipeItems);
  }

  public static Recipe newRecipe(
    final String recipeName,
    final WorkbenchId workbenchId,
    final List<RecipeItem> items,
    final ItemId craftedItemId
  ) {
    return new Recipe(RecipeId.newInstance(), workbenchId, recipeName, craftedItemId, null, items);
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

  public List<RecipeItem> recipeItems() {
    return Collections.unmodifiableList(this.recipeItems);
  }

  public ItemId craftedItemId() {
    return this.craftedItemId;
  }

}
