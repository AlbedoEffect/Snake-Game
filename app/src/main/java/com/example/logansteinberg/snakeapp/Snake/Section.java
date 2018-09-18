package com.example.logansteinberg.snakeapp.Snake;

import com.example.logansteinberg.snakeapp.Direction;

import java.util.ArrayDeque;

public class Section {
    private Direction direction;
    private ArrayDeque<Square> squares;

    public Section(Direction direction, ArrayDeque<Square> squares)
    {
        this.direction = direction;
        this.squares = squares;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void move(int speed){
        for (Square square : squares)
        {
            square.move(speed, direction);
        }
    }

    public Square pop()
    {
        // Use remove over pop to have FIFO behaviour
        return squares.remove();
    }

    public void push(Square square){
        squares.push(square);
    }

    public Direction getDirection() {
        return direction;
    }

    public Square getHeadSquare()
    {
        return squares.peekFirst();
    }

    public Square getTailSquare()
    {
        return squares.peekLast();
    }
}
