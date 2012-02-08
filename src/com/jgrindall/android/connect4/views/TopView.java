package com.jgrindall.android.connect4.views;

import com.jgrindall.android.connect4.R;
import com.jgrindall.android.connect4.inter.*;
import com.jgrindall.android.connect4.lib.board.*;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TopView extends RelativeLayout implements IOnTurnChangeListener{

	private Resources res;
	private TextView t1,t2;
	public int turn = Players.PLAYER1;
	
	public TopView(Context c, AttributeSet a){
		super(c,a);
		init();
	}
	public TopView(Context c){
		super(c);
		init();
	}
	public void setTurn(int i){
		turn = i;
		this.postInvalidate();
	}
	private void inflate(){
		LayoutInflater linf = (LayoutInflater)(getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE));
		linf.inflate(R.layout.top, this);
	}
	private int dpToPix(float f){
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, f, res.getDisplayMetrics());
	}
	private void init(){
		inflate();
		res = getResources();
		this.setWillNotDraw(false);
		t1 = (TextView)this.findViewById(R.id.your_text);
		t2 = (TextView)this.findViewById(R.id.comp_text);
	}
	public void setNumPlayers(int p){
		if(p==Players.ONE_PLAYER){
			t1.setText("You");
			t2.setText("Computer");
		}
		else{
			t1.setText("Player 1");
			t2.setText("Player 2");
		}
	}
	@Override
	protected void onDraw(Canvas c){
		float x0,x1,y0,y1;
		int padSides = dpToPix(4);
		int round = dpToPix(1F);
		int padBottom = dpToPix(3);
		
		int e1Pix = dpToPix(1F);	
		int e2Pix = dpToPix(2F);
		int e3Pix = dpToPix(3.5F);
		
		try{
			
			Paint linePaint = new Paint();
			linePaint.setStyle(Style.FILL);
			if(turn==Players.PLAYER1){
				x0 = padSides;
				x1 = t1.getRight()+padSides;
				y0 = t1.getBottom()+padBottom;
				y1 = y0+2*round;
				t1.setTextColor(Color.WHITE);
				t2.setTextColor(Color.GRAY);
			}
			else{
				x0 = t2.getLeft()-padSides;
				x1 = this.getWidth()-1;
				y0 = t2.getBottom()+padBottom;
				y1 = y0+2*round;
				t1.setTextColor(Color.GRAY);
				t2.setTextColor(Color.WHITE);
			}
			
			
			RectF r1 = new RectF(x0,y0,x1,y1);
			RectF r2 = new RectF(x0-e1Pix,y0-e1Pix,x1+e1Pix,y1+e1Pix);
			RectF r3 = new RectF(x0-e2Pix,y0-e2Pix,x1+e2Pix,y1+e2Pix);
			RectF r4 = new RectF(x0-e3Pix,y0-e3Pix,x1+e3Pix,y1+e3Pix);
			
			linePaint.setColor(res.getColor(R.color.wood_dark));
			c.drawRoundRect(r4,  round, round, linePaint);
			linePaint.setColor(res.getColor(R.color.wood_meddark));
			c.drawRoundRect(r3,  round, round, linePaint);
			linePaint.setColor(res.getColor(R.color.wood_medlight));
			c.drawRoundRect(r2,  round, round, linePaint);
			linePaint.setColor(res.getColor(R.color.wood_light));
			c.drawRoundRect(r1,  round, round, linePaint);
			
		}
		catch(Exception e){
			// not there yet
		}
		super.onDraw(c);
	}
	@Override
	public boolean onChange(View v, int t) {
		turn = t;
		this.postInvalidate();
		return false;
	}
}



