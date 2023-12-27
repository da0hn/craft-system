package com.da0hn.craft.system.core.domain;

import com.da0hn.craft.system.core.domain.command.CreateWorkbenchCommand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WorkbenchTest {

  @BeforeEach
  void setUp() {
  }

  @Test
  @DisplayName("Should not create Workbench with less than 4 slots ( 2 x 2 )")
  void test1() {
    Assertions.assertThatThrownBy(() -> Workbench.newWorkbench(new CreateWorkbenchCommand("Wooden Workbench", 1, 1)))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("Workbench must have at least 4 slots");
  }

  @Test
  @DisplayName("Should not create Workbench with width different than height")
  void test2() {
    Assertions.assertThatThrownBy(() -> Workbench.newWorkbench(new CreateWorkbenchCommand("Wooden Workbench", 2, 3)))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("Workbench Width and Height must be equal");
  }

  @Test
  @DisplayName("Should create Workbench with 9 slots")
  void test3() {
    final var workbench = Workbench.newWorkbench(new CreateWorkbenchCommand("Iron Workbench", 3, 3));

    Assertions.assertThat(workbench.name()).isEqualTo("Iron Workbench");
    Assertions.assertThat(workbench.workbenchId()).isNotNull();
    Assertions.assertThat(workbench.slots()).hasSize(9);
  }

  @Test
  @DisplayName("Should create Workbench slots with correct positions")
  void test4() {
    final var workbench = Workbench.newWorkbench(new CreateWorkbenchCommand("Wooden Workbench", 2, 2));

    Assertions.assertThat(workbench.name()).isEqualTo("Wooden Workbench");
    Assertions.assertThat(workbench.workbenchId()).isNotNull();
    Assertions.assertThat(workbench.slots()).hasSize(4);
    Assertions.assertThat(workbench.slots()).extracting(Slot::label)
      .containsExactly(
        "Wooden Workbench(0, 0)",
        "Wooden Workbench(1, 0)",
        "Wooden Workbench(0, 1)",
        "Wooden Workbench(1, 1)"
      );
    Assertions.assertThat(workbench.slots()).extracting(Slot::position)
      .containsExactly(Position.of(0, 0), Position.of(1, 0), Position.of(0, 1), Position.of(1, 1));
  }

}
