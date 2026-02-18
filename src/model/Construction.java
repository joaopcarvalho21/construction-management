package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enums.ConstructionStatus;

public class Construction {

    private static int autoIncrement = 1;

    private final Integer id;
    private String name;
    private Double budget;
    private LocalDate startDate;
    private LocalDate deadline;
    private ConstructionStatus status;

    private Client client;
    private Location location;
    private List<Employee> employees = new ArrayList<>();
    private List<Expense> expenses = new ArrayList<>();

    public Construction(String name, Double budget, LocalDate startDate, 
                        LocalDate deadline, Client client, Location location) {

        this.id = autoIncrement++;
        this.name = name;
        this.budget = budget;
        this.startDate = startDate;
        this.deadline = deadline;
        this.client = client;
        this.location = location;
        this.status = ConstructionStatus.PLANNING;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getBudget() {
        return budget;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public ConstructionStatus getStatus() {
        return status;
    }

    public Client getClient() {
        return client;
    }

    public Location getLocation() {
        return location;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setName(String name) {
        if (name != null && !name.isBlank()) {
            this.name = name;
        }
    }

    public void setBudget(Double budget) {
        if (budget != null && budget >= 0) {
            this.budget = budget;
        }
    }

    public void setDeadline(LocalDate deadline) {
        if (deadline != null && deadline.isAfter(startDate)) {
            this.deadline = deadline;
        }
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
        if (e == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        this.employees.remove(e);
    }

    public void updateStatus(ConstructionStatus status) {
        if (status != null) {
            this.status = status;
        }
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
        return String.format(
                "Construction [id=%d, name=%s, budget=R$ %.2f, status=%s, client=%s, city=%s, employees=%d, expenses=%d]",
                id, name, budget, status,
                client.getName(), location.getCity(),
                employees.size(), expenses.size()
        );
    }
}
