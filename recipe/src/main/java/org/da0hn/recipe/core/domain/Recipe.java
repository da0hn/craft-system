package org.da0hn.recipe.core.domain;

import org.da0hn.recipe.core.shared.RecipeMessages;

import java.util.Objects;

import static org.da0hn.recipe.core.shared.RecipeMessages.RECIPE_ID_NOT_NULL;
import static org.da0hn.recipe.core.shared.RecipeMessages.RECIPE_NAME_NOT_EMPTY;
import static org.da0hn.recipe.core.shared.RecipeMessages.RECIPE_NAME_NOT_NULL;
import static org.da0hn.recipe.core.shared.RecipeMessages.RECIPE_QUANTITY_PRODUCED_NOT_NULL;
import static org.da0hn.recipe.core.shared.RecipeMessages.RECIPE_TYPE_PRODUCED_NOT_NULL;


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
    this.id = Objects.requireNonNull(id, RECIPE_ID_NOT_NULL);
    this.type = Objects.requireNonNull(type, RECIPE_TYPE_PRODUCED_NOT_NULL);
    this.quantityProduced = Objects.requireNonNull(quantityProduced, RECIPE_QUANTITY_PRODUCED_NOT_NULL);
    this.name = Objects.requireNonNull(name, RECIPE_NAME_NOT_NULL);
    this.items = ItemContainer.empty();
    this.ifNameIsEmptyThrowException();
    this.ifQuantityProducedLessThanOneThrowException();
  }

  private void ifQuantityProducedLessThanOneThrowException() {
    if(this.quantityProduced < 1) {
      throw new RecipeValidationException(RecipeMessages.RECIPE_QUANTITY_PRODUCED_LESS_THAN_ONE);
    }
  }

  private void ifNameIsEmptyThrowException() {
    if(this.name.isEmpty() || this.name.isBlank()) {
      throw new RecipeValidationException(RECIPE_NAME_NOT_EMPTY);
    }
  }

  public boolean hasConstraints() {
    return !this.items.isEmpty();
  }

  public void add(final Item item) {
  }

}
