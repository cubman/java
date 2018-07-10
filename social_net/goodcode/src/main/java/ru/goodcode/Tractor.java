package ru.goodcode;

import ru.goodcode.exception.TractorInDitchException;
import ru.goodcode.orientation.Orientation;
import ru.goodcode.orientation.Position;

public class Tractor {

    Position position;
    Position goalField;
    Orientation orientation;

    public Tractor(Position position, Position goalField, Orientation orientation) {
        this.position = position;
        this.goalField = goalField;
        this.orientation = orientation;
    }

    public Tractor(Orientation orientation, Position goalField) {
        this.position = new Position(0, 0);
        this.goalField = goalField;
        this.orientation = orientation;
    }

    public Tractor() {
        this.position = new Position(0, 0);
        this.goalField = new Position(5, 5);;
        this.orientation = Orientation.NORTH;
    }

    public void move(Command command) {
        switch (command) {
            case TURN:
                orientation = Orientation.clockwith(orientation);
                break;
            case FORWARD:
                moveForwards();
                break;
            default:
                throw new IndexOutOfBoundsException("Command was detected" + command);
        }
    }

    public void moveForwards() {
        int x = getPositionX();
        int y = getPositionY();

        switch (orientation) {
            case SOUTH:
                --y;
                break;
            case NORTH:
                ++y;
                break;
            case WEST:
                ++x;
                break;
            case EAST:
                --x;
                break;
            default:
                throw new IndexOutOfBoundsException("Orientation was not defined");
        }

        if (x > goalField.getX() || y > goalField.getY()) {
            throw new TractorInDitchException();
        }

        position.setY(y);
        position.setX(x);
    }

    public int getPositionX() {
        return position.getX();
    }

    public int getPositionY() {
        return position.getY();
    }

    public Orientation getOrientation() {
        return orientation;
    }

}


