package data;

import java.util.List;

import entities.Report;

public interface ReportDAO {
	public List<Report> index(int trailId);
	public Report show(int id);
	public Report create(Report report, int tid, int uid);
	public Report update(int id, Report report);
	public Report destroy(int id);

}
