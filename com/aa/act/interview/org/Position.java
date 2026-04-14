package com.aa.act.interview.org;

import java.util.*;

public class Position {
	private final String title;
	private Optional<Employee> employee;
	private final Set<Position> directReports;
	
	public Position(String title) {
		this.title = title;
		employee = Optional.empty();
		directReports = new HashSet<>();
	}

	public Position(String title, Employee employee) {
		this(title);
		if(employee != null)
			setEmployee(Optional.of(employee));
	}
	
	public String getTitle() {
		return title;
	}

	public Optional<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(Optional<Employee> employee) {
		this.employee = employee;
	}

	public boolean isFilled() {
		return employee.isPresent();
	}
	
	public boolean addDirectReport(Position position) {
		if(position == null)
			throw new IllegalArgumentException("position cannot be null");
		return directReports.add(position);
	}
	
	public boolean removePosition(Position position) {
		return directReports.remove(position);
	}
	
	public Collection<Position> getDirectReports() {
		return Collections.unmodifiableCollection(directReports);
	}

	@Override
	public String toString() {
		return title + employee.map(e -> ": " + e.toString()).orElse("");
	}
}
