package com.da0hn.craft.system.core.domain.item;

import com.da0hn.craft.system.core.domain.shared.DomainValidationUtils;

public abstract sealed class Item permits CraftableItem, MaterialItem {

  protected final ItemId itemId;

  protected final String name;

  protected final String description;

  protected Item(final ItemId itemId, final String name, final String description) {
    this.itemId = DomainValidationUtils.assertNotNull(itemId, "Item id cannot be null");
    this.name = DomainValidationUtils.assertNotEmpty(name, "Item name cannot be empty");
    this.description = DomainValidationUtils.assertNotEmpty(description, "Item description cannot be empty");
  }

  public String name() {
    return this.name;
  }

  public ItemId itemId() {
    return this.itemId;
  }

  public String description() {
    return this.description;
  }

}
