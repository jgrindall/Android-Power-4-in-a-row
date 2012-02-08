package com.jgrindall.android.connect4.views;

import java.util.Timer;
import java.util.TimerTask;
import com.jgrindall.android.connect4.R;
import com.jgrindall.android.connect4.inter.*;
import com.jgrindall.android.connect4.lib.board.*;
import android.graphics.drawable.AnimationDrawable;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ImageView.ScaleType;

public class WinLinesView extends RelativeLayout{
	
	private int pieceDiam;
	private IWinListener winListen;
	
	public WinLinesView(Context context) {
		super(context);
	}
	public WinLinesView(Context c, AttributeSet a){
		super(c,a);
	}
	public WinLinesView(Context c, AttributeSet a, int ds){
		super(c,a,ds);
	}
	public void setPieceDiam(int d){
		pieceDiam = d;
	}
	public void setWinListener(IWinListener g){
		this.winListen = g;
	}
	private void decorFinished(){
		reset();
		if(winListen!=null){
			winListen.onWinFinished();
		}
	}
	public void draw(APoint[][] line){
		for(int i=0;i<=line.length-1;i++){
			APoint[] level = line[i];
			for(int j=0;j<=level.length-1;j++){
				makeWin(level[j],i);
			}
		}
		decorWait();
	}
	private void decorWait(){
		Timer t = new Timer();
		final Handler handler = new Handler();
		TimerTask endDecorTask = new TimerTask() {
	        public void run() {
	        	handler.post(new Runnable() {
                    public void run() {
                    	decorFinished();
                    }
	        		});
	        }};
		t.schedule(endDecorTask, 2000);
	}
	public void reset(){
		removeAllViews();
	}
	private int getAnimationId(int i){
		if(i==0){
			return R.drawable.animated_decor0;
		}
		else if(i==1){
			return R.drawable.animated_decor1;
		}
		else if(i==2){
			return R.drawable.animated_decor2;
		}
		else{
			return R.drawable.animated_decor3;
		}
	}
	private void makeWin(APoint p, int n){
		MarginLayoutParams mp = new MarginLayoutParams(pieceDiam,pieceDiam);
		mp.leftMargin  =  p.x;
		mp.topMargin =    p.y;
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(mp);
		ImageView im = new ImageView(this.getContext());
		im.setScaleType(ScaleType.FIT_CENTER);
		im.setBackgroundResource(getAnimationId(n));
		addView(im, params);
		((AnimationDrawable)(im.getBackground())).start();
		
	}
	
}