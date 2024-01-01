package com.da0hn.craft.system.core.domain.workbench;

import com.da0hn.craft.system.core.domain.item.ItemId;

public class RecipeItem {

  private final RecipeItemId recipeItemId;

  private final ItemId itemId;

  private final ManufacturingMethod manufacturingMethod;

  public RecipeItem(
    final RecipeItemId recipeItemId,
    final ItemId itemId,
    final ManufacturingMethod manufacturingMethod
  ) {
    this.recipeItemId = recipeItemId;
    this.itemId = itemId;
    this.manufacturingMethod = manufacturingMethod;
  }

  public static RecipeItem newRecipeItem(final ItemId itemId, final ManufacturingMethod manufacturingMethod) {
    return new RecipeItem(RecipeItemId.newInstance(), itemId, manufacturingMethod);
  }

  public RecipeItemId recipeItemId() {
    return this.recipeItemId;
  }

  public ItemId itemId() {
    return this.itemId;
  }

  public ManufacturingMethod manufacturingMethod() {
    return this.manufacturingMethod;
  }

  @Override
  public int hashCode() {
    return this.recipeItemId.hashCode();
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (!(o instanceof final RecipeItem that)) return false;

    return this.recipeItemId.equals(that.recipeItemId);
  }

}
