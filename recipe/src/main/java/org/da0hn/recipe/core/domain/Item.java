package org.da0hn.recipe.core.domain;

import org.da0hn.commons.core.domain.Identity;

import java.util.Objects;

import static org.da0hn.recipe.core.shared.RecipeMessages.ITEM_ID_NOT_NULL;
import static org.da0hn.recipe.core.shared.RecipeMessages.ITEM_NAME_NOT_EMPTY;
import static org.da0hn.recipe.core.shared.RecipeMessages.ITEM_NAME_NOT_NULL;
import static org.da0hn.recipe.core.shared.RecipeMessages.ITEM_QUANTITY_NEGATIVE;
import static org.da0hn.recipe.core.shared.RecipeMessages.ITEM_QUANTITY_NOT_NULL;
import static org.da0hn.recipe.core.shared.RecipeMessages.ITEM_TYPE_NOT_NULL;


public class Item implements ItemModel {

  private final Identity<Long> identity;

  private final ItemType type;

  private final String name;

  private final Integer quantity;

  public Item(
    final Identity<Long> identity,
    final ItemType type,
    final String name,
    final Integer quantity
  ) {
    this.identity = Objects.requireNonNull(identity, ITEM_ID_NOT_NULL);
    this.type = Objects.requireNonNull(type, ITEM_TYPE_NOT_NULL);
    this.name = Objects.requireNonNull(name, ITEM_NAME_NOT_NULL);
    this.quantity = Objects.requireNonNull(quantity, ITEM_QUANTITY_NOT_NULL);
    this.ifNameIsEmptyThrowException();
    this.ifQuantityIsNegativeThrowException();
  }

  private void ifQuantityIsNegativeThrowException() {
    if(this.quantity < 0) {
      throw new RecipeValidationException(ITEM_QUANTITY_NEGATIVE);
    }
  }

  private void ifNameIsEmptyThrowException() {
    if(this.name.isEmpty() || this.name.isBlank()) {
      throw new RecipeValidationException(ITEM_NAME_NOT_EMPTY);
    }
  }

  @Override public ItemType getType() {
    return this.type;
  }

  @Override public Integer getQuantity() {
    return this.quantity;
  }

  @Override public Identity<Long> getIdentity() {
    return this.identity;
  }

}
