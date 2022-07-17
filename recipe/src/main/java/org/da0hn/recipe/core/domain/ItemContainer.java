package org.da0hn.recipe.core.domain;

import org.da0hn.recipe.core.shared.RecipeMessages;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;


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

  @Override public Stream<Item> stream() {
    return this.items.stream();
  }

}
