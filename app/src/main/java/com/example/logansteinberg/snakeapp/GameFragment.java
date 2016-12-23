package com.example.logansteinberg.snakeapp;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import java.util.ArrayList;
/**
 * Created by logan.steinberg on 2016-12-22.
 */

public class GameFragment extends Fragment {
	public static final String NAME = GameFragment.class.getSimpleName();
	private ArrayList<Snake> players;
	int x1,x2,y1,y2;

	public static GameFragment newInstance(){
		return new GameFragment();
	}

	public void setPlayers(ArrayList<Snake> players){
		System.out.println("Set players");
		this.players = players;
		System.out.println("Players size: " + players.size());
	}

	public GameFragment(){

	}

	private void setupTouchListener(CustomGameView v){
		v.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(final View view, final MotionEvent motionEvent) {
				System.out.println("Detected touch: " + motionEvent.getAction());
				final int MIN_DISTANCE = 20;
				Snake p1 = null;
				if(players != null && players.size() > 0){
					p1 = players.get(0);
				}
				switch(motionEvent.getAction())
				{
					case MotionEvent.ACTION_DOWN:
						System.out.println("Down");
						x1 = (int)motionEvent.getX();
						y1 = (int)motionEvent.getY();
						return true;
					case MotionEvent.ACTION_UP:
						System.out.println("Action down");
						x2 = (int)motionEvent.getX();
						y2 = (int)motionEvent.getY();
						int deltaX = x2 - x1;
						int deltaY = y2 - y1;
						System.out.println("Swipe dists.: " + deltaX + "," + deltaY);
						if (deltaX > MIN_DISTANCE && p1 != null) {
							if(p1.getCurrentDirection().getYDirect() == -1){
								System.out.println("Left turn1");
								p1.turnLeft();
							}else if(p1.getCurrentDirection().getYDirect() == 1){
								System.out.println("Right turn1");
								p1.turnRight();
							}
						}else if((x1 - x2) > MIN_DISTANCE && p1 != null){
							if(p1.getCurrentDirection().getYDirect() == -1){
								System.out.println("Right turn2");
								p1.turnRight();
							}else if (p1.getCurrentDirection().getYDirect() == 1){
								System.out.println("Left turn2");
								p1.turnLeft();
							}
						}
						if(deltaY > MIN_DISTANCE && p1 != null) {
							if (p1.getCurrentDirection().getXDirect() == 1) {
								p1.turnLeft();
							}else if(p1.getCurrentDirection().getXDirect() == -1){
								p1.turnRight();
							}
						}else if((y1 - y2 > MIN_DISTANCE) && p1 != null){
							if(p1.getCurrentDirection().getXDirect() == 1){
								p1.turnRight();
							}else if(p1.getCurrentDirection().getXDirect() == -1){
								p1.turnLeft();
							}
						}
						break;
				}
				return false;
			}
		});
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final Handler handler = new Handler();
		final CustomGameView v = new CustomGameView(getActivity());
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				System.out.println("bool check: " + ((players != null && players.size() > 0)));
				if(players != null && players.size() > 0) {
					if(v.getPlayers().size() == 0){
						v.setPlayers(players);
						setupTouchListener(v);
					}
					updatePlayers();
					v.invalidate();
				}
				handler.postDelayed(this, 100);
			}
		},1000);
		return v;
	}

	private void updatePlayers(){
		for(Snake player : players){
			player.move();
		}
	}
}
