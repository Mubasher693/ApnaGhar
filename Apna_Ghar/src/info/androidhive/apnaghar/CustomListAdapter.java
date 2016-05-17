package info.androidhive.apnaghar;


import info.androidhive.apnaghar.R;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;


public class CustomListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<Property> propertyItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomListAdapter(Activity activity, List<Property> propertyItems) {
		this.activity = activity;
		this.propertyItems = propertyItems;
	}

	@Override
	public int getCount() {
		return propertyItems.size();
	}

	@Override
	public Object getItem(int location) {
		return propertyItems.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.list_row, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);
		TextView title = (TextView) convertView.findViewById(R.id.title);
		TextView beds = (TextView) convertView.findViewById(R.id.beds);
		TextView location = (TextView) convertView.findViewById(R.id.location);
		TextView baths = (TextView) convertView.findViewById(R.id.baths);
		TextView price = (TextView) convertView.findViewById(R.id.price);
		TextView descriptionn = (TextView) convertView.findViewById(R.id.descriptionn);
		TextView propfor = (TextView) convertView.findViewById(R.id.propfor);
		TextView propcon = (TextView) convertView.findViewById(R.id.contactno);
		// getting movie data for the row
		Property m = propertyItems.get(position);

		// thumbnail image
		thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
		// title
		title.setText("Type : "+ m.getTitle());
		// beds
		beds.setText(String.valueOf("Bed Room : "+m.getBeds()));
		//location
		location.setText(String.valueOf("Location : "+m.getProperty_location()));
		//baths
		baths.setText(String.valueOf("Bath Room : "+m.getBaths()));
		// price
		price.setText("Price : "+ m.getPrice());
		// desc
		descriptionn.setText("Description : "+ m.getDescriptionn());
		// for
		propfor.setText("For : "+ m.getPropfor());
		//contact
		propcon.setText("Contact no : "+ m.getPopcontact());
		
		return convertView;
	}

}