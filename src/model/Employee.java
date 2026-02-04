package model;

import java.math.BigDecimal;

public class Employee {

    private static int autoIncrement = 1; 
    private final Integer id;            
    private String name;
    private String role;
    private BigDecimal salary;

    public Employee(String name, String role, BigDecimal salary) {
        this.id = autoIncrement++;
        this.name = name;
        this.role = role;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        if (salary != null && salary.compareTo(BigDecimal.ZERO) >= 0) {
            this.salary = salary;
        }
    }

    @Override
    public String toString() {
        return String.format(
            "Employee [id=%d, name=%s, role=%s, salary=%s]",
            id, name, role, salary
        );
    }
}
