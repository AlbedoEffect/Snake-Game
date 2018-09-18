package com.example.logansteinberg.snakeapp.Views;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.logansteinberg.snakeapp.EatDot;
import com.example.logansteinberg.snakeapp.Snake.Snake;
import com.example.logansteinberg.snakeapp.Snake.Square;

import java.util.ArrayList;
import java.util.Random;
/**
 * Created by logan.steinberg on 2016-12-22.
 */

public class CustomGameView extends View {

	private float boardWidth;
	private float boardLength;

	public static final int SQUARE_LEN = 75;
	public static final int SQUARE_DIST = 85;
	public static final int TURN_OFFSET = 30;

	private final int BORDER_WIDTH = 40;
	private final int BOARD_OFFSET = 10;

	public CustomGameView(final Context context) {
		super(context);
	}

	public CustomGameView(final Context context, @Nullable final AttributeSet attrs) {
		super(context, attrs);
	}

	public void setPlayers(ArrayList<Snake> players){
		this.players = players;
	}

	public ArrayList<Snake> getPlayers(){
		return players;
	}

	public CustomGameView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public CustomGameView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr,
		final int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	@Override
	public void onDraw(Canvas canvas){
		System.out.println("Entered onDraw");
		initialBoardDraw(canvas);
		drawDot(canvas);
		drawPlayers(canvas);
		ArrayList<Snake> players;
		for(Snake snake : players){
			if (snake.body.size() == 0) snake.setHeadSquare(canvas.getWidth()/2,canvas.getHeight()/2);
		}
	}

	private void drawDot(Canvas canvas){
		int newX,newY;
		if(eatDot.getX() == -1 && eatDot.getY() == -1){
			newX = getRandX(canvas.getWidth() - (BORDER_WIDTH + 15), BORDER_WIDTH + 15);
			newY = getRandX(canvas.getWidth() - (BORDER_WIDTH + 15), BORDER_WIDTH + 15);
		}
	}

	private void checkDotPos(int x, int y){
		for(Snake snake: players){
			for(Square square: snake.body){
				
			}
		}
	}

	private int getRandX(int max, int min){
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}

	private void drawPlayers(Canvas canvas){
		System.out.println("Players size: " + players.size());
		for(Snake snake : players){
			drawSnake(canvas,snake);
		}
	}

	private void drawSnake(Canvas canvas, Snake snake){
		System.out.println("Snake body size: " + snake.body.size());
		for(Square square : snake.body){
			drawSquare(canvas,square);
		}
	}

	private void drawSquare(Canvas canvas, Square square){
		Paint paint = new Paint();
		paint.setColor(Color.YELLOW);
		System.out.println("Coords: (" + square.getX()  + "," + square.getY() + ")");
		canvas.drawRect(square.getX(), square.getY(), square.getX() + SQUARE_LEN,square.getY() + SQUARE_LEN, paint);
	}

	private void initialBoardDraw(Canvas canvas){
		boardWidth = canvas.getWidth();
		boardLength = canvas.getHeight();
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		canvas.drawRect(BORDER_WIDTH,BORDER_WIDTH,boardWidth - BOARD_OFFSET,boardLength - BOARD_OFFSET,paint);
		paint.setStyle(Style.STROKE);
		paint.setColor(Color.RED);
		paint.setStrokeWidth(BORDER_WIDTH);
		canvas.drawRect(BORDER_WIDTH,BORDER_WIDTH,boardWidth - BOARD_OFFSET,boardLength - BOARD_OFFSET,paint);
	}
}