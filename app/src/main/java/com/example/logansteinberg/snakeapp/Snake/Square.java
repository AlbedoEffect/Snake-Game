package com.example.logansteinberg.snakeapp.Snake;
import com.example.logansteinberg.snakeapp.Direction;

import java.util.ArrayList;
/**
 * Created by logan.steinberg on 2016-12-22.
 */

public class Square {
	private int x, y;

	public Square(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public void move(int speed, Direction direction){
		x += speed * direction.getXDirect();
		y += speed * direction.getYDirect();
	}
}