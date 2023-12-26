package com.da0hn.craft.system.core.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WorkbenchTest {

  @BeforeEach
  void setUp() {
  }

  @Test
  @DisplayName("Should create Workbench with 9 slots")
  void test1() {
    final var workbench = Workbench.newWorkbench("Iron Workbench", 3, 3);

    Assertions.assertThat(workbench.name()).isEqualTo("Iron Workbench");
    Assertions.assertThat(workbench.workbenchId()).isNotNull();
    Assertions.assertThat(workbench.slots()).hasSize(9);
  }

  @Test
  @DisplayName("Should create Workbench slots with correct positions")
  void test2() {
    final var workbench = Workbench.newWorkbench("Wooden Workbench", 2, 2);

    Assertions.assertThat(workbench.name()).isEqualTo("Wooden Workbench");
    Assertions.assertThat(workbench.workbenchId()).isNotNull();
    Assertions.assertThat(workbench.slots()).hasSize(4);
    Assertions.assertThat(workbench.slots()).extracting(Slot::label)
      .containsExactlyInAnyOrder(
        "Wooden Workbench(0, 0)",
        "Wooden Workbench(0, 1)",
        "Wooden Workbench(1, 0)",
        "Wooden Workbench(1, 1)"
      );
    Assertions.assertThat(workbench.slots()).extracting(Slot::position)
      .containsExactlyInAnyOrder(Position.of(0, 0), Position.of(0, 1), Position.of(1, 0), Position.of(1, 1));
  }

}
