package org.da0hn.recipe.core.domain;

import java.util.Objects;

import static org.da0hn.recipe.core.shared.RecipeMessages.ITEM_ID_NOT_NULL;
import static org.da0hn.recipe.core.shared.RecipeMessages.ITEM_NAME_NOT_EMPTY;
import static org.da0hn.recipe.core.shared.RecipeMessages.ITEM_NAME_NOT_NULL;
import static org.da0hn.recipe.core.shared.RecipeMessages.ITEM_QUANTITY_NEGATIVE;
import static org.da0hn.recipe.core.shared.RecipeMessages.ITEM_QUANTITY_NOT_NULL;
import static org.da0hn.recipe.core.shared.RecipeMessages.ITEM_TYPE_NOT_NULL;


public class Item {

  private final Long id;

  private final ItemType type;

  private final String name;

  private final Integer quantity;

  public Item(
    final Long id,
    final ItemType type,
    final String name,
    final Integer quantity
  ) {
    this.id = Objects.requireNonNull(id, ITEM_ID_NOT_NULL);
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

  public ItemType getType() {
    return this.type;
  }

  public Integer getQuantity() {
    return this.quantity;
  }

}
