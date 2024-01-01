package com.da0hn.craft.system.core.domain.workbench;

import com.da0hn.craft.system.core.domain.workbench.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

  @Test
  @DisplayName("Should change to Top position without exceed the limit")
  void test1() {
    final var position = new Position(0, 0);
    final var maybeTopPosition = position.top(3);
    Assertions.assertThat(maybeTopPosition).isPresent();
    Assertions.assertThat(maybeTopPosition.get()).isEqualTo(new Position(0, 1));
  }

  @Test
  @DisplayName("Should return empty position when Top position exceed the limit")
  void test2() {
    final var position = new Position(0, 2);
    final var maybeTopPosition = position.top(3);
    Assertions.assertThat(maybeTopPosition).isEmpty();
  }

  @Test
  @DisplayName("Should change to Bottom position without exceed the limit")
  void test3() {
    final var position = new Position(0, 1);
    final var maybeTopPosition = position.bottom();
    Assertions.assertThat(maybeTopPosition).isPresent();
    Assertions.assertThat(maybeTopPosition.get()).isEqualTo(new Position(0, 0));
  }

  @Test
  @DisplayName("Should return empty position when Bottom position exceed the limit")
  void test4() {
    final var position = new Position(0, 0);
    final var maybeBottomPosition = position.bottom();
    Assertions.assertThat(maybeBottomPosition).isEmpty();
  }

  @Test
  @DisplayName("Should change to Left position without exceed the limit")
  void test5() {
    final var position = new Position(1, 0);
    final var maybeLeftPosition = position.left();
    Assertions.assertThat(maybeLeftPosition).isPresent();
    Assertions.assertThat(maybeLeftPosition.get()).isEqualTo(new Position(0, 0));
  }

  @Test
  @DisplayName("Should return empty position when Left position exceed the limit")
  void test6() {
    final var position = new Position(0, 0);
    final var maybeLeftPosition = position.left();
    Assertions.assertThat(maybeLeftPosition).isEmpty();
  }

  @Test
  @DisplayName("Should change to Right position without exceed the limit")
  void test7() {
    final var position = new Position(0, 0);
    final var maybeLeftPosition = position.right(3);
    Assertions.assertThat(maybeLeftPosition).isPresent();
    Assertions.assertThat(maybeLeftPosition.get()).isEqualTo(new Position(1, 0));
  }

  @Test
  @DisplayName("Should return empty position when Right position exceed the limit")
  void test8() {
    final var position = new Position(2, 0);
    final var maybeLeftPosition = position.right(3);
    Assertions.assertThat(maybeLeftPosition).isEmpty();
  }

  @Test
  @DisplayName("Should change to Bottom Left position without exceed the limit")
  void test9() {
    final var position = new Position(1, 1);
    final var maybeBottomLeftPosition = position.bottomLeft();
    Assertions.assertThat(maybeBottomLeftPosition).isPresent();
    Assertions.assertThat(maybeBottomLeftPosition.get()).isEqualTo(new Position(0, 0));
  }

  @Test
  @DisplayName("Should return empty position when Bottom Left position exceed the limit")
  void test10() {
    final var position = new Position(0, 0);
    final var maybeBottomLeftPosition = position.bottomLeft();
    Assertions.assertThat(maybeBottomLeftPosition).isEmpty();
  }

  @Test
  @DisplayName("Should change to Bottom Right position without exceed the limit")
  void test11() {
    final var position = new Position(1, 1);
    final var maybeBottomRightPosition = position.bottomRight(3);
    Assertions.assertThat(maybeBottomRightPosition).isPresent();
    Assertions.assertThat(maybeBottomRightPosition.get()).isEqualTo(new Position(2, 0));
  }

  @Test
  @DisplayName("Should return empty position when Bottom Right position exceed the limit")
  void test12() {
    final var position = new Position(2, 1);
    final var maybeBottomRightPosition = position.bottomRight(3);
    Assertions.assertThat(maybeBottomRightPosition).isEmpty();
  }

  @Test
  @DisplayName("Should change to Top Left position without exceed the limit")
  void test13() {
    final var position = new Position(1, 1);
    final var maybeTopLeftPosition = position.topLeft(3);
    Assertions.assertThat(maybeTopLeftPosition).isPresent();
    Assertions.assertThat(maybeTopLeftPosition.get()).isEqualTo(new Position(0, 2));
  }

  @Test
  @DisplayName("Should return empty position when Top Left position exceed the limit")
  void test14() {
    final var position = new Position(0, 0);
    final var maybeTopLeftPosition = position.topLeft(3);
    Assertions.assertThat(maybeTopLeftPosition).isEmpty();
  }

  @Test
  @DisplayName("Should change to Top Right position without exceed the limit")
  void test15() {
    final var position = new Position(1, 1);
    final var maybeTopLeftPosition = position.topRight(3);
    Assertions.assertThat(maybeTopLeftPosition).isPresent();
    Assertions.assertThat(maybeTopLeftPosition.get()).isEqualTo(new Position(2, 2));
  }

  @Test
  @DisplayName("Should return empty position when Top Right position exceed the limit")
  void test16() {
    final var position = new Position(1, 2);
    final var maybeTopLeftPosition = position.topRight(3);
    Assertions.assertThat(maybeTopLeftPosition).isEmpty();
  }

}
