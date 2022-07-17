package org.da0hn.recipe.core.domain;

import org.da0hn.commons.core.domain.Identity;

import java.util.Objects;
import java.util.stream.Stream;

import static org.da0hn.recipe.core.domain.ItemType.FINAL;
import static org.da0hn.recipe.core.shared.RecipeMessages.RECIPE_ID_NOT_NULL;
import static org.da0hn.recipe.core.shared.RecipeMessages.RECIPE_ITEM_QUANTITY_LESS_THAN_ONE;
import static org.da0hn.recipe.core.shared.RecipeMessages.RECIPE_ITEM_TYPE_NOT_FINAL;
import static org.da0hn.recipe.core.shared.RecipeMessages.RECIPE_NAME_NOT_EMPTY;
import static org.da0hn.recipe.core.shared.RecipeMessages.RECIPE_NAME_NOT_NULL;
import static org.da0hn.recipe.core.shared.RecipeMessages.RECIPE_QUANTITY_PRODUCED_LESS_THAN_ONE;
import static org.da0hn.recipe.core.shared.RecipeMessages.RECIPE_QUANTITY_PRODUCED_NOT_NULL;
import static org.da0hn.recipe.core.shared.RecipeMessages.RECIPE_TYPE_PRODUCED_NOT_NULL;


public class Recipe {

  private final Identity<Long> identity;

  private final ItemType type;

  private final Integer quantityProduced;

  private final String name;

  private final Items items;

  public Recipe(
    final Identity<Long> identity,
    final String name,
    final ItemType type,
    final Integer quantityProduced
  ) {
    this.identity = Objects.requireNonNull(identity, RECIPE_ID_NOT_NULL);
    this.type = Objects.requireNonNull(type, RECIPE_TYPE_PRODUCED_NOT_NULL);
    this.quantityProduced = Objects.requireNonNull(quantityProduced, RECIPE_QUANTITY_PRODUCED_NOT_NULL);
    this.name = Objects.requireNonNull(name, RECIPE_NAME_NOT_NULL);
    this.items = ItemContainer.empty();
    this.ifNameIsEmptyThrowException();
    this.ifQuantityProducedLessThanOneThrowException();
  }

  private void ifQuantityProducedLessThanOneThrowException() {
    if(this.quantityProduced < 1) {
      throw new RecipeValidationException(RECIPE_QUANTITY_PRODUCED_LESS_THAN_ONE);
    }
  }

  private void ifNameIsEmptyThrowException() {
    if(this.name.isEmpty() || this.name.isBlank()) {
      throw new RecipeValidationException(RECIPE_NAME_NOT_EMPTY);
    }
  }

  public boolean hasItems() {
    return !this.items.isEmpty();
  }

  public void addItem(final ItemModel item) {
    ifItemRecipeNotValidThrowException(item);
    this.items.add(item);
  }

  private static void ifItemRecipeNotValidThrowException(final ItemModel item) {
    if(item.getType() == FINAL) {
      throw new RecipeValidationException(RECIPE_ITEM_TYPE_NOT_FINAL);
    }
    if(item.getQuantity() < 1) {
      throw new RecipeValidationException(RECIPE_ITEM_QUANTITY_LESS_THAN_ONE);
    }
  }

  public Identity<Long> getIdentity() {
    return this.identity;
  }

  public Stream<ItemModel> itemsStream() {
    return this.items.stream();
  }

}
