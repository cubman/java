package ru.goodcode.orientation;

public enum Orientation {
    NORTH, WEST, SOUTH, EAST;

    public static Orientation clockwith(Orientation orientation) {
        switch (orientation) {
            case EAST: return SOUTH;
            case WEST: return NORTH;
            case NORTH: return EAST;
            case SOUTH: return WEST;
                default:
                    throw new IndexOutOfBoundsException("Направление не определено");
        }
    }
}

