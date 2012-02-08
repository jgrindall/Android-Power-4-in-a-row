package com.jgrindall.android.connect4.activity;

import com.jgrindall.android.connect4.Connect4App;
import com.jgrindall.android.connect4.R;
import com.jgrindall.android.connect4.lib.board.*;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class SettingsActivity extends Activity  {
	
	 private RadioGroup soundGroup;
	 private RadioButton sound0,sound1;
	 private Button doneButton;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        init();
        load();
    }
    
    private void setSound(int i){
    	if(i==Players.SOUND_ON){sound0.setChecked(true);}
    	else if(i==Players.SOUND_OFF){sound1.setChecked(true);}
    }
    
    private int getSound(){
    	if(soundGroup.getCheckedRadioButtonId()==R.id.radio_sound0){
    		return Players.SOUND_ON;
    	}
    	else {
    		return Players.SOUND_OFF;
    	}
    }
   
    private void store(){
    	 SharedPreferences settings = getSharedPreferences(Connect4App.PREFS_NAME, 0);
	     SharedPreferences.Editor editor = settings.edit();
	     int s = getSound();
	     editor.putInt(Connect4App.PREFS_SOUND,s);
	     editor.commit();
    }
    private void load(){
    	SharedPreferences settings = getSharedPreferences(Connect4App.PREFS_NAME, 0);
        int s = settings.getInt(Connect4App.PREFS_SOUND, Players.SOUND_ON);
        setSound(s);
    }
    private void init(){
        soundGroup = (RadioGroup) findViewById(R.id.radio_sound);
        sound0 = (RadioButton) findViewById(R.id.radio_sound0);
        sound1 = (RadioButton) findViewById(R.id.radio_sound1);
        doneButton = (Button) findViewById(R.id.done_options);
        doneButton.setOnClickListener(new OnClickListener() {
    		public void onClick(View v) {
    			store();
    			finish();
    		}
    	});	
    }
    
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
    	super.onRestoreInstanceState(savedInstanceState);
    }
    public void onRestart(){
    	super.onRestart();
    	//load();
    }
    public void onStart(){
    	super.onStart();
    	//load();
    }
    public void onResume(){
    	super.onResume();
    	//load();
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
    
   
}



