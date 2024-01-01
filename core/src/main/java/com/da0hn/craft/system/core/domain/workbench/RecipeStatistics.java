package com.da0hn.craft.system.core.domain.workbench;

import com.da0hn.craft.system.core.domain.item.ItemId;
import com.da0hn.craft.system.core.domain.shared.DomainValidationUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class RecipeStatistics {

  private final Set<Item> items;

  public RecipeStatistics(final Set<Item> items) { this.items = items; }

  public static RecipeStatistics evaluateRecipeStatistics(final Collection<? extends RecipeItem> items) {
    DomainValidationUtils.assertCollectionIsNotEmpty(items, "Recipe items must be not empty");
    final var itemIdGroupedByRecipeItemId = items.stream().collect(Collectors.groupingBy(
      RecipeItem::itemId,
      Collectors.mapping(RecipeItem::recipeItemId, Collectors.toSet())
    ));
    final var itemsGroupedByQuantity = items.stream().collect(Collectors.groupingBy(RecipeItem::itemId, Collectors.counting()));
    final var statisticItems = items.stream()
      .map(item -> {
        final var recipeItemsId = itemIdGroupedByRecipeItemId.get(item.itemId());
        final var quantity = itemsGroupedByQuantity.get(item.itemId()).intValue();
        return new Item(
          item.itemId(),
          quantity,
          recipeItemsId
        );
      })
      .collect(Collectors.toSet());
    return new RecipeStatistics(statisticItems);
  }

  public Set<Item> items() {
    return Collections.unmodifiableSet(this.items);
  }

  public record Item(
    ItemId itemId,
    Integer quantity,
    Set<RecipeItemId> recipeItemsId
  ) {

    public Item {
      DomainValidationUtils.assertCollectionIsNotEmpty(recipeItemsId, "Recipe item id must be not null");
      DomainValidationUtils.assertNotNull(itemId, "Item id must be not null");
      DomainValidationUtils.assertNotNull(quantity, "Quantity must be not null");
      DomainValidationUtils.isTrue(quantity > 0, "Quantity must be greater than 0");
    }

    @Override
    public boolean equals(final Object o) {
      if (this == o) return true;
      if (!(o instanceof final Item item)) return false;

      return this.itemId.equals(item.itemId);
    }

    @Override
    public int hashCode() {
      return this.itemId.hashCode();
    }

  }

}
