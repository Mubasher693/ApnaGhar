package info.androidhive.apnaghar;

import android.app.Fragment;
import android.widget.EditText;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import info.androidhive.apnaghar.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
public class SignupFragment extends Fragment{
	
	  private EditText editTextUserName;
	    private EditText editTextEmail;
	    private EditText editTextPassword;
	    private Spinner spiner;
	 Button login;
	    public static final String USER_NAME = "USERNAME";
	 
	    String username;
	    String useremail;
	    String password;
	    String userrole;
	    String role;
	    View rootView ;
	public SignupFragment() {
	// TODO Auto-generated constructor stub
}
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
	         rootView = inflater.inflate(R.layout.fragment_signup, container, false);

	  login=(Button)rootView.findViewById(R.id.button);
	  login.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
	       
			editTextUserName=(EditText)rootView.findViewById(R.id.editTextUserName);
			editTextPassword=(EditText)rootView.findViewById(R.id.editTextPassword);
			editTextEmail=(EditText)rootView.findViewById(R.id.editTextEmail);
			spiner=(Spinner)rootView.findViewById(R.id.spinner);
			
			
			
			
			
			 username = editTextUserName.getText().toString();
		        if(TextUtils.isEmpty(username)) {
		            Toast.makeText(getActivity(), "Please enter your name ", Toast.LENGTH_SHORT).show();
		            return;
		        }
		        useremail= editTextEmail.getText().toString();
		        if(TextUtils.isEmpty(useremail)) {
		            Toast.makeText(getActivity(), "Please enter your email ", Toast.LENGTH_SHORT).show();
		            return;
		        }
		        password = editTextPassword.getText().toString();
		        if(TextUtils.isEmpty(password)) {
		            Toast.makeText(getActivity(), "Please enter your password ", Toast.LENGTH_SHORT).show();
		            return;
		        }
		        userrole=spiner.getSelectedItem().toString();
		        String role=userrole.toString(); 
		        if(TextUtils.isEmpty(role) || role=="Select Role") {
		            Toast.makeText(getActivity(), "Please enter your password ", Toast.LENGTH_SHORT).show();
		            return;
		        }
		        if(role=="" || useremail=="" || password=="" || role=="" || role=="Select Role"){
		        	 Toast.makeText(getActivity(), "Please enter full information ", Toast.LENGTH_SHORT).show();
		             return;
		        }else{
		        	 login(username,useremail,password,role);
		        } 
		       
		        
		      
		}
	});
		      
		 
		    
	        return rootView;
	    }
	
    public void invokeLogin(View view){
        username = editTextUserName.getText().toString();
        password = editTextPassword.getText().toString();
        useremail= editTextEmail.getText().toString();
        spiner=(Spinner)rootView.findViewById(R.id.spinner);
         role=userrole.toString(); 
        login(username,useremail,password,role);
 
    }
 
    private void login(final String username,final String useremail, String password,final String userrole) {
 
        class LoginAsync extends AsyncTask<String, Void, String>{
 
            private Dialog loadingDialog;
 
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(getActivity(), "Please wait", "Loading...");
            }
 
            @Override
            protected String doInBackground(String... params) {
                String uname = params[0];
                String email = params[1];
                String pass = params[2];
                String role = params[3];
 
                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("username", uname));
                nameValuePairs.add(new BasicNameValuePair("useremail", email));
                nameValuePairs.add(new BasicNameValuePair("password", pass));
                nameValuePairs.add(new BasicNameValuePair("userrole", role));
                String result = null;
 
                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    //http://androidtutorial.comxa.com/webservice/Signuplive.php
                    //http://10.0.2.2/webservice/Signup.php
                    HttpPost httpPost = new HttpPost("http://androidtutorial.comxa.com/webservice/Signuplive.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
 
                    HttpResponse response = httpClient.execute(httpPost);
 
                    HttpEntity entity = response.getEntity();
 
                    is = entity.getContent();
 
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();
 
                    
                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }
 
            @Override
            protected void onPostExecute(String result){
                String s = result.trim();
                loadingDialog.dismiss();
                if(s.equalsIgnoreCase("success")){
                  SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                	SharedPreferences.Editor editor = sharedPreferences.edit();
                	editor.putString("email", useremail);
                	editor.putString("role", userrole);
                	editor.commit();
                	
                	String email = sharedPreferences.getString("email", null);
                	String roles = sharedPreferences.getString("role", null);
                	
                	if(roles.equals("Seller")){
                    Intent intent = new Intent(getActivity(), AddpropertyFragment.class);
                
                    startActivity(intent);
                	Toast.makeText(getActivity(), "Add Property", Toast.LENGTH_LONG).show();
                	}
                	else{
//                	    HomeFragment nextFrag= new HomeFragment();
//                	    getActivity().getFragmentManager().beginTransaction()
//                	    .replace(R.id.frame_container, nextFrag, null)
//                	    .addToBackStack(null)
//                	    .commit();  
//	                	Toast.makeText(getActivity(), "Home", Toast.LENGTH_LONG).show();
                		Intent intent = new Intent(getActivity(), MainActivity.class);
    	                
	                    startActivity(intent);
                	}
                }else {
                    Toast.makeText(getActivity(), s + "Email Already Registered", Toast.LENGTH_LONG).show();
                }
            }
        }
 
        LoginAsync la = new LoginAsync();
        la.execute(username,useremail,password,userrole);
 
    }
 
 
    
 
    
}
