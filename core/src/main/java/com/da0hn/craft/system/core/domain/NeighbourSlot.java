package com.da0hn.craft.system.core.domain;

public record NeighbourSlot(Slot slot, Direction direction) {

  public static NeighbourSlot newNeighbourAtLeft(final Slot slot) {
    return new NeighbourSlot(slot, Direction.LEFT);
  }

  public static NeighbourSlot newNeighbourAtRight(final Slot slot) {
    return new NeighbourSlot(slot, Direction.RIGHT);
  }

  public static NeighbourSlot newNeighbourAtTop(final Slot slot) {
    return new NeighbourSlot(slot, Direction.TOP);
  }

  public static NeighbourSlot newNeighbourAtBottom(final Slot slot) {
    return new NeighbourSlot(slot, Direction.BOTTOM);
  }

  public static NeighbourSlot newNeighbourAtTopRight(final Slot slot) {
    return new NeighbourSlot(slot, Direction.DIAGONAL_TOP_RIGHT);
  }

  public static NeighbourSlot newNeighbourAtTopLeft(final Slot slot) {
    return new NeighbourSlot(slot, Direction.DIAGONAL_TOP_LEFT);
  }

  public static NeighbourSlot newNeighbourAtBottomRight(final Slot slot) {
    return new NeighbourSlot(slot, Direction.DIAGONAL_BOTTOM_RIGHT);
  }

  public static NeighbourSlot newNeighbourAtBottomLeft(final Slot slot) {
    return new NeighbourSlot(slot, Direction.DIAGONAL_BOTTOM_LEFT);
  }

}

