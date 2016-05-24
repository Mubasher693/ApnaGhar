package info.androidhive.apnaghar;


import info.androidhive.apnaghar.R;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;


public class Propertsingle extends Activity {

	Button myprop;
	Button allprop;
	EditText cmnt;
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@SuppressLint({ "CutPasteId", "NewApi" })
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_prop_single);
       ActionBar actionBar = getActionBar();
		   actionBar.setHomeButtonEnabled(true);
		   actionBar.setDisplayHomeAsUpEnabled(true);
	
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#319c34")));
		getActionBar().setDisplayShowHomeEnabled(true);
		getActionBar().setIcon(R.drawable.logo_one);
		getActionBar().setTitle("Property");
		getActionBar().setDisplayUseLogoEnabled(true);
		getActionBar().setDisplayShowTitleEnabled(true);

		
		TextView type;
		TextView loc;
		TextView bed;
		TextView bath;
		TextView descriptions;
		TextView pfor;
		TextView prices;
		TextView nodrwingroom;
		TextView sandcabel;
		TextView intercom;
		TextView pcon;
		ImageView imge;
		
		type=(TextView)findViewById(R.id.types);
		loc=(TextView)findViewById(R.id.locations);
		bed=(TextView)findViewById(R.id.beds);
		bath=(TextView)findViewById(R.id.baths);
		descriptions=(TextView)findViewById(R.id.descs);
		pfor=(TextView)findViewById(R.id.prics);
		prices=(TextView)findViewById(R.id.pfors);
		nodrwingroom=(TextView)findViewById(R.id.nodrwingroom);
		intercom=(TextView)findViewById(R.id.intercom);
		sandcabel=(TextView)findViewById(R.id.sandcabel);
		pcon=(TextView)findViewById(R.id.contactno);
		imge=(ImageView)findViewById(R.id.thumbnail);
		//cmnt= (EditText)findViewById(R.id.comments);
		// Get the intent from ListViewAdapter
		 Intent i=getIntent();
		 String name = i.getStringExtra("title");
		 String locat = i.getStringExtra("location");
		 String beds = i.getStringExtra("beds");
		 String baths = i.getStringExtra("baths");
		 String description = i.getStringExtra("description");
		 String price = i.getStringExtra("price");
		 String propfor = i.getStringExtra("propfor");
		 String cnumber = i.getStringExtra("propcont");
		 ImageLoader imageLoader = AppController.getInstance().getImageLoader();
		 String bitmap = i.getStringExtra("images");
		 ((NetworkImageView) imge).setImageUrl(bitmap, imageLoader);
		 type.setText("Type : "+name);
		 loc.setText("Location : "+locat);
		 bed.setText("Bed Room : "+beds);
		 bath.setText("Bath Room : "+baths);
		 descriptions.setText("Description : "+description);
		 prices.setText("Price : "+price);
		 pfor.setText("For : "+propfor);
		 nodrwingroom.setText("Drawing Room : "+ "YES");
		 sandcabel.setText("Satellite Or Cable TV Ready : "+ "YES");
		 intercom.setText("Intercom : "+ "YES");
		 pcon.setText("Contact no : "+ cnumber);
		 myprop=(Button)findViewById(R.id.my_prop);
		 allprop=(Button)findViewById(R.id.all_prop);
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		 String roles = sharedPreferences.getString("role", null);
		 
		 if( roles ==null || roles !="Seller"){
				
			 myprop.setVisibility(View.GONE);
			 LinearLayout.LayoutParams params = (LayoutParams) allprop.getLayoutParams();
			 params.width = 100;
			 allprop.setLayoutParams(params); 
		 }
		 
		 if(roles != null && roles.equals("Seller") ){
     		 myprop.setVisibility(View.VISIBLE);
			 myprop.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Intent intent= new Intent(Propertsingle.this,My_property_Froagment.class);
						startActivity(intent);
						
						
					}
				});
			
		 }
		 
		
		
		 
		
		allprop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				Intent openFragmentBIntent = new Intent(getApplicationContext(), MainActivity.class);
				openFragmentBIntent.putExtra("PropertyFragment","Prop");
				startActivity(openFragmentBIntent);
            	Toast.makeText(getApplicationContext(), "All Property", Toast.LENGTH_LONG).show();
			}
		});
    }
	
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.signupactivitys, menu);
//        return true;
//    }
// 
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		//int id = item.getItemId();
		//if (id == R.id.action_settings) {
		//	return true;
		//}
		//return super.onOptionsItemSelected(item);

	    switch (item.getItemId()) {

	    case android.R.id.home:

	        Toast.makeText(getApplicationContext(), "Privious",
	                Toast.LENGTH_LONG).show();
	        startActivity(new Intent(Propertsingle.this, MainActivity.class));  
	        // go to previous activity
//	        Intent inte = new Intent(Propertsingle.this,PhotosFragment.class);
//	        startActivity(inte);
//	        
	        
	        this.finish();
	        return true;

	    }

	    return super.onOptionsItemSelected(item);
	}
    public void onBackPressed(){
    	 startActivity(new Intent(Propertsingle.this, MainActivity.class));
    }
  
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
