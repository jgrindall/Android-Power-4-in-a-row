package com.jgrindall.android.connect4.utils;

public class PowerBall{
	
	private boolean hasBeenPlayed = false;
	private boolean inUse = false;
	private boolean justPlayed = false;
	
	public boolean getIsPlayed(){
		return hasBeenPlayed;
	}
	public boolean getInUse(){
		return inUse;
	}
	public boolean getJustPlayed(){
		return justPlayed;
	}
	public void setHasBeenPlayed(boolean b){
		hasBeenPlayed=b;
	}
	public void setInUse(boolean b){
		inUse=b;
	}
	public void setJustPlayed(boolean b){
		justPlayed=b;
	}
	public boolean playNow(){
		return (inUse && !hasBeenPlayed && Math.random()<GameConfig.POWER_RAND);
	}
	public void reset(){
		hasBeenPlayed = false;
		justPlayed = false;
	}
}