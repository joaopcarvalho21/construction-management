package application;

import java.math.BigDecimal;
import java.time.LocalDate;

import enums.ConstructionStatus;
import enums.ExpenseCategory;
import model.*;
import service.ConstructionService;

public class Main {

    public static void main(String[] args) {

        ConstructionService service = new ConstructionService();

        System.out.println("===== CONSTRUCTION MANAGEMENT SYSTEM =====\n");

        Client client = new Client("Construtora Carvalho", "11984227737");

        Location location = new Location("Av. dos Imigrantes, 350", "Bragança Paulista");

        Employee e1 = new Employee("Carlos Silva", "Pedreiro", BigDecimal.valueOf(3500));
        Employee e2 = new Employee("Marcos Lima", "Mestre de Obras", BigDecimal.valueOf(5500));
        Employee e3 = new Employee("Ana Souza", "Engenheira Civil", BigDecimal.valueOf(7500));

        Construction construction = new Construction(
                "Casa Alto Padrão - Residencial Vista Verde",
                300000.0,
                LocalDate.now(),
                LocalDate.now().plusMonths(5),
                client,
                location
        );

        service.addEmployee(construction, e1);
        service.addEmployee(construction, e2);
        service.addEmployee(construction, e3);

        Expense exp1 = new Expense("Cimento", 1800.0, LocalDate.now(), ExpenseCategory.MATERIAL);
        Expense exp2 = new Expense("Areia", 950.0, LocalDate.now(), ExpenseCategory.MATERIAL);
        Expense exp3 = new Expense("Tijolos", 2400.0, LocalDate.now(), ExpenseCategory.MATERIAL);
        Expense exp4 = new Expense("Mão de obra extra", 3000.0, LocalDate.now(), ExpenseCategory.LABOR);

        service.addExpense(construction, exp1);
        service.addExpense(construction, exp2);
        service.addExpense(construction, exp3);
        service.addExpense(construction, exp4);

        service.updateStatus(construction, ConstructionStatus.IN_PROGRESS);

        System.out.println("===== CONSTRUCTION SUMMARY =====");
        System.out.println(construction);
        System.out.println("Total Expenses: R$ " + service.calculateTotalExpenses(construction));
        System.out.println("Profit: R$ " + service.calculateProfit(construction));
        System.out.println("Remaining Days: " + construction.getRemainingDays());
        System.out.println("Delayed? " + construction.isDelayed());

        System.out.println("\n===== SYSTEM FINISHED SUCCESSFULLY =====");
    }
}
