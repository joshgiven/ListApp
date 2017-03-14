package entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="trail")
public class Trail {

	public Integer getRecentReportId() {
		return recentReportId;
	}

	public void setRecentReportId(Integer recentReportId) {
		this.recentReportId = recentReportId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String city;
	private String state;
	private String name;

	@Column(name="api_id")
	private String apiId;

	private String directions;
	private int latitude;
	private int longitude;
	private String description;
	private Double length;

	@Column(name="image_url")
	private String imageUrl;

	@OneToMany(mappedBy="trail", cascade={CascadeType.REMOVE})
	@JsonIgnore
	private List<Report> reports;

	@Column(name="recent_report_id")
	private Integer recentReportId;

	@ManyToMany(mappedBy="favorites")
	@JsonIgnore
	List<User> fans;

	@PreRemove
	private void clearFans() {
		for(User u : fans) {
			u.getFavorites().remove(this);
		}
	}

	public Trail(){}

	public Trail(int id, String city, String state, String name, String apiId, String directions, int latitude,
			int longitude, String description, Double length, String imageUrl, List<Report> reports) {
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

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public int getId() {
		return id;
	}

	public List<User> getFans() {
		return fans;
	}

	public void setFans(List<User> fans) {
		this.fans = fans;
	}

}
