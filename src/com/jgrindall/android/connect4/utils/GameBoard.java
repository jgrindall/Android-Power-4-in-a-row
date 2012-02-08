package com.jgrindall.android.connect4.utils;

import com.jgrindall.android.connect4.lib.board.*;

public class GameBoard{
	
	private int[] xpos = new int[Board.NUMX];
	private int[] ypos = new int[Board.NUMY];
	private int[] centrex = new int[Board.NUMX];
	private double scaleX,scaleY;
	private int pieceDiam;
	private double boardWidth, boardHeight;
	private double boardOriginX, boardOriginY;
	private double realGapY;
	
	public GameBoard(){
		
	}
	public double getRealGapY(){
		return realGapY;
	}
	public int getColForTouch(double xPos){
		int leastD = Integer.MAX_VALUE;
		int leastCol = 0;
		int dx;
		for(int i=0;i<=Board.NUMX-1;i++){
			dx = (int)Math.abs(xPos - centrex[i]);
			if(dx<leastD){
				leastD = dx;
				leastCol = i;
			}
		}
		return leastCol;
	}
	public int getX(int i){
		return xpos[i];
	}
	public int getY(int i){
		return ypos[i];
	}
	public double getScaleX(){
		return scaleX;
	}
	public double getScaleY(){
		return scaleY;
	}
	public int getPieceDiam(){
		return pieceDiam;
	}
	public void setBoardDimensions(int w, int h ){
		double realGapX,realPadY,realPadX;
		if(w >= h){
			boardHeight = h;
			boardWidth = (boardHeight*GameConfig.WIDTH_TO_HEIGHT);
			boardOriginX = (w - boardWidth)/2;
			boardOriginY = 0;
		}
		else{
			boardWidth = w;
			boardHeight = (boardWidth/GameConfig.WIDTH_TO_HEIGHT);
			boardOriginX = 0;
			boardOriginY = (h - boardHeight)/2;
		}
		
		scaleX = boardWidth/GameConfig.ORIG_BOARD_WIDTH;
		scaleY = boardHeight/GameConfig.ORIG_BOARD_HEIGHT;
		pieceDiam = (int)(GameConfig.ORIG_PIECE_DIAM* (scaleX+scaleY)/2);
		realGapX = GameConfig.ORIG_GAP_X*scaleX;
		realGapY = GameConfig.ORIG_GAP_Y*scaleY;
		realPadX = GameConfig.ORIG_PAD_X*scaleX;
		realPadY = GameConfig.ORIG_PAD_Y*scaleY;
		int x,y;
		for(int i=0;i<=Board.NUMX-1;i++){
			x = (int)(boardOriginX+(GameConfig.ORIG_PAD_X+i*GameConfig.ORIG_GAP_X)*scaleX);
			xpos[i]=x;
			centrex[i] = x+(pieceDiam)/2;
		}
		for(int j=0;j<=Board.NUMY-1;j++){
			y = (int)(boardOriginY+(GameConfig.ORIG_PAD_Y+j*GameConfig.ORIG_GAP_Y)*scaleY);
			ypos[j]=y;
		}
	}
}