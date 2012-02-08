package com.jgrindall.android.connect4.activity;

import com.jgrindall.android.connect4.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class HelpActivity extends Activity implements View.OnClickListener {

	
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        init();
        
    }
    
    private void init(){
    	TextView tV = (TextView)(this.findViewById(R.id.help_text));
    	tV.setLinkTextColor(R.color.linkcolors);
    	
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
		
		
		
	}
   
}