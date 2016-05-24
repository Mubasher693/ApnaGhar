package info.androidhive.apnaghar;


import info.androidhive.apnaghar.R;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HomeFragment extends Fragment {
	Button search;
	Button signup;
	Button login;
	public HomeFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
       // Bundle bundle = this.getArguments();
       // String roles = bundle.getString("rol"); 
        search=(Button)rootView. findViewById(R.id.btnsearch);

		search.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				Toast.makeText(getActivity(), "Search",Toast.LENGTH_LONG ).show();
//				Intent intent= new Intent(getActivity(),SearchActivity.class);
//				startActivity(intent);
				Fragment fragment = null;
		        
		                fragment = new PropertyFragment();
		                replaceFragment(fragment);
		              

			      
			}
		});
		signup=(Button)rootView. findViewById(R.id.btnsignup);
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
		String email = sharedPreferences.getString("email", null);
		
    	String roles = sharedPreferences.getString("role", null);
    	
    
    	
		if( roles ==null || roles !="Seller"){
			signup.setOnClickListener(new OnClickListener(){
				public void onClick(View arg0) {
				
					Toast.makeText(getActivity(), "Signup",Toast.LENGTH_LONG ).show();
					Fragment fragment = null;
			        
	                fragment = new SignupFragment();
	                replaceFragment(fragment);
				}
			});
		
		}
		 if(roles != null && roles.equals("Seller") ){

			signup.setText("Add Property");
			signup.setOnClickListener(new OnClickListener(){
				public void onClick(View arg0) {
			Intent intent = new Intent(getActivity(), AddpropertyFragment.class);
			startActivity(intent);
        	Toast.makeText(getActivity(), "Add Property", Toast.LENGTH_LONG).show();
				}
			});
		}
		//SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
    	//String email = sharedPreferences.getString("email", null);
    	if(sharedPreferences.getString("email",null)!=null && roles.equals("Seller") ){
    		login=(Button)rootView. findViewById(R.id.btnlogin);
    		login.setText("My Property");
    		login.setOnClickListener(new OnClickListener(){
    			public void onClick(View arg0) {
    				Toast.makeText(getActivity(), "Add Property", Toast.LENGTH_LONG).show();
    				Intent intent = new Intent(getActivity(), My_property_Froagment.class);
	                startActivity(intent); 
	                	//Toast.makeText(getActivity(), "Logout Success", Toast.LENGTH_LONG).show();
	                	//login.setText("Login");
    			}
    		});	
    		
    	}
    	else if(sharedPreferences.getString("email",null)!=null && roles !="Seller" ){
    		login=(Button)rootView. findViewById(R.id.btnlogin);
    		login.setText("Logout");
    		login.setOnClickListener(new OnClickListener(){
    			public void onClick(View arg0) {
    				Toast.makeText(getActivity(), "Logout",Toast.LENGTH_LONG ).show();
    				SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
    				sharedPreferences.edit().clear().commit();
    				Intent intent = new Intent(getActivity(), Logout.class);
	                startActivity(intent); 
    			
    			}
    		});	
    	}
    	else if(sharedPreferences.getString("email",null)==null && roles ==null ){
    		login=(Button)rootView. findViewById(R.id.btnlogin);
    		login.setText("Login");
    		login.setOnClickListener(new OnClickListener(){
    			public void onClick(View arg0) {
    				Toast.makeText(getActivity(), "Login",Toast.LENGTH_LONG ).show();
    		Fragment fragment = null;	        
            fragment = new LoginFragment();
            replaceFragment(fragment);
    			}
    		});	
    	}
		
        return rootView;
    }
	 public void replaceFragment(Fragment someFragment) {
	        android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
	        transaction.replace(R.id.frame_container, someFragment);
	        transaction.addToBackStack(null);
	        transaction.commit();
	    }
	 
//	public void onBackPressed(){
//		 Intent intent = new Intent(getActivity(), MainActivity.class);
//	        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//	        startActivity(intent);
//	        getActivity().finish();
//	}
	 
//	 int backButtonCount;
//		public void onBackPressed()
//		{
//		    if(backButtonCount >= 1)
//		    {
//		        Intent intent = new Intent(Intent.ACTION_MAIN);
//		        intent.addCategory(Intent.CATEGORY_HOME);
//		        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		        startActivity(intent);
//		    }
//		    else
//		    {
//		        Toast.makeText(this.getActivity(), "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
//		        backButtonCount++;
//		    }
//		}
//	 
//	 public boolean onKeyUp(int keyCode, KeyEvent event) {
//		    boolean back = false;
//		    if(keyCode == KeyEvent.KEYCODE_BACK){
//		    	Toast.makeText(getActivity(), "Exit", Toast.LENGTH_LONG).show();
//		    	Intent intent = new Intent(Intent.ACTION_MAIN);
//		        intent.addCategory(Intent.CATEGORY_HOME);
//		        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		        startActivity(intent);
//		        //backStack();
//		    }
//		    return back;
//
//		}
		
	
}
