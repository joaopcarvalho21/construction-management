package service;

import enums.ConstructionStatus;
import model.Construction;
import model.Employee;
import model.Expense;

public class ConstructionService {

    public void addEmployee(Construction c, Employee e) {
        if (c == null || e == null) {
            throw new IllegalArgumentException("Construction and Employee cannot be null");
        }
        c.addEmployee(e);
    }

    public void removeEmployee(Construction c, Employee e) {
        if (c == null || e == null) {
            throw new IllegalArgumentException("Construction and Employee cannot be null");
        }
        c.removeEmployee(e);
    }

    public void addExpense(Construction c, Expense ex) {
        if (c == null || ex == null) {
            throw new IllegalArgumentException("Construction and Expense cannot be null");
        }
        if (ex.getAmount() <= 0) {
            throw new IllegalArgumentException("Expense amount must be positive");
        }
        c.addExpense(ex);
    }

    public void removeExpense(Construction c, Expense ex) {
        if (c == null || ex == null) {
            throw new IllegalArgumentException("Construction and Expense cannot be null");
        }
        c.removeExpense(ex);
    }

    public double calculateProfit(Construction c) {
        if (c == null) {
            throw new IllegalArgumentException("Construction cannot be null");
        }
        return c.getProfit();
    }

    public double calculateTotalExpenses(Construction c) {
        if (c == null) {
            throw new IllegalArgumentException("Construction cannot be null");
        }
        return c.getTotalExpenses();
    }

    public void updateStatus(Construction c, ConstructionStatus status) {
        if (c == null || status == null) {
            throw new IllegalArgumentException("Construction and status cannot be null");
        }
        c.updateStatus(status);
    }

}
