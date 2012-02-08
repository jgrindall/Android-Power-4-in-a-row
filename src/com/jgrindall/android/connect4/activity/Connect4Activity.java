package com.jgrindall.android.connect4.activity;

import com.jgrindall.android.connect4.Connect4App;
import com.jgrindall.android.connect4.R;
import com.jgrindall.android.connect4.lib.algorithm.*;
import com.jgrindall.android.connect4.lib.board.*;
import com.jgrindall.android.connect4.views.*;
import com.jgrindall.android.connect4.inter.*;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

public class Connect4Activity extends ABaseActivity implements IOnDebugListener,IOptionsListener,IOnExitListener, View.OnClickListener {
	
	private TopView tV;
	private GameView gV;
	private BottomView bV;
	private TextView dV;
	private Button powerButton;
	private boolean compInterrupted = false;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	this.debug("CREATE");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        try {
			Class.forName("android.os.AsyncTask");
		}
        catch (ClassNotFoundException e) {
			
		}
        init();
    }
    public void debug(String s){
    	if(!Connect4App.DEBUG){
    		return;
    	}
    	if(dV!=null){
    		if(s==""){
    			dV.setText("");
    		}
    		else{
    			dV.setText(dV.getText()+"\n"+s);
    		}
    	}
    }
    private void init(){
    	tV = (TopView)this.findViewById(R.id.topview);
    	gV = (GameView)this.findViewById(R.id.gameview);
    	bV = (BottomView)this.findViewById(R.id.bottomview);
    	dV = (TextView)this.findViewById(R.id.debugtext);
    	if(!Connect4App.DEBUG){
    		dV.setVisibility(View.GONE);
    	}
    	powerButton = (Button)(this.findViewById(R.id.powerbutton));
    	addListeners();
    	startGame();
    	
    }
    private void startGame(){
    	SharedPreferences settings = getSharedPreferences(Connect4App.PREFS_NAME, 0);
        int d = settings.getInt(Connect4App.PREFS_DIFF, Players.DIFF_EASY);
        int p = settings.getInt(Connect4App.PREFS_PLAY, Players.ONE_PLAYER);
    	gV.setDepth(AlgorithmConsts.getDefaultDepth());
    	gV.setDifficulty(d);
    	gV.setOnExitListener(this);
    	tV.setNumPlayers(p);
    	
    }
    private void reload(){
    	debug("compInterrupted "+compInterrupted);
    	if(compInterrupted){
    		gV.restart();
    	}
    }
    private void gameInflated(){
    	this.debug("inflated");
    	gV.inflated();
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
    	super.onRestoreInstanceState(savedInstanceState);
    }
    public void onRestart(){
    	super.onRestart();
    	this.debug("RESTART");
    }
    public void onStart(){
    	super.onStart();
    	this.debug("STARTING");
    }
    public void onResume(){
    	super.onResume();
    	this.debug("RESUMING");
    	// reload the UI and play the move.
    	reload();
    }
    public void onSaveInstanceState(Bundle outState){
    	super.onSaveInstanceState(outState);
    	this.debug("onSaveInstanceState");
    }
    public void onPause(){
    	super.onPause();
    	this.debug("PAUSE");
    }
    private void cleanUp(){
    	this.debug("cleanUp");
    	if(gV!=null){
    		compInterrupted = gV.cleanUp();
    	}
    }
    public void onStop(){
    	super.onStop();
    	this.debug("STOP");
    	cleanUp();
    }
    public void onDestroy(){
    	super.onDestroy();
    	this.debug("DESTROY");
    	cleanUp();
    }
    private void addListeners(){
    	bV.setOnNewGameListener(gV);
    	bV.setOnUndoListener(gV);
    	bV.setOnOptionsListener(this);
    	gV.setOnTurnChangeListener(tV);
    	gV.setOnBottomListener(bV);
    	gV.setOnDebugListener(this);
    	ViewTreeObserver vto = gV.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                gameInflated();
                ViewTreeObserver obs = gV.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
            }
        });
        if(Connect4App.DEBUG){
    		powerButton.setOnClickListener(this);
    	}
    	else{
    		powerButton.setVisibility(View.INVISIBLE);
    	}
    }
    
   	
    private void back(){
    	finish();
    }
    private void openLeaveDialog(){
    	final boolean wasEnabled = gV.getBoardEnabled();
    	gV.enableBoard(false);
    	final Dialog dialog = new Dialog(this,R.style.MyDialog);
        dialog.setContentView(R.layout.leave);
        TextView tV = (TextView)dialog.findViewById(R.id.leave_msg);
        tV.setText(R.string.sureleave);
        dialog.setCancelable(true);
      
        
        
        dialog.show();
        ((Button) dialog.findViewById(R.id.leave_cancel)).setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		dialog.dismiss();
        		gV.enableBoard(wasEnabled);
            }
        });
        ((Button) dialog.findViewById(R.id.leave_quit)).setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		gV.enableBoard(wasEnabled);
        		back();
            }
        });
    }
  
    @Override
   
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
        	openLeaveDialog();
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }

   
    
	@Override
	public boolean onOptions(View v) {
		Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
        return true;
	}
	@Override
	public void exit() {
		finish();
	}
	@Override
	public void onClick(View v) {
		gV.powerPressed();
		
	}
	
    
    
}