package model;

import java.time.LocalDate;

import enums.ExpenseCategory;

public class Expense {

    private static int autoIncrement = 1;

    private final Integer id;
    private String description;
    private Double amount;
    private LocalDate date;
    private ExpenseCategory category;

    public Expense(String description, Double amount, LocalDate date, ExpenseCategory category) {
        this.id = autoIncrement++;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Expense { " +
                "id=" + id +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", category=" + category +
                " }";
    }
}
