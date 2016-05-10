package info.androidhive.apnaghar;


import info.androidhive.apnaghar.R;
import android.app.Fragment;
import android.os.Bundle;
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
		signup.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
			
				Toast.makeText(getActivity(), "Signup",Toast.LENGTH_LONG ).show();
				Fragment fragment = null;
		        
                fragment = new SignupFragment();
                replaceFragment(fragment);
			}
		});
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
    	//String email = sharedPreferences.getString("email", null);
    	if(sharedPreferences.getString("email",null)!=null){
    		login=(Button)rootView. findViewById(R.id.btnlogin);
    		login.setText("Logout");
    		login.setOnClickListener(new OnClickListener(){
    			public void onClick(View arg0) {
    				
    				
    				SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
    				sharedPreferences.edit().clear().commit();
    				Intent intent = new Intent(getActivity(), Logout.class);
	                startActivity(intent); 
	                	//Toast.makeText(getActivity(), "Logout Success", Toast.LENGTH_LONG).show();
	                	//login.setText("Login");
    			}
    		});	
    		
    	}
    	else{
    		login=(Button)rootView. findViewById(R.id.btnlogin);
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
}
