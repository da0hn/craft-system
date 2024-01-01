package com.da0hn.craft.system.core.domain.workbench;

import com.da0hn.craft.system.core.domain.item.ItemId;

import java.util.Optional;

public class RelativeToPosition {

  private final ItemId itemId;

  private final Optional<Integer> relativeX;

  private final Optional<Integer> relativeY;

  public RelativeToPosition(final ItemId itemId, final Optional<Integer> relativeX, final Optional<Integer> relativeY) {
    this.itemId = itemId;
    this.relativeX = relativeX;
    this.relativeY = relativeY;
  }

  public static RelativeToPosition relativeTo(final ItemId recipeItemId, final Integer relativeX, final Integer relativeY) {
    return new RelativeToPosition(recipeItemId, Optional.ofNullable(relativeX), Optional.ofNullable(relativeY));
  }

  public ItemId itemId() {
    return this.itemId;
  }

  public Optional<Integer> maybeRelativeX() {
    return this.relativeX;
  }

  public Optional<Integer> maybeRelativeY() {
    return this.relativeY;
  }

}
