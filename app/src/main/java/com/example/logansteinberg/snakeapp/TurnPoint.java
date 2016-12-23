package com.example.logansteinberg.snakeapp;
/**
 * Created by logan.steinberg on 2016-12-22.
 */

public class TurnPoint {
	int x, y;
	Direction direction;

	public TurnPoint(int x, int y, Direction direction){
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
}
