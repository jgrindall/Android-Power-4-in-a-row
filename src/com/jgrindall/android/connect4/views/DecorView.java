package com.jgrindall.android.connect4.views;

import com.jgrindall.android.connect4.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class DecorView extends RelativeLayout {
			
	public DecorView(Context c, AttributeSet a) {
		super(c,a);
		init();
	}
	public DecorView(Context c){
		super(c);
		init();
	}
	private void init(){
		inflate();
	}
	private boolean getIsLarge(){
		// 640 by 480 is 307200
		// 700 by 900 is 630000
		WindowManager man = (WindowManager)this.getContext().getSystemService(Context.WINDOW_SERVICE);
		Display display = man.getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		return (width*height > 450000);
	}
	private void inflate(){
		LayoutInflater linf = (LayoutInflater)(getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE));
		boolean isLarge = getIsLarge();
		if(isLarge){
			linf.inflate(R.layout.decorlarge, this, true);
		}
		else{
			linf.inflate(R.layout.decor, this, true);
		}
	}
	
}






