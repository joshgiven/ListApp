package entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


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
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
	private Date timestamp;

	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name = "t_status_has_report", 
	joinColumns = @JoinColumn(name = "report_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "t_status_id", referencedColumnName = "id"))
	private List<TStatus> tstatuses;
		
	
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Report [id=");
		builder.append(id);
		builder.append(", trail=");
		builder.append(trail);
		builder.append(", user=");
		builder.append(user);
		builder.append(", heading=");
		builder.append(heading);
		builder.append(", comment=");
		builder.append(comment);
		builder.append(", timestamp=");
		builder.append(timestamp);
		builder.append(", statuses=");
		builder.append(tstatuses);
		builder.append("]");
		return builder.toString();
	}


	public Report() {
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

	public int getId() {
		return id;
	}


	public List<TStatus> getTStatuses() {
		return tstatuses;
	}


	public void setTStatuses(List<TStatus> tstatuses) {
		this.tstatuses = tstatuses;
	}

}
