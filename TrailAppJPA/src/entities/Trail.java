package entities;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Trail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String city;
	private String state;
	private String name;
	private String apiId;
	private String directions;
	private int latitude;
	private int longitude;
	private String description;
	private int length;
	private String imageUrl;
	
	@OneToMany(mappedBy="trail")
	List<TrailReport> reports;

	
	public Trail(){}
	
	public Trail(int id, String city, String state, String name, String apiId, String directions, int latitude,
			int longitude, String description, int length, String imageUrl, List<TrailReport> reports) {
		super();
		this.id = id;
		this.city = city;
		this.state = state;
		this.name = name;
		this.apiId = apiId;
		this.directions = directions;
		this.latitude = latitude;
		this.longitude = longitude;
		this.description = description;
		this.length = length;
		this.imageUrl = imageUrl;
		this.reports = reports;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<TrailReport> getReports() {
		return reports;
	}

	public void setReports(List<TrailReport> reports) {
		this.reports = reports;
	}

	public int getId() {
		return id;
	}
	
	
}
