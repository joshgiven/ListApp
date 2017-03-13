package entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="report")
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "trail_id")
	private Trail trail;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private String heading;
	private String comment;
	private Date timestamp;

	// conditions
	@ManyToMany
	@JoinTable(name = "report_status", joinColumns = @JoinColumn(name = "report_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "status_id", referencedColumnName = "id"))
	private List<Status> condition;

	public Report() {
	}

	public Report(int id, Trail trail, User user, String heading, String comment, Date timestamp,
			List<Status> condition) {
		super();
		this.id = id;
		this.trail = trail;
		this.user = user;
		this.heading = heading;
		this.comment = comment;
		this.timestamp = timestamp;
		this.condition = condition;
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

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public List<Status> getCondition() {
		return condition;
	}

	public void setCondition(List<Status> condition) {
		this.condition = condition;
	}

	public int getId() {
		return id;
	}

}
