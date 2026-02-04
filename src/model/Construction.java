package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Construction {
	
	private Integer id;
	private BigDecimal value;
	private LocalDate deadline;
	private Employee employee;
	private Client client;
	private String location;
	
	public Construction(Integer id, BigDecimal value, LocalDate deadline, Employee employee, Client client, String location) {
		this.id = id;
		this.value = value;
		this.deadline = deadline;
		this.employee = employee;
		this.client = client;
		this.location = location;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (!(o instanceof Construction)) return false;
	    Construction construction = (Construction) o;
	    return id != null && id.equals(construction.id);
	}

	@Override
	public int hashCode() {
	    return getClass().hashCode();
	}

	
	@Override
	public String toString() {
	    return String.format(
	        "Construction [id=%d, value=%s, deadline=%s, client=%s, location=%s, employee=%s]",
	        id, value, deadline, client, location, employee
	    );
	}

	
}
