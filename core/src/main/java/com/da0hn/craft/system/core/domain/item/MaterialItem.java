package com.da0hn.craft.system.core.domain.item;

import com.da0hn.craft.system.core.domain.item.Item;
import com.da0hn.craft.system.core.domain.item.ItemId;

public final class MaterialItem extends Item {

  private MaterialItem(final ItemId itemId, final String name, final String description) {
    super(itemId, name, description);
  }

}
