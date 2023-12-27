package com.da0hn.craft.system.core.domain.command;

import com.da0hn.craft.system.core.domain.Item;

import java.util.Set;

public record AddRecipeCommand(String recipeName, Set<Item> items) {
}
