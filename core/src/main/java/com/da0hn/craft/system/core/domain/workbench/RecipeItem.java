package com.da0hn.craft.system.core.domain.workbench;

import com.da0hn.craft.system.core.domain.item.ItemId;

public class RecipeItem {

  private final ItemId itemId;

  private final String name;

  public RecipeItem(final ItemId itemId, final String name) {
    this.itemId = itemId;
    this.name = name;
  }

  public static RecipeItem newRecipeItem(final ItemId itemId, final String name) {
    return new RecipeItem(itemId, name);
  }

  public ItemId itemId() {
    return this.itemId;
  }

  public String name() {
    return this.name;
  }

}
