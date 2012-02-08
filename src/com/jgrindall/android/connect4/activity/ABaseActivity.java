package com.jgrindall.android.connect4.activity;

import com.jgrindall.android.connect4.R;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public abstract class ABaseActivity extends Activity{
	
	
	public ABaseActivity(){
		super();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu m){
		MenuItem m0 = m.add(0,Menu.FIRST,Menu.FIRST,"Settings");
		MenuItem m1 = m.add(0,Menu.FIRST+1,Menu.FIRST+1,"Help");
		
		m0.setIcon(R.drawable.ic_menu_preferences);
		m1.setIcon(R.drawable.ic_menu_help);
		
		return super.onCreateOptionsMenu(m);
	}
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	if(item.getItemId()==Menu.FIRST){
    		Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
    		// settings
    	}
    	else if(item.getItemId()==(Menu.FIRST+1)){
    		Intent intent = new Intent(this, HelpActivity.class);
            startActivity(intent);
            return true;
    		// help
    	}
    	return true;
    }

}