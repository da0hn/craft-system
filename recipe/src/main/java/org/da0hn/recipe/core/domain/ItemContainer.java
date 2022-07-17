package org.da0hn.recipe.core.domain;

import org.da0hn.recipe.core.shared.RecipeMessages;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;


public final class ItemContainer implements Items {

  private final Collection<ItemModel> items = new LinkedHashSet<>();

  private ItemContainer() {
  }

  private ItemContainer(final Collection<? extends ItemModel> items) {
    this.items.addAll(items);
  }

  public static Items empty() {
    return new ItemContainer();
  }

  public static Items of(final Collection<? extends ItemModel> items) {
    return new ItemContainer(items);
  }

  public static Items of(final ItemModel... items) {
    return new ItemContainer(List.of(items));
  }

  @Override
  public boolean isEmpty() {
    return this.items.isEmpty();
  }

  @Override
  public void add(final ItemModel item) {
    this.items.add(Objects.requireNonNull(item, RecipeMessages.ITEM_NOT_NULL));
  }

  @Override
  public Stream<ItemModel> stream() {
    return this.items.stream();
  }

}
