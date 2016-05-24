package info.androidhive.apnaghar;

import info.androidhive.apnaghar.R;
import android.R.bool;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class Intentclass extends Activity {
	RelativeLayout back;
	 Boolean clickbtn = false;
	@SuppressLint("NewApi")
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_intetn);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#319c34")));
	bar.setDisplayShowHomeEnabled(true);
		bar.setIcon(R.drawable.logo_one);
		back=(RelativeLayout)findViewById(R.id.backscr);
		
		OnClickListener listener = new OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    
		        if (v.equals(back)) {
		            // do something
		        	Intent intentobj = new Intent(Intentclass.this,MainActivity.class); 
					
					startActivity(intentobj);
					clickbtn=true;
		        }
		    }
		};
		
		if(clickbtn){
			Thread timer = new Thread(){
    			public void run() {
    			try{ 
    				sleep(3000);
    			}
    			catch(InterruptedException e){ e.printStackTrace(); }
    			finally{
    				Intent intentobj = new Intent(Intentclass.this,MainActivity.class); 
    				startActivity(intentobj);
    				
    			}
    			}
    		};
    		timer.start();
		}

back.setOnClickListener(listener);
//		back.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				Intent intentobj = new Intent(Intentclass.this,MainActivity.class); 
//				
//				startActivity(intentobj);
//				
//			}
//		});
		
	}

	

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
////		// Handle action bar item clicks here. The action bar will
////		// automatically handle clicks on the Home/Up button, so long
////		// as you specify a parent activity in AndroidManifest.xml.
////		int id = item.getItemId();
////		if (id == R.id.action_settings) {
////			return true;
////		}
//		 switch (item.getItemId()) {
//
//		    case android.R.id.home:
//
//		        Toast.makeText(getApplicationContext(), "Home Clicked",
//		                Toast.LENGTH_LONG).show();
//
//		        // go to previous activity
//		        onBackPressed();
//
//		        return true;
//
//		    }
//		return super.onOptionsItemSelected(item);
//	}
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
