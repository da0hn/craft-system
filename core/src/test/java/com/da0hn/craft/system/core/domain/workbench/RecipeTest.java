package com.da0hn.craft.system.core.domain.workbench;

import com.da0hn.craft.system.core.domain.item.ItemId;
import com.da0hn.craft.system.core.domain.workbench.commands.AddRecipeCommand;
import com.da0hn.craft.system.core.domain.workbench.commands.CreateWorkbenchCommand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;

class RecipeTest {

  @Test
  @DisplayName("Should add a new recipe to Workbench with any position manufacturing method")
  void test1() {
    final var workbench = Workbench.newWorkbench(new CreateWorkbenchCommand("Workbench", 3, 3));

    final var stoneSwordId = ItemId.newInstance();
    final var stoneId = ItemId.newInstance();
    final var stickId = ItemId.newInstance();

    final var stick = RecipeItem.newRecipeItem(stickId, ManufacturingMethod.anyPosition());
    final var stone1 = RecipeItem.newRecipeItem(stoneId, ManufacturingMethod.anyPosition());
    final var stone2 = RecipeItem.newRecipeItem(stoneId, ManufacturingMethod.anyPosition());

    final var stoneSwordRecipe = workbench.addRecipe(
      new AddRecipeCommand(
        "Stone Sword",
        List.of(stick, stone1, stone2),
        stoneSwordId
      )
    );

    Assertions.assertThat(stoneSwordRecipe.recipeId()).isNotNull();
    Assertions.assertThat(stoneSwordRecipe.workbenchId()).isEqualTo(workbench.workbenchId());
    Assertions.assertThat(stoneSwordRecipe.name()).isEqualTo("Stone Sword");
    Assertions.assertThat(stoneSwordRecipe.description()).isNull();
    Assertions.assertThat(stoneSwordRecipe.craftedItemId()).isEqualTo(stoneSwordId);
    Assertions.assertThat(stoneSwordRecipe.recipeItems()).hasSize(3);
    Assertions.assertThat(stoneSwordRecipe.recipeItems()).extracting(RecipeItem::itemId)
      .containsExactlyInAnyOrder(stickId, stoneId, stoneId);
    Assertions.assertThat(stoneSwordRecipe.recipeItems()).extracting(RecipeItem::manufacturingMethod)
      .containsExactlyInAnyOrder(
        ManufacturingMethod.anyPosition(),
        ManufacturingMethod.anyPosition(),
        ManufacturingMethod.anyPosition()
      );
  }

  @Test
  @DisplayName("Should add a new recipe to Workbench with relative position manufacturing method")
  void test2() {
    final var stickId = ItemId.newInstance();
    final var stoneId = ItemId.newInstance();
    final var stoneSwordId = ItemId.newInstance();
    final var stoneSword = "Stone Sword";

    final var upperStone = RecipeItem.newRecipeItem(
      stoneId,
      ManufacturingMethod.relativePosition(
        Set.of(RelativeToPosition.relativeTo(stoneId, null, -1))
      )
    );
    final var middleStone = RecipeItem.newRecipeItem(
      stoneId,
      ManufacturingMethod.relativePosition(
        Set.of(
          RelativeToPosition.relativeTo(stoneId, null, 1),
          RelativeToPosition.relativeTo(stickId, null, -1)
        )
      )
    );
    final var stick = RecipeItem.newRecipeItem(
      stickId,
      ManufacturingMethod.relativePosition(
        Set.of(RelativeToPosition.relativeTo(stoneId, null, 1))
      )
    );

    final var workbench = Workbench.newWorkbench(new CreateWorkbenchCommand("Workbench", 3, 3));
    final var stoneSwordRecipe = workbench.addRecipe(
      new AddRecipeCommand(
        stoneSword,
        List.of(stick, middleStone, upperStone),
        stoneSwordId
      )
    );

    // Recipe assertions
    Assertions.assertThat(workbench.recipes()).hasSize(1);
    Assertions.assertThat(stoneSwordRecipe.recipeId()).isNotNull();
    Assertions.assertThat(stoneSwordRecipe.workbenchId()).isEqualTo(workbench.workbenchId());
    Assertions.assertThat(stoneSwordRecipe.craftedItemId()).isEqualTo(stoneSwordId);
    Assertions.assertThat(stoneSwordRecipe.name()).isEqualTo(stoneSword);
    Assertions.assertThat(stoneSwordRecipe.description()).isNull();
    // Recipe statistics assertions
    Assertions.assertThat(stoneSwordRecipe.statistics()).isNotNull();
    Assertions.assertThat(stoneSwordRecipe.statistics().items()).hasSize(2);
    Assertions.assertThat(stoneSwordRecipe.statistics().items()).extracting(RecipeStatistics.Item::itemId)
      .containsExactlyInAnyOrder(stickId, stoneId);
    Assertions.assertThat(stoneSwordRecipe.statistics().items()).extracting(RecipeStatistics.Item::quantity)
      .containsExactlyInAnyOrder(1, 2);
    Assertions.assertThat(stoneSwordRecipe.statistics().items()).extracting(RecipeStatistics.Item::recipeItemsId)
      .containsExactlyInAnyOrder(Set.of(stick.recipeItemId()), Set.of(middleStone.recipeItemId(), upperStone.recipeItemId()));
    // Stick assertions
    Assertions.assertThat(stick.recipeItemId()).isNotNull();
    Assertions.assertThat(stick.itemId()).isEqualTo(stickId);
    Assertions.assertThat(stick.manufacturingMethod()).isInstanceOf(ManufacturingMethod.RelativePositionMethod.class);
    Assertions.assertThat(((ManufacturingMethod.RelativePositionMethod) stick.manufacturingMethod()).relativeTo())
      .hasSize(1);
    Assertions.assertThat(((ManufacturingMethod.RelativePositionMethod) stick.manufacturingMethod()).relativeTo())
      .extracting(RelativeToPosition::itemId)
      .containsExactlyInAnyOrder(stoneId);
    Assertions.assertThat(((ManufacturingMethod.RelativePositionMethod) stick.manufacturingMethod()).relativeTo())
      .extracting(RelativeToPosition::maybeRelativeX)
      .containsExactlyInAnyOrder(Optional.empty());
    Assertions.assertThat(((ManufacturingMethod.RelativePositionMethod) stick.manufacturingMethod()).relativeTo())
      .extracting(RelativeToPosition::maybeRelativeY)
      .containsExactlyInAnyOrder(Optional.of(1));
    // Stone at (1, 1) assertions
    Assertions.assertThat(middleStone.recipeItemId()).isNotNull();
    Assertions.assertThat(middleStone.itemId()).isEqualTo(stoneId);
    Assertions.assertThat(middleStone.manufacturingMethod()).isInstanceOf(ManufacturingMethod.RelativePositionMethod.class);
    Assertions.assertThat(((ManufacturingMethod.RelativePositionMethod) middleStone.manufacturingMethod()).relativeTo())
      .hasSize(2);
    Assertions.assertThat(((ManufacturingMethod.RelativePositionMethod) middleStone.manufacturingMethod()).relativeTo())
      .extracting(RelativeToPosition::itemId)
      .containsExactlyInAnyOrder(stoneId, stickId);
    Assertions.assertThat(((ManufacturingMethod.RelativePositionMethod) middleStone.manufacturingMethod()).relativeTo())
      .extracting(RelativeToPosition::maybeRelativeX)
      .containsExactlyInAnyOrder(Optional.empty(), Optional.empty());
    Assertions.assertThat(((ManufacturingMethod.RelativePositionMethod) middleStone.manufacturingMethod()).relativeTo())
      .extracting(RelativeToPosition::maybeRelativeY)
      .containsExactlyInAnyOrder(Optional.of(1), Optional.of(-1));
    // Stone at (0, 2) assertions
    Assertions.assertThat(upperStone.recipeItemId()).isNotNull();
    Assertions.assertThat(upperStone.itemId()).isEqualTo(stoneId);
    Assertions.assertThat(upperStone.manufacturingMethod()).isInstanceOf(ManufacturingMethod.RelativePositionMethod.class);
    Assertions.assertThat(((ManufacturingMethod.RelativePositionMethod) upperStone.manufacturingMethod()).relativeTo())
      .hasSize(1);
    Assertions.assertThat(((ManufacturingMethod.RelativePositionMethod) upperStone.manufacturingMethod()).relativeTo())
      .extracting(RelativeToPosition::itemId)
      .containsExactlyInAnyOrder(stoneId);
    Assertions.assertThat(((ManufacturingMethod.RelativePositionMethod) upperStone.manufacturingMethod()).relativeTo())
      .extracting(RelativeToPosition::maybeRelativeX)
      .containsExactlyInAnyOrder(Optional.empty());
    Assertions.assertThat(((ManufacturingMethod.RelativePositionMethod) upperStone.manufacturingMethod()).relativeTo())
      .extracting(RelativeToPosition::maybeRelativeY)
      .containsExactlyInAnyOrder(Optional.of(-1));
  }

  @Test
  @DisplayName("Should create a recipe item with any position")
  void test3() {
    final var oakLogItemId = ItemId.newInstance();

    final var oakLog = RecipeItem.newRecipeItem(oakLogItemId, ManufacturingMethod.anyPosition());

    Assertions.assertThat(oakLog.itemId()).isEqualTo(oakLogItemId);
    Assertions.assertThat(oakLog.manufacturingMethod()).isInstanceOf(ManufacturingMethod.AnyPositionMethod.class);
  }

  @Test
  @DisplayName("Should create a recipe item with relative position")
  void test4() {
    final var stickId = ItemId.newInstance();
    final var stoneId = ItemId.newInstance();

    final var upperStone = RecipeItem.newRecipeItem(
      stoneId,
      ManufacturingMethod.relativePosition(
        Set.of(RelativeToPosition.relativeTo(stoneId, null, -1))
      )
    );
    final var middleStone = RecipeItem.newRecipeItem(
      stoneId,
      ManufacturingMethod.relativePosition(
        Set.of(
          RelativeToPosition.relativeTo(stoneId, null, 1),
          RelativeToPosition.relativeTo(stickId, null, -1)
        )
      )
    );
    final var stick = RecipeItem.newRecipeItem(
      stickId,
      ManufacturingMethod.relativePosition(
        Set.of(RelativeToPosition.relativeTo(stoneId, null, 1))
      )
    );

    // Stick assertions
    Assertions.assertThat(stick.recipeItemId()).isNotNull();
    Assertions.assertThat(stick.itemId()).isEqualTo(stickId);
    Assertions.assertThat(stick.manufacturingMethod()).isInstanceOf(ManufacturingMethod.RelativePositionMethod.class);
    Assertions.assertThat(((ManufacturingMethod.RelativePositionMethod) stick.manufacturingMethod()).relativeTo())
      .hasSize(1);
    Assertions.assertThat(((ManufacturingMethod.RelativePositionMethod) stick.manufacturingMethod()).relativeTo())
      .extracting(RelativeToPosition::itemId)
      .containsExactlyInAnyOrder(stoneId);
    Assertions.assertThat(((ManufacturingMethod.RelativePositionMethod) stick.manufacturingMethod()).relativeTo())
      .extracting(RelativeToPosition::maybeRelativeX)
      .containsExactlyInAnyOrder(Optional.empty());
    Assertions.assertThat(((ManufacturingMethod.RelativePositionMethod) stick.manufacturingMethod()).relativeTo())
      .extracting(RelativeToPosition::maybeRelativeY)
      .containsExactlyInAnyOrder(Optional.of(1));
    // Stone at (1, 1) assertions
    Assertions.assertThat(middleStone.recipeItemId()).isNotNull();
    Assertions.assertThat(middleStone.itemId()).isEqualTo(stoneId);
    Assertions.assertThat(middleStone.manufacturingMethod()).isInstanceOf(ManufacturingMethod.RelativePositionMethod.class);
    Assertions.assertThat(((ManufacturingMethod.RelativePositionMethod) middleStone.manufacturingMethod()).relativeTo())
      .hasSize(2);
    Assertions.assertThat(((ManufacturingMethod.RelativePositionMethod) middleStone.manufacturingMethod()).relativeTo())
      .extracting(RelativeToPosition::itemId)
      .containsExactlyInAnyOrder(stoneId, stickId);
    Assertions.assertThat(((ManufacturingMethod.RelativePositionMethod) middleStone.manufacturingMethod()).relativeTo())
      .extracting(RelativeToPosition::maybeRelativeX)
      .containsExactlyInAnyOrder(Optional.empty(), Optional.empty());
    Assertions.assertThat(((ManufacturingMethod.RelativePositionMethod) middleStone.manufacturingMethod()).relativeTo())
      .extracting(RelativeToPosition::maybeRelativeY)
      .containsExactlyInAnyOrder(Optional.of(1), Optional.of(-1));
    // Stone at (0, 2) assertions
    Assertions.assertThat(upperStone.recipeItemId()).isNotNull();
    Assertions.assertThat(upperStone.itemId()).isEqualTo(stoneId);
    Assertions.assertThat(upperStone.manufacturingMethod()).isInstanceOf(ManufacturingMethod.RelativePositionMethod.class);
    Assertions.assertThat(((ManufacturingMethod.RelativePositionMethod) upperStone.manufacturingMethod()).relativeTo())
      .hasSize(1);
    Assertions.assertThat(((ManufacturingMethod.RelativePositionMethod) upperStone.manufacturingMethod()).relativeTo())
      .extracting(RelativeToPosition::itemId)
      .containsExactlyInAnyOrder(stoneId);
    Assertions.assertThat(((ManufacturingMethod.RelativePositionMethod) upperStone.manufacturingMethod()).relativeTo())
      .extracting(RelativeToPosition::maybeRelativeX)
      .containsExactlyInAnyOrder(Optional.empty());
    Assertions.assertThat(((ManufacturingMethod.RelativePositionMethod) upperStone.manufacturingMethod()).relativeTo())
      .extracting(RelativeToPosition::maybeRelativeY)
      .containsExactlyInAnyOrder(Optional.of(-1));
  }

}
