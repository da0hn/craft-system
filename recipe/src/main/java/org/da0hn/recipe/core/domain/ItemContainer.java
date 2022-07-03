package org.da0hn.recipe.core.domain;

import org.da0hn.recipe.core.shared.RecipeMessages;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class ItemContainer implements Items {

  private final Set<Item> items = new HashSet<>();

  public static Items empty() {
    return new ItemContainer();
  }

  @Override public boolean isEmpty() {
    return this.items.isEmpty();
  }

  @Override public void add(final Item item) {
    this.items.add(Objects.requireNonNull(item, RecipeMessages.ITEM_NOT_NULL));
  }

}
