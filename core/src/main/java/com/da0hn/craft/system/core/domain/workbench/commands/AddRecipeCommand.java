package com.da0hn.craft.system.core.domain.workbench.commands;

import com.da0hn.craft.system.core.domain.item.ItemId;
import com.da0hn.craft.system.core.domain.workbench.RecipeItem;

import java.util.List;

public record AddRecipeCommand(String recipeName, List<RecipeItem> items, ItemId craftedItemId) {
}
