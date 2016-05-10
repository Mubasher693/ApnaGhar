package info.androidhive.apnaghar;

import android.app.Application;

public class Credentials  extends Application{
private String name;
private String email;
private String role;

public String getEmail() {
	return email;
}
public String getName() {
	return name;
}
public String getRole() {
	return role;
}
public void setEmail(String email) {
	this.email = email;
}
public void setName(String name) {
	this.name = name;
}
public void setRole(String role) {
	this.role = role;
}

}
