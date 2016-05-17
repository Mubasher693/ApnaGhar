package info.androidhive.apnaghar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import info.androidhive.apnaghar.R;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;


public class PropertyFragment extends Fragment {
	private static final String TAG = MainActivity.class.getSimpleName();

	// Movies json url
	private static final String url = "http://androidtutorial.comxa.com/webservice/propertyvolly.php";
	private ProgressDialog pDialog;
	private List<Property> propertyList = new ArrayList<Property>();
	private ListView listView;
	private CustomListAdapter adapter;
	public PropertyFragment(){}

	
	
	

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_photos, container, false);
        listView = (ListView)rootView. findViewById(R.id.list);
		adapter = new CustomListAdapter(getActivity(), propertyList);
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
				String propcon= propertyList.get(position).getPopcontact();
				 Intent intent = new Intent(getActivity(), Propertsingle.class);  
				 intent.putExtra("images", bitmap);
				 intent.putExtra("title", title);
				 intent.putExtra("location", loc);
				 intent.putExtra("beds", bed);
				 intent.putExtra("baths", bath);
				 intent.putExtra("description", descrp);
				 intent.putExtra("price", price);
				 intent.putExtra("propfor", propfor);
				 intent.putExtra("propcont", propcon);
				startActivity(intent);
			}
		});

		

		// Creating volley request obj
		JsonArrayRequest propReq = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {
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
								prop.setPopcontact(obj.getString("user_no"));
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
		AppController.getInstance().addToRequestQueue(propReq);
	
	
        return rootView;
	}

private void hidePDialog() {
	if (pDialog != null) {
		pDialog.dismiss();
		pDialog = null;
	}
}

}
