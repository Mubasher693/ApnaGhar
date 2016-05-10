package info.androidhive.apnaghar;

import info.androidhive.apnaghar.R;

import java.io.ByteArrayOutputStream;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class AddpropertyFragment extends Activity {
    ProgressDialog prgDialog;
    String encodedString;
    RequestParams params = new RequestParams();
    String imgPath, fileName;
    Bitmap bitmap;
    EditText name,pbed,pbath,parea,pdescription,paddress,pprice;
    Spinner propfr,proptyp,pcity;
    String username,beds,baths,propfor,proptype,area,city,description,address,price;
    private static int RESULT_LOAD_IMG = 1;
 
    @SuppressLint({ "NewApi", "ShowToast" })
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_addproperty);
        ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#319c34")));
		bar.setHomeButtonEnabled(true);
		bar.setDisplayShowHomeEnabled(true);
		bar.setIcon(R.drawable.logo_one);
		bar.setTitle("Add Property") ;
		
		
		//Toast.makeText(getApplicationContext(), objnew.getEmail().toString(), Toast.LENGTH_LONG);
        name = (EditText) findViewById(R.id.name);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    	SharedPreferences.Editor editor = sharedPreferences.edit();
    	
    	editor.commit();
    	
    	String email = sharedPreferences.getString("email", null);
    	
        // When Image is selected from Gallery
    	name.setText(email);
       
       
        pbed = (EditText) findViewById(R.id.bed);
        pbath = (EditText) findViewById(R.id.bath);
        propfr = (Spinner) findViewById(R.id.contract);
        proptyp = (Spinner) findViewById(R.id.ptype);
        parea = (EditText) findViewById(R.id.area);
        pcity = (Spinner) findViewById(R.id.city);
        pdescription = (EditText) findViewById(R.id.discription);
        paddress = (EditText) findViewById(R.id.address);
        pprice = (EditText) findViewById(R.id.price);
        prgDialog = new ProgressDialog(this);
        // Set Cancelable as False
        prgDialog.setCancelable(false);
        
    }
 
    public void loadImagefromGallery(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }
 
    // When Image is selected from Gallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data
 
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
 
                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();
 
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgPath = cursor.getString(columnIndex);
                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.imgView);
                // Set the Image in ImageView
                imgView.setBackgroundResource(0);
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(imgPath));
                // Get the Image's file name
                String fileNameSegments[] = imgPath.split("/");
                fileName = fileNameSegments[fileNameSegments.length - 1];
                
                // Put file name in Async Http Post Param which will used in Php web app
                params.put("filename", fileName);
                username=name.getText().toString();
              //EditText name,pbed,pbath,parea,pdescription,paddress,pprice;
                //Spinner propfr,proptyp,pcity;
                //String username,beds,baths,propfor,proptype,area,city,description,address,price;
                beds = pbed.getText().toString();
            	baths = pbath.getText().toString();
            	propfor = propfr.getSelectedItem().toString();
            	proptype = proptyp.getSelectedItem().toString();
            	area = parea.getText().toString();
            	city = pcity.getSelectedItem().toString();
            	description = pdescription.getText().toString();
            	address = paddress.getText().toString();
            	price = pprice.getText().toString();
                params.put("name", username);
                
                params.put("bed", beds);
                params.put("bath", baths);
                params.put("ptype", proptype);
                params.put("pfor", propfor);
                params.put("area", area);
                params.put("city", city);
                params.put("address", address);
                params.put("price", price);
                params.put("description", description);
 
            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }
 
    }
     
    // When Upload button is clicked
    public void uploadImage(View v) {
    	
    	
    	username = name.getText().toString();
    	beds = pbed.getText().toString();
    	baths = pbath.getText().toString();
    	propfor = propfr.getSelectedItem().toString();
    	proptype = proptyp.getSelectedItem().toString();
    	area = parea.getText().toString();
    	city = pcity.getSelectedItem().toString();
    	description = pdescription.getText().toString();
    	address = paddress.getText().toString();
    	price = pprice.getText().toString();
        if(TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Please enter your name ", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(address)) {
            Toast.makeText(this, "Please Enter Address ", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(area)) {
            Toast.makeText(this, "Please Enter Area (Sq ft) ", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(city)) {
            Toast.makeText(this, "Please Select City ", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(beds)) {
            Toast.makeText(this, "Please enter Bed Room ", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(baths)) {
            Toast.makeText(this, "Please enter Bath Room ", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(price)) {
            Toast.makeText(this, "Please Enter Price ($) ", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(propfor)) {
            Toast.makeText(this, "Please Select Property For ", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(proptype)) {
            Toast.makeText(this, "Please Select Property Type ", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(description)) {
            Toast.makeText(this, "Please Enter Description ", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
        if (imgPath != null && !imgPath.isEmpty()) {
        	//username = name.getText().toString();
            prgDialog.setMessage("Converting Image to Binary Data");
            prgDialog.show();
            // Convert image to String using Base64
            encodeImagetoString();
        // When Image is not selected from Gallery
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "You must select image from gallery before you try to upload",
                    Toast.LENGTH_LONG).show();
        }
        }
    }
 
    // AsyncTask - To convert Image to String
    public void encodeImagetoString() {
        new AsyncTask<Void, Void, String>() {
 
            protected void onPreExecute() {
 
            };
 
            @Override
            protected String doInBackground(Void... params) {
            	
                BitmapFactory.Options options = null;
                options = new BitmapFactory.Options();
                options.inSampleSize = 3;
                bitmap = BitmapFactory.decodeFile(imgPath,
                        options);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                // Must compress the Image to reduce image size to make upload easy
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream); 
                byte[] byte_arr = stream.toByteArray();
                // Encode Image to String
                encodedString = Base64.encodeToString(byte_arr, 0);
                return "";
            }
 
            @Override
            protected void onPostExecute(String msg) {
                prgDialog.setMessage("Calling Upload");
                // Put converted Image string into Async Http Post param
                params.put("image", encodedString);
                
                // Trigger Image upload
                triggerImageUpload();
            }
        }.execute(null, null, null);
    }
     
    public void triggerImageUpload() {
    	 
        makeHTTPCall();
         
    }
 
    // Make Http call to upload Image to Php server
    public void makeHTTPCall() {
        prgDialog.setMessage("Invoking Php");       
        AsyncHttpClient client = new AsyncHttpClient();
        // Don't forget to change the IP address to your LAN address. Port no as well.
        //http://androidtutorial.comxa.com/imgupload/upload_image.php
        //http://localhost/imgupload/upload_image.php
        //http://androidtutorial.comxa.com/upload_image.php
        client.post("http://androidtutorial.comxa.com/upload_image.php",
                params, new AsyncHttpResponseHandler() {
                    // When the response returned by REST has Http
                    // response code '200'
                    @Override
                    
                    public void onSuccess(String response) {
                        // Hide Progress Dialog
                        prgDialog.hide();
                        Toast.makeText(getApplicationContext(), response,
                                Toast.LENGTH_LONG).show();
                    }
 
                    // When the response returned by REST has Http
                    // response code other than '200' such as '404',
                    // '500' or '403' etc
                    @Override
                    public void onFailure(int statusCode, Throwable error,
                            String content) {
                        // Hide Progress Dialog
                        prgDialog.hide();
                        // When Http response code is '404'
                        if (statusCode == 404) {
                            Toast.makeText(getApplicationContext(),
                                    "Requested resource not found",
                                    Toast.LENGTH_LONG).show();
                        }
                        // When Http response code is '500'
                        else if (statusCode == 500) {
                            Toast.makeText(getApplicationContext(),
                                    "Something went wrong at server end",
                                    Toast.LENGTH_LONG).show();
                        }
                        // When Http response code other than 404, 500
                        else {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Error Occured n Most Common Error: n1. Device not connected to Internetn2. Web App is not deployed in App servern3. App server is not runningn HTTP Status code : "
                                            + statusCode, Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                });
    }
 
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        // Dismiss the progress bar when application is closed
        if (prgDialog != null) {
            prgDialog.dismiss();
        }
    }
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

	        Toast.makeText(getApplicationContext(), "Home",
	                Toast.LENGTH_LONG).show();

	        // go to previous activity
	        onBackPressed();
	        this.finish();
	        return true;

	    }

	    return super.onOptionsItemSelected(item);
	}
//    protected void onPause() {
//		// TODO Auto-generated method stub
//		super.onPause();
//		finish();
//		
//	}
}