package info.androidhive.apnaghar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Logout extends Activity{
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	
	Intent intent = new Intent(this, MainActivity.class);
	startActivity(intent);
}
}
