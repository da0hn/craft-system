package org.da0hn.recipe.core.domain;

import org.da0hn.commons.core.domain.Identity;

import java.util.Objects;
import java.util.stream.Stream;

import static org.da0hn.recipe.core.domain.ItemType.FINAL;
import static org.da0hn.recipe.core.shared.RecipeMessages.*;


public class Recipe implements RecipeModel {

  private final Identity<Long> identity;

  private final ItemType type;

  private final Integer quantityProduced;

  private final String name;

  private final Items items = ItemContainer.empty();

  public Recipe(
    final Identity<Long> identity,
    final String name,
    final ItemType type,
    final Integer quantityProduced,
    final Items items
  ) {
    this.identity = Objects.requireNonNull(identity, RECIPE_ID_NOT_NULL);
    this.type = Objects.requireNonNull(type, RECIPE_TYPE_PRODUCED_NOT_NULL);
    this.quantityProduced = Objects.requireNonNull(quantityProduced,
                                                   RECIPE_QUANTITY_PRODUCED_NOT_NULL);
    this.name = Objects.requireNonNull(name, RECIPE_NAME_NOT_NULL);
    this.addAllItems(Objects.requireNonNull(items, RECIPE_ITEMS_NOT_NULL));
    this.ifNameIsEmptyThrowException();
    this.ifQuantityProducedLessThanOneThrowException();
  }

  private void addAllItems(final Items items) {
    if(items.isEmpty()) {
      throw new RecipeValidationException(RECIPE_ITEMS_NOT_EMPTY);
    }
    items.stream().forEach(this::addItem);
  }

  @Override
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

  @Override
  public Identity<Long> getIdentity() {
    return this.identity;
  }

  @Override
  public Stream<ItemModel> itemStream() {
    return this.items.stream();
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

  @Override
  public boolean hasItems() {
    return !this.items.isEmpty();
  }

}
