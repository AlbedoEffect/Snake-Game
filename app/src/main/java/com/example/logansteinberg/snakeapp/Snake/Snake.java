package com.example.logansteinberg.snakeapp.Snake;
import com.example.logansteinberg.snakeapp.Direction;
import com.example.logansteinberg.snakeapp.Board.CustomGameView;

import java.util.ArrayDeque;
import java.util.Iterator;

/**
 * Created by logan.steinberg on 2016-12-22.
 */

public class Snake {
	ArrayDeque<Section> sections;
	private int speed = 0;

	public Snake(int initSpeed, Direction initDirection, Square initSquare, int numTrailingSquares){
		speed = initSpeed;
		sections = new ArrayDeque<>();
		ArrayDeque<Square> initSectionSquares = new ArrayDeque<>();
		initSectionSquares.push(initSquare);
		sections.push(new Section(initDirection, initSectionSquares));
		addSquares(numTrailingSquares);
	}

	private Direction getTurnDirection(int invert){
		Direction currentDirection =  sections.getFirst().getDirection();
		if(currentDirection.getXDirect() == Direction.BACKWARD){
			currentDirection.setYDirect(Direction.BACKWARD*invert);
			currentDirection.setXDirect(Direction.NO_DIRECTION);
		}else if(currentDirection.getXDirect() == Direction.FORWARD){
			currentDirection.setYDirect(Direction.FORWARD*invert);
			currentDirection.setXDirect(Direction.NO_DIRECTION);
			System.out.println("Dir.: X: " + currentDirection.getXDirect() + " Y: " + currentDirection.getYDirect());
		}else if(currentDirection.getYDirect() == Direction.FORWARD){
			currentDirection.setXDirect(Direction.BACKWARD*invert);
			currentDirection.setYDirect(Direction.NO_DIRECTION);
		}else if(currentDirection.getYDirect() == Direction.BACKWARD){
			currentDirection.setXDirect(Direction.FORWARD*invert);
			currentDirection.setYDirect(Direction.NO_DIRECTION);
		}
		return currentDirection;
	}

	public void turnLeft(){
		turn(getTurnDirection(1));
	}

	public void turnRight(){
		turn(getTurnDirection(-1));
	}

	private void turn(Direction direction)
    {
        ArrayDeque<Square> newSectionSquares = new ArrayDeque<>();
        newSectionSquares.push(sections.getFirst().pop());
        sections.push(new Section(direction, newSectionSquares));
    }

	public void move() {
	    //Move behaviour is different between first and other sections
        //The first section can move freely forward, but the other sections
        //must push one of their squares to the section ahead of them
        Iterator<Section> sectionIterator = sections.iterator();
        //Represents the currently moving section - moves from front-to-back
        Section parentSection = sectionIterator.next();
        while(parentSection != null)
        {
            Section nextSection = sectionIterator.hasNext() ? sectionIterator.next() : null;
            if (nextSection != null)
            {
                parentSection.push(nextSection.pop());
            }
            parentSection.move(speed);
            parentSection = nextSection;
        }
	}

	public void addSquares(int numSquares){
	    Section lastSection = sections.peekLast();
	    for (int i = 0; i < numSquares; ++i) {
            Square tailSquare = lastSection.getTailSquare();
            lastSection.push(new Square(tailSquare.getX() + CustomGameView.SQUARE_DIST, tailSquare.getY() + CustomGameView.SQUARE_DIST));
        }
	}
}
