package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enums.ConstructionStatus;

public class Construction {

    private static int autoIncrement = 1;

    private final Integer id;
    private String description;
    private Double budget;
    private LocalDate startDate;
    private LocalDate deadline;
    private ConstructionStatus status;

    private Client client;
    private Location location;
    private List<Employee> employees = new ArrayList<>();
    private List<Expense> expenses = new ArrayList<>();

    public Construction(String description, Double budget, LocalDate startDate, 
                        LocalDate deadline, Client client, Location location) {

        this.id = autoIncrement++;
        this.description = description;
        this.budget = budget;
        this.startDate = startDate;
        this.deadline = deadline;
        this.client = client;
        this.location = location;
        this.status = ConstructionStatus.PLANNING;
    }

    public void addExpense(Expense ex) {
        if (ex == null) {
            throw new IllegalArgumentException("Expense cannot be null");
        }
        this.expenses.add(ex);
    }
    
    public void removeExpense(Expense ex) {
        if (ex == null) {
            throw new IllegalArgumentException("Expense cannot be null");
        }
        this.expenses.remove(ex);
    }

    public double getTotalExpenses() {
        return expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    public double getProfit() {
        return budget - getTotalExpenses();
    }
    
    public void addEmployee(Employee e) {
        if (e == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        this.employees.add(e);
    }

    public void removeEmployee(Employee e) {
        this.employees.remove(e);
    }

    public void updateStatus(ConstructionStatus status) {
        this.status = status;
    }

    public long getRemainingDays() {
        return java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), deadline);
    }

    public boolean isDelayed() {
        return LocalDate.now().isAfter(deadline) 
                && status != ConstructionStatus.COMPLETED;
    }    

    @Override
    public String toString() {
        return "Construction [id=" + id +
               ", description=" + description +
               ", budget=" + budget +
               ", startDate=" + startDate +
               ", deadline=" + deadline +
               ", status=" + status +
               ", client=" + client +
               ", location=" + location +
               ", employees=" + employees.size() +
               ", expenses=" + expenses.size() +
               "]";
    }

}
