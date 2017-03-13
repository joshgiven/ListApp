package entities;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class TrailReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="trail_id")
	private Trail trail;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	private String heading;
	private String comment;
	private Date timestamp;
	
	//conditions
	private Boolean deepSnow;
	private Boolean lightSnow;
	private Boolean veryMuddy;
	private Boolean lightMud;
	private Boolean dry;
	private Boolean impassable;
	private Boolean closed;
	private Boolean icy;
	
	
	public TrailReport(){}
	
	public TrailReport(int id, Trail trail, User user, String heading, String comment, Date timestamp, Boolean deepSnow,
			Boolean lightSnow, Boolean veryMuddy, Boolean lightMud, Boolean dry, Boolean impassable, Boolean closed,
			Boolean icy) {
		super();
		this.id = id;
		this.trail = trail;
		this.user = user;
		this.heading = heading;
		this.comment = comment;
		this.timestamp = timestamp;
		this.deepSnow = deepSnow;
		this.lightSnow = lightSnow;
		this.veryMuddy = veryMuddy;
		this.lightMud = lightMud;
		this.dry = dry;
		this.impassable = impassable;
		this.closed = closed;
		this.icy = icy;
	}

	public Trail getTrail() {
		return trail;
	}
	public void setTrail(Trail trail) {
		this.trail = trail;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Boolean getDeepSnow() {
		return deepSnow;
	}
	public void setDeepSnow(Boolean deepSnow) {
		this.deepSnow = deepSnow;
	}
	public Boolean getLightSnow() {
		return lightSnow;
	}
	public void setLightSnow(Boolean lightSnow) {
		this.lightSnow = lightSnow;
	}
	public Boolean getVeryMuddy() {
		return veryMuddy;
	}
	public void setVeryMuddy(Boolean veryMuddy) {
		this.veryMuddy = veryMuddy;
	}
	public Boolean getLightMud() {
		return lightMud;
	}
	public void setLightMud(Boolean lightMud) {
		this.lightMud = lightMud;
	}
	public Boolean getDry() {
		return dry;
	}
	public void setDry(Boolean dry) {
		this.dry = dry;
	}
	public Boolean getImpassable() {
		return impassable;
	}
	public void setImpassable(Boolean impassable) {
		this.impassable = impassable;
	}
	public Boolean getClosed() {
		return closed;
	}
	public void setClosed(Boolean closed) {
		this.closed = closed;
	}
	public Boolean getIcy() {
		return icy;
	}
	public void setIcy(Boolean icy) {
		this.icy = icy;
	}
	public int getId() {
		return id;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
