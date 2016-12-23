package com.example.logansteinberg.snakeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.application_activity);
	}

	@Override
	public void onResume(){
		super.onResume();
		if(getFragmentManager().findFragmentByTag(TitlePageFragment.NAME) == null){
			getFragmentManager().beginTransaction()
				.add(R.id.mainContentContainer,TitlePageFragment.newInstance(), TitlePageFragment.NAME)
			.commit();
		}
	}
}
