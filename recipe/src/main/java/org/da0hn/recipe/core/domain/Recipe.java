package org.da0hn.recipe.core.domain;

import java.util.Objects;


public class Recipe {

  private final Long id;

  private final ItemType type;

  private final Integer quantityProduced;

  private final String name;

  private final Items items;

  public Recipe(
    final Long id,
    final String name,
    final ItemType type,
    final Integer quantityProduced
  ) {
    this.id = Objects.requireNonNull(id, "Recipe id must be not null");
    this.type = Objects.requireNonNull(type, "Recipe produced item type must be not null");
    this.quantityProduced = Objects.requireNonNull(quantityProduced, "Recipe quantity produced must be not null");
    this.name = Objects.requireNonNull(name, "Recipe name must be not null");
    this.items = ItemContainer.empty();
    this.ifNameIsEmptyThrowException();
    this.ifQuantityProducedLessThanOneThrowException();
  }

  private void ifQuantityProducedLessThanOneThrowException() {
    if(this.quantityProduced < 1) {
      throw new RecipeValidationException("Recipe quantity produced must be greater than one");
    }
  }

  private void ifNameIsEmptyThrowException() {
    if(this.name.isEmpty() || this.name.isBlank()) {
      throw new RecipeValidationException("Recipe name must be not empty");
    }
  }

  public boolean hasConstraints() {
    return !this.items.isEmpty();
  }

}
