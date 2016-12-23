package com.example.logansteinberg.snakeapp;
import java.util.ArrayList;
/**
 * Created by logan.steinberg on 2016-12-22.
 */

public class Snake {
	Square headSquare;
	Square lastSquare;
	ArrayList<Square> body;
	ArrayList<TurnPoint>turnPoints;
	private int speed = 0;
	Direction initDirection;

	public Snake(int initSpeed, Direction initDirection){
		speed = initSpeed;
		this.initDirection = initDirection;
		body = new ArrayList<>();
		turnPoints = new ArrayList<>();
	}

	public Direction getTurnDirection(int invert){
		Direction currentDirection = new Direction(headSquare.getCurrentDirection().getXDirect(),
			headSquare.getCurrentDirection().getYDirect());
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
		Direction turnDirection = getTurnDirection(1);
		pushTurningPoint(turnDirection);
	}

	public void turnRight(){
		Direction turnDirection = getTurnDirection(-1);
		pushTurningPoint(turnDirection);
	}

	private int getTurningPointX(){
		if (headSquare.getCurrentDirection().getXDirect() == 1){
			return headSquare.getX() + CustomGameView.TURN_OFFSET;
		}else if(headSquare.getCurrentDirection().getXDirect() == -1){
			return headSquare.getX() - CustomGameView.TURN_OFFSET;
		}else{
			return headSquare.getX();
		}
	}

	private int getTurningPointY(){
		if (headSquare.getCurrentDirection().getYDirect() == 1){
			return headSquare.getY() + CustomGameView.TURN_OFFSET;
		}else if(headSquare.getCurrentDirection().getYDirect() == -1){
			return headSquare.getY() - CustomGameView.TURN_OFFSET;
		}else{
			return  headSquare.getY();
		}
	}

	private void pushTurningPoint(Direction turnDirection){
		TurnPoint turnPoint = new TurnPoint(getTurningPointX(),getTurningPointY(),turnDirection);
		System.out.println("Turn point: " + "(" + (getTurningPointX())
		 + "," + getTurningPointY() + ")");
		turnPoints.add(turnPoints.size(),turnPoint);
	}

	private void pushTurningPoint(TurnPoint turnPoint){
		turnPoints.add(turnPoints.size(),turnPoint);
	}

	public void move(){
		for(Square square : body){
			TurnPoint turnPoint = square.move(turnPoints);
			if(square == lastSquare && turnPoints.size() > 0 && turnPoint == turnPoints.get(0)){
				System.out.println("Removed turn point");
				turnPoints.remove(0);
			}
		}
	}

	public int getSpeed(){
		return speed;
	}

	public Direction getInitDirection(){
		return initDirection;
	}

	public void setHeadSquare(int xPos, int yPos){
		headSquare = new Square(xPos,yPos,speed,initDirection);
		lastSquare = headSquare;
		body.add(headSquare);
		//pushTurningPoint((new TurnPoint(810,1046,new Direction(0,-1))));
		addSquares();
	}

	public void addSquares(){
		Square lastSquare = body.get(body.size() - 1);
		Direction lastSquareDirection = lastSquare.getCurrentDirection();
		int lSqrX = lastSquare.getX();
		int lSqrY = lastSquare.getY();
		final int SQUARE_INCREMENT = 5;
		for (int i = 0; i < SQUARE_INCREMENT; i++) {
			Square square = new Square(getCoord(lastSquareDirection.getXDirect(),lSqrX),
				getCoord(lastSquareDirection.getYDirect(),lSqrY), speed, lastSquareDirection);
			body.add(body.size(), square);
			lastSquare = body.get(body.size() - 1);
			lSqrX = lastSquare.getX();
			lSqrY = lastSquare.getY();
		}
		this.lastSquare = body.get(body.size()-1);
	}

	private int getCoord(int direction, int curPos){
		if(direction == 1){
			return curPos - CustomGameView.SQUARE_DIST;
		}else if(direction == -1) {
			return curPos + CustomGameView.SQUARE_DIST;
		}
		return curPos;
	}

	public Direction getCurrentDirection(){
		return headSquare.getCurrentDirection();
	}
}
