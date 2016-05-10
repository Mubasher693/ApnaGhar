package info.androidhive.apnaghar;

public class Property {
	private String title, thumbnailUrl , price ,descriptionn ,propfor;
	private int beds;
	private int baths;
	
	private String property_location;
	
	
	public Property() {
	}

	public Property(String name, String thumbnailUrl, int beds,String property_location ,int baths ,String price,String descriptionn,String propfor) {
		this.title = name;
		this.thumbnailUrl = thumbnailUrl;
		this.beds = beds;
		this.property_location=property_location;
		this.baths=baths;
		this.price=price;
		this.descriptionn=descriptionn;
		this.propfor=propfor;
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public int getBeds() {
		return beds;
	}

	public void setBeds(int beds) {
		this.beds = beds;
	}
	public String getProperty_location() {
		return property_location;
	}
	public void setProperty_location(String property_location) {
		this.property_location = property_location;
	}
	public int getBaths() {
	return baths;
	}
	public void setBaths(int baths) {
	this.baths = baths;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPropfor() {
		return propfor;
	}
	public void setPropfor(String propfor) {
		this.propfor = propfor;
	}
	public String getDescriptionn() {
		return descriptionn;
	}
	public void setDescriptionn(String descriptionn) {
		this.descriptionn = descriptionn;
	}

	
	

}
