package com.da0hn.craft.system.core.domain.workbench;

import com.da0hn.craft.system.core.domain.item.ItemId;
import com.da0hn.craft.system.core.domain.workbench.RecipeItem;
import com.da0hn.craft.system.core.domain.workbench.Workbench;
import com.da0hn.craft.system.core.domain.workbench.commands.AddRecipeCommand;
import com.da0hn.craft.system.core.domain.workbench.commands.CreateWorkbenchCommand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class RecipeTest {

  @Test
  @DisplayName("Should add a new recipe to Workbench")
  void test1() {
    final var workbench = Workbench.newWorkbench(new CreateWorkbenchCommand("Workbench", 3, 3));
    final var stoneSwordRecipe = workbench.addRecipe(
      new AddRecipeCommand(
        "Stone Sword",
        List.of(
          RecipeItem.newRecipeItem(ItemId.newInstance(), "Stick"),
          RecipeItem.newRecipeItem(ItemId.newInstance(), "Stone"),
          RecipeItem.newRecipeItem(ItemId.newInstance(), "Stone")
        )
      )
    );

    Assertions.assertThat(stoneSwordRecipe.recipeId()).isNotNull();
    Assertions.assertThat(stoneSwordRecipe.workbenchId()).isEqualTo(workbench.workbenchId());
    Assertions.assertThat(stoneSwordRecipe.name()).isEqualTo("Stone Sword");
    Assertions.assertThat(stoneSwordRecipe.description()).isNull();
    Assertions.assertThat(stoneSwordRecipe.recipeItems()).hasSize(3);
  }

}
