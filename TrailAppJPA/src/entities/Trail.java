package entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "trail")
public class Trail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String city;
	private String state;
	private String name;

	@Column(name = "api_id")
	private String apiId;

	private String directions;
	private double latitude;
	private double longitude;
	private String description;
	private Double length;

	@Column(name = "image_url")
	private String imageUrl;

	@OneToMany(mappedBy = "trail", cascade = { CascadeType.REMOVE }, fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Report> reports;

	// @Column(name="recent_report_id")
	// private Integer recentReportId;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "recent_report_id")
	private Report recentReport;

	// @ManyToMany(mappedBy="favorites")
	// @JsonIgnore
	// List<User> fans;
	//
	// @PreRemove
	// private void clearFans() {
	// for(User u : fans) {
	// u.getFavorites().remove(this);
	// }
	// }

	@PostLoad 
	private void verifyRecentReport() {
		Report rpt = null;
		if(getRecentReport() == null && reports.size() > 0) {
			rpt = reports.get(0);
			setRecentReport(rpt);
		}
	}
	
	public Trail() {
	}

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

	public Report getRecentReport() {
		return recentReport;
	}

	public void setRecentReport(Report recentReport) {
		if(!reports.contains(recentReport)) {
			reports.add(recentReport);
		}
		
		if(reports.size() > 1) {
			reports.sort((Report r1, Report r2) -> r1.getTimestamp().compareTo(r2.getTimestamp()));
		}
		
		this.recentReport = reports.get(reports.size() - 1);
		
		
//		if (reports.size() > 1) {
////			for (Report r : reports) {
////				System.out.println(r.getTimestamp());
////			}
//			reports.sort((Report r1, Report r2) -> r1.getTimestamp().compareTo(r2.getTimestamp()));
//			this.recentReport = reports.get(reports.size() - 1);
//		} 
//		else {
//			this.recentReport = recentReport;
//		}
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

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
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

	// public List<User> getFans() {
	// return fans;
	// }
	//
	// public void setFans(List<User> fans) {
	// this.fans = fans;
	// }

}
