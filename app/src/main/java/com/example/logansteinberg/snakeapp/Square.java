package com.example.logansteinberg.snakeapp;
import java.util.ArrayList;
/**
 * Created by logan.steinberg on 2016-12-22.
 */

public class Square {
	private int x, y, speed;
	private TurnPoint passedTurnPoint = null;
	private Direction currentDirection;

	public Square(int x, int y, int speed, Direction direction){
		this.x = x;
		this.y = y;
		this.speed = speed;
		currentDirection = direction;
	}

	public Direction getCurrentDirection(){
		return currentDirection;
	}

	public void turn(TurnPoint turnPoint){
		currentDirection = turnPoint.direction;
		System.out.println("Turned: " + "X: " + currentDirection.getXDirect() + " Y: " + currentDirection.getYDirect());
		x = turnPoint.x;
		y = turnPoint.y;
	}

	public int getX(){
		return x;
	}

	public int getSpeed(){
		return  speed;
	}

	public int getY(){
		return y;
	}

	private boolean checkCoord(int turnPoint,int point1, int point2){
		System.out.println("Check for coord: " +
		point1 + ", " +  "," + point2);
		return  ((point1 <= turnPoint) && (turnPoint <= point2));
	}

	private boolean checkCoordNeg(int turnPoint,int point1, int point2){
		System.out.println("Check for coord: " +
			point1 + ", " +  "," + point2);
		return  ((point1 >= turnPoint) && (turnPoint >= point2));
	}

	private boolean checkForTurnPoint(TurnPoint turnPoint,int updatedX, int updatedY){
		if(turnPoint != null){
			boolean checkX = (getCurrentDirection().getXDirect() == 1) ?
				checkCoord(turnPoint.x,getX(),updatedX) : checkCoordNeg(getX(),turnPoint.x,updatedX);
			boolean checkY = (getCurrentDirection().getYDirect() == 1) ?
				checkCoord(turnPoint.y,getY(),updatedY) : checkCoordNeg(turnPoint.y,getY(),updatedY);
			return checkX && checkY;
		}
		return false;
	}

	public TurnPoint move(ArrayList<TurnPoint> turnPoints){
		int updatedX = x + speed * currentDirection.getXDirect();
		int updatedY = y + speed * currentDirection.getYDirect();
		for (TurnPoint turnPoint : turnPoints) {
			if (checkForTurnPoint(turnPoint,updatedX, updatedY)) {
				System.out.println("Passed turn point: " );
				if (passedTurnPoint == turnPoint)break;
				passedTurnPoint = turnPoint;
				turn(turnPoint);
				return turnPoint;
			}else{
				passedTurnPoint = null;
			}
		}
		x = updatedX;
		y = updatedY;
		return passedTurnPoint;
	}
}