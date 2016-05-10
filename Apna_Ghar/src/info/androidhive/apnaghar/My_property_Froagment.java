package info.androidhive.apnaghar;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

public class My_property_Froagment extends Activity {
	private static final String TAG = MainActivity.class.getSimpleName();
	
	// Movies json url
	private static final String url = "http://androidtutorial.comxa.com/webservice/my_property.php";
	private ProgressDialog pDialog;
	private List<Property> propertyList = new ArrayList<Property>();
	private ListView listView;
	private CustomListAdapter adapter;
	
	

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_photos);
		ActionBar actionBar = getActionBar();
		   actionBar.setHomeButtonEnabled(true);
		   actionBar.setDisplayHomeAsUpEnabled(true);
	
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#319c34")));
		getActionBar().setDisplayShowHomeEnabled(true);
		getActionBar().setIcon(R.drawable.logo_one);
		getActionBar().setTitle("My Property");
		getActionBar().setDisplayUseLogoEnabled(true);
		getActionBar().setDisplayShowTitleEnabled(true);
        listView = (ListView)findViewById(R.id.list);
		adapter = new CustomListAdapter(this, propertyList);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				
				// TODO Auto-generated method stub
				String bitmap = propertyList.get(position).getThumbnailUrl();
				String title= propertyList.get(position).getTitle();
				String loc= propertyList.get(position).getProperty_location();
				String bed= String.valueOf(propertyList.get(position).getBeds());
				String bath= String.valueOf(propertyList.get(position).getBaths());
				String descrp= propertyList.get(position).getDescriptionn();
				String price= propertyList.get(position).getPrice();
				String propfor= propertyList.get(position).getPropfor();
				
				
				
				 Intent intent = new Intent(getApplicationContext(), Propertsingle.class);  
				 intent.putExtra("images", bitmap);
				 intent.putExtra("title", title);
				 intent.putExtra("location", loc);
				 intent.putExtra("beds", bed);
				 intent.putExtra("baths", bath);
				 intent.putExtra("description", descrp);
				 intent.putExtra("price", price);
				 intent.putExtra("propfor", propfor);
				 
          
                startActivity(intent);
			}
		});

		

		// Creating volley request obj
		JsonArrayRequest movieReq = new JsonArrayRequest(prepareGetMethodUrl(), new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());
						

						// Parsing json
						for (int i = 0; i < response.length(); i++) {
							try {

								JSONObject obj = response.getJSONObject(i);
								Property prop = new Property();
								prop.setTitle(obj.getString("property_type"));
								prop.setThumbnailUrl(obj.getString("property_img"));								
								prop.setBeds(obj.getInt("property_beds"));
								prop.setProperty_location(obj.getString("property_location"));
								prop.setBaths(obj.getInt("property_baths"));
								prop.setPrice(obj.getString("property_price"));
								prop.setDescriptionn(obj.getString("property_description"));
								prop.setPropfor(obj.getString("property_for"));
								// adding prop to property array
								propertyList.add(prop);

							} catch (JSONException e) {
								e.printStackTrace();
							}

						}

						// notifying list adapter about data changes
						// so that it renders the list view with updated data
						adapter.notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						

					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(movieReq);
	
	
	}

	private String prepareGetMethodUrl() {
		// TODO Auto-generated method stub
	// TODO Auto-generated method stub
//		String useremail ="mubasher@gmail.com";
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		//String useremail = sharedPreferences.getString("email", null);
		//String userroles = sharedPreferences.getString("role", null);
	return url+"?useremail="+sharedPreferences.getString("email", null);
	}
	


private void hidePDialog() {
	if (pDialog != null) {
		pDialog.dismiss();
		pDialog = null;
	}
}
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
	        startActivity(new Intent(My_property_Froagment.this, Propertsingle.class));  
	        // go to previous activity
//	        Intent inte = new Intent(Propertsingle.this,PhotosFragment.class);
//	        startActivity(inte);
//	        
	        
	        this.finish();
	        return true;

	    }

	    return super.onOptionsItemSelected(item);
	}
}
