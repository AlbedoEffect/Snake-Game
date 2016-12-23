package com.example.logansteinberg.snakeapp;
/**
 * Created by logan.steinberg on 2016-12-22.
 */

public class Direction {
	public static int FORWARD = 1;
	public static int BACKWARD = -1;
	public static int NO_DIRECTION = 0;

	private int xDirect;
	private int yDirect;

	public Direction(int initXDirect, int initYDirect){
		xDirect = initXDirect;
		yDirect = initYDirect;
	}

	public void setXDirect(int xDirect){
		this.xDirect = xDirect;
	}

	public void setYDirect(int yDirect){
		this.yDirect = yDirect;
	}

	public int getXDirect(){
		return xDirect;
	}

	public int getYDirect(){
		return yDirect;
	}
}
