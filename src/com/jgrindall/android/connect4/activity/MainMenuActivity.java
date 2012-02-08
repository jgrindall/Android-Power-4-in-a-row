package com.jgrindall.android.connect4.activity;

import com.jgrindall.android.connect4.Connect4App;
import com.jgrindall.android.connect4.R;
import com.jgrindall.android.connect4.lib.board.Players;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.*;
import android.widget.*;


public class MainMenuActivity extends ABaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

	private Button setButton;
	private Button playButton;
	private Button helpButton;
	private RadioGroup diffGroup;
	private RadioGroup turnGroup;
	private RadioButton diff0, diff1, diff2;
	private RadioGroup playGroup;
	private RadioButton play0,play1;
	private RadioButton turn0,turn1;
	private RadioGroup powerGroup;
	private RadioButton power0,power1;
	
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        init();
        load();
    }
    private void setDiff(int i){
    	if(i==Players.DIFF_EASY){diff0.setChecked(true);}
    	else if(i==Players.DIFF_MED){diff1.setChecked(true);}
    	else if(i==Players.DIFF_HARD){diff2.setChecked(true);}
    }
    private void setPower(int i){
    	if(i==Players.POWER_ON){power0.setChecked(true);}
    	else if(i==Players.POWER_OFF){power1.setChecked(true);}
    }
    private void setPlayers(int i){
    	if(i==Players.ONE_PLAYER){play0.setChecked(true);}
    	else if(i==Players.TWO_PLAYERS){play1.setChecked(true);}
    }
    private void setTurn(int i){
    	if(i==Players.GO_FIRST){turn0.setChecked(true);}
    	else if(i==Players.GO_SECOND){turn1.setChecked(true);}
    }
    private int getTurn(){
    	if(turnGroup.getCheckedRadioButtonId()==R.id.radio_turn0){
    		return 0;
    	}
    	else {
    		return 1;
    	}
    }
    private int getPower(){
    	if(powerGroup.getCheckedRadioButtonId()==R.id.radio_power0){
    		return Players.POWER_ON;
    	}
    	else {
    		return Players.POWER_OFF;
    	}
    }
    private int getDiff(){
    	if(diffGroup.getCheckedRadioButtonId()==R.id.radio_diff0){
    		return 0;
    	}
    	else if(diffGroup.getCheckedRadioButtonId()==R.id.radio_diff1){
    		return 1;
    	}
    	else {
    		return 2;
    	}
    }
    private int getPlay(){
    	if(playGroup.getCheckedRadioButtonId()==R.id.radio_play0){
    		return 0;
    	}
    	else {
    		return 1;
    	}

    }
    private void store(){
   	 	 SharedPreferences settings = getSharedPreferences(Connect4App.PREFS_NAME, 0);
	     SharedPreferences.Editor editor = settings.edit();
	     int d = getDiff();
	     int p = getPlay();
	     int t = getTurn();
	     int pw = getPower();
	     editor.putInt(Connect4App.PREFS_DIFF,d);
	     editor.putInt(Connect4App.PREFS_PLAY,p);
	     editor.putInt(Connect4App.PREFS_TURN,t);
	     editor.putInt(Connect4App.PREFS_POWER,pw);
	     editor.commit();
   }
    private void load(){
    	SharedPreferences settings = getSharedPreferences(Connect4App.PREFS_NAME, 0);
        int d = settings.getInt(Connect4App.PREFS_DIFF, Players.DIFF_EASY);
        int p = settings.getInt(Connect4App.PREFS_PLAY, Players.ONE_PLAYER);
        int t = settings.getInt(Connect4App.PREFS_TURN, Players.GO_FIRST);
        int pw = settings.getInt(Connect4App.PREFS_POWER, Players.POWER_OFF);
        showTurn();
        setDiff(d);
        setPlayers(p);
        setTurn(t);
        setPower(pw);
        this.showTurn();
    }
    private void init(){
    	playButton = (Button)(this.findViewById(R.id.play_button));
    	playButton.setOnClickListener(this);
    	setButton = (Button)(this.findViewById(R.id.open_settings));
    	setButton.setOnClickListener(this);
    	helpButton = (Button)(this.findViewById(R.id.open_help));
    	helpButton.setOnClickListener(this);
    	diffGroup = (RadioGroup) findViewById(R.id.radio_diff);
        playGroup = (RadioGroup) findViewById(R.id.radio_play);
        turnGroup = (RadioGroup) findViewById(R.id.radio_turn);
        diff0 = (RadioButton) findViewById(R.id.radio_diff0);
        diff1 = (RadioButton) findViewById(R.id.radio_diff1);
        diff2 = (RadioButton) findViewById(R.id.radio_diff2);
        play0 = (RadioButton) findViewById(R.id.radio_play0);
        play1 = (RadioButton) findViewById(R.id.radio_play1);
        turn0 = (RadioButton) findViewById(R.id.radio_turn0);
        turn1 = (RadioButton) findViewById(R.id.radio_turn1);
        playGroup.setOnCheckedChangeListener(this);
        powerGroup = (RadioGroup) findViewById(R.id.radio_power);
        power0 = (RadioButton) findViewById(R.id.radio_power0);
        power1 = (RadioButton) findViewById(R.id.radio_power1);
        //layout();
    }

   
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
    	super.onRestoreInstanceState(savedInstanceState);
    }
    public void onRestart(){
    	super.onRestart();
    }
    public void onStart(){
    	super.onStart();
    }
    public void onResume(){
    	super.onResume();
    }
    public void onSaveInstanceState(Bundle outState){
    	super.onSaveInstanceState(outState);
    }
    public void onPause(){
    	super.onPause();
    }
    public void onStop(){
    	super.onStop();
    }
    public void onDestroy(){
    	super.onDestroy();
    }
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.open_settings){
			Intent intent = new Intent(this, SettingsActivity.class);
	        startActivity(intent);
		}
		else if(v.getId()==R.id.play_button){
			store();
			Intent intent = new Intent(this, Connect4Activity.class);
	        this.startActivity(intent);
		}
		else if(v.getId()==R.id.open_help){
			store();
			Intent intent = new Intent(this, HelpActivity.class);
	        this.startActivity(intent);
		}
		
	}
	private void showTurn(){
		boolean show = (getPlay()==Players.ONE_PLAYER);
		turnGroup.setEnabled(show);
		turn0.setEnabled(show);
		turn1.setEnabled(show);
		diff0.setEnabled(show);
		diff1.setEnabled(show);
		diff2.setEnabled(show);
		diffGroup.setEnabled(show);
		
	}
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		showTurn();
	}
   
}