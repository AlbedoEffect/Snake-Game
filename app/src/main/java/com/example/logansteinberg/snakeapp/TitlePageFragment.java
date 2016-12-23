package com.example.logansteinberg.snakeapp;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import java.util.ArrayList;

/**
 * Created by logan.steinberg on 2016-12-22.
 */

public class TitlePageFragment extends Fragment {
	public static String NAME = TitlePageFragment.class.getSimpleName();

	public static TitlePageFragment newInstance(){
		TitlePageFragment titlePageFragment = new TitlePageFragment();
		return titlePageFragment;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.title_screen,container,false);
		initalizeSelectionButtons(v);
		return v;

	}

	private void initalizeSelectionButtons(View rootView){
		Button soloGame = (Button) rootView.findViewById(R.id.soloButton);
		Button battleGame = (Button) rootView.findViewById(R.id.battleGame);
		soloGame.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View view) {
				GameFragment gameFragment = GameFragment.newInstance();
				getFragmentManager().beginTransaction()
					.replace(R.id.mainContentContainer,gameFragment,GameFragment.NAME)
					.commit();
				Direction direction = new Direction(1,0);
				Snake p1 = new Snake(30,direction);
				ArrayList<Snake> players = new ArrayList<Snake>();
				players.add(p1);
				gameFragment.setPlayers(players);
			}
		});

		battleGame.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View view) {

			}
		});
	}
}
