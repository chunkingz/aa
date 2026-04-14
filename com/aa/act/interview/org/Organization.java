package com.aa.act.interview.org;

import java.util.Optional;

public abstract class Organization {

	private final Position root;
	private int headCount;
	
	public Organization() {
		root = createOrganization();
		this.headCount = 0;
	}
	
	protected abstract Position createOrganization();
	
	/**
	 * hire the given person as an employee in the position that has that title
	 * 
	 * @param person - the individual to be hired
	 * @param title - the individuals position
	 * @return the newly filled position or empty if no position has that title
	 */
	public Optional<Position> hire(Name person, String title) {
		//your code here
		return findPosition(root, person, title);
	}

	private Optional<Position> findPosition(Position current, Name person, String title){
		if(current.getTitle().equals(title) && !current.isFilled()){
			current.setEmployee(Optional.of(new Employee(headCount++, person)));
			return Optional.of(current);
		}
		for(Position pos : current.getDirectReports()){
			var result = findPosition(pos, person, title);
			if(result.isPresent()) return result;
		}
		return Optional.empty();
	}

	@Override
	public String toString() {
		return printOrganization(root, "");
	}
	
	private String printOrganization(Position pos, String prefix) {
		StringBuffer sb = new StringBuffer(prefix + "+-" + pos.toString() + "\n");
		for(Position p : pos.getDirectReports()) {
			sb.append(printOrganization(p, prefix + "\t"));
		}
		return sb.toString();
	}
}
