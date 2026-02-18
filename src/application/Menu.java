package application;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import enums.ConstructionStatus;
import enums.ExpenseCategory;
import model.Client;
import model.Construction;
import model.Employee;
import model.Expense;
import model.Location;
import service.ConstructionService;

public class Menu {

    private static final Scanner sc = new Scanner(System.in);
    private static final ConstructionService service = new ConstructionService();

    public static void start() {

        int option;

        do {
            showMenu();
            option = readInt();

            switch (option) {
                case 1 -> createConstruction();
                case 2 -> listConstructions();
                case 3 -> selectConstruction();
                case 0 -> System.out.println("\nSistema finalizado.");
                default -> System.out.println("\nOpção inválida.");
            }

        } while (option != 0);
    }

    private static void showMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1 - Cadastrar obra");
        System.out.println("2 - Listar obras");
        System.out.println("3 - Selecionar obra");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    private static void listConstructions() {
        List<Construction> constructions = service.getConstructions();

        if (constructions.isEmpty()) {
            System.out.println("\nNenhuma obra cadastrada.");
            return;
        }

        System.out.println("\n===== LISTA DE OBRAS =====");

        for (int i = 0; i < constructions.size(); i++) {
            Construction c = constructions.get(i);
            System.out.println("[" + i + "] " + c.getName() + " - " + c.getStatus());
        }
    }

    private static void createConstruction() {
        System.out.println("\n===== CADASTRO DE OBRA =====");

        System.out.print("Nome da obra: ");
        String name = sc.nextLine();

        System.out.print("Orçamento: ");
        double budget = readDouble();

        System.out.print("Nome do cliente: ");
        String clientName = sc.nextLine();

        System.out.print("Telefone do cliente: ");
        String phone = sc.nextLine();

        System.out.print("Rua: ");
        String street = sc.nextLine();

        System.out.print("Bairro: ");
        String district = sc.nextLine();

        System.out.print("Cidade: ");
        String city = sc.nextLine();

        Client client = new Client(clientName, phone);
        Location location = new Location(street, district, city);

        Construction construction = new Construction(
                name,
                budget,
                LocalDate.now(),
                LocalDate.now().plusMonths(6),
                client,
                location
        );

        service.addConstruction(construction);

        System.out.println("\n✔ Obra cadastrada com sucesso!");
    }
    
    private static void selectConstruction() {
        if (service.getConstructions().isEmpty()) {
            System.out.println("\nNenhuma obra cadastrada.");
            return;
        }

        listConstructions();

        System.out.print("\nEscolha o índice da obra: ");
        int index = sc.nextInt();

        if (index < 0 || index >= service.getConstructions().size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Construction selected = service.getConstructions().get(index);
        constructionMenu(selected);
    }

    private static void constructionMenu(Construction c) {

        int option;

        do {
            System.out.println("\n===== MENU DA OBRA =====");
            System.out.println("Obra: " + c.getName());
            System.out.println("1 - Ver detalhes");
            System.out.println("2 - Funcionários");
            System.out.println("3 - Despesas");
            System.out.println("4 - Atualizar status");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            option = sc.nextInt();

            switch (option) {
                case 1 -> showConstructionDetails(c);
                case 2 -> employeeMenu(c);
                case 3 -> expenseMenu(c);
                case 4 -> updateStatus(c);
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }

        } while (option != 0);
    }
    
    private static void showConstructionDetails(Construction c) {
	    System.out.println("\n===== DETALHES DA OBRA =====");
	    System.out.println(c);
	    System.out.println("Total de despesas: R$ " + c.getTotalExpenses());
	    System.out.println("Lucro estimado: R$ " + c.getProfit());
	    System.out.println("Dias restantes: " + c.getRemainingDays());
	    System.out.println("Atrasada? " + (c.isDelayed() ? "Sim" : "Não"));
	}

    private static void employeeMenu(Construction c) {

        int option;

        do {
            System.out.println("\n===== FUNCIONÁRIOS =====");
            System.out.println("Obra: " + c.getName());
            System.out.println("1 - Adicionar funcionário");
            System.out.println("2 - Listar funcionários");
            System.out.println("3 - Remover funcionário");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            option = sc.nextInt();

            switch (option) {
                case 1 -> addEmployee(c);
                case 2 -> listEmployees(c);
                case 3 -> removeEmployee(c);
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }

        } while (option != 0);
    }

    private static void addEmployee(Construction c) {
        sc.nextLine();

        System.out.print("Nome: ");
        String name = sc.nextLine();

        System.out.print("Cargo: ");
        String role = sc.nextLine();

        System.out.print("Salário: ");
        BigDecimal salary = sc.nextBigDecimal();

        Employee e = new Employee(name, role, salary);

        service.addEmployee(c, e);

        System.out.println("\n✔ Funcionário adicionado com sucesso!");
    }
    
    private static void listEmployees(Construction c) {

        if (c.getEmployees().isEmpty()) {
            System.out.println("\nNenhum funcionário cadastrado.");
            return;
        }

        System.out.println("\n===== LISTA DE FUNCIONÁRIOS =====");

        for (int i = 0; i < c.getEmployees().size(); i++) {
            System.out.println("[" + i + "] " + c.getEmployees().get(i));
        }
    }
    
    private static void removeEmployee(Construction c) {

        if (c.getEmployees().isEmpty()) {
            System.out.println("\nNenhum funcionário cadastrado.");
            return;
        }

        listEmployees(c);

        System.out.print("\nEscolha o índice para remover: ");
        int index = sc.nextInt();

        if (index < 0 || index >= c.getEmployees().size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Employee removed = c.getEmployees().get(index);
        service.removeEmployee(c, removed);

        System.out.println("\n✔ Funcionário removido com sucesso!");
    }
    
    private static void expenseMenu(Construction c) {

        int option;

        do {
            System.out.println("\n===== DESPESAS =====");
            System.out.println("Obra: " + c.getName());
            System.out.println("1 - Adicionar despesa");
            System.out.println("2 - Listar despesas");
            System.out.println("3 - Remover despesa");
            System.out.println("4 - Total gasto");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            option = readInt();

            switch (option) {
                case 1 -> addExpense(c);
                case 2 -> listExpenses(c);
                case 3 -> removeExpense(c);
                case 4 -> showTotalExpenses(c);
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }

        } while (option != 0);
    }

    private static void addExpense(Construction c) {
        sc.nextLine();

        System.out.println("\n===== ADICIONAR DESPESA =====");

        System.out.print("Descrição: ");
        String description = sc.nextLine();

        System.out.print("Valor: ");
        double amount = readDouble();

        ExpenseCategory[] categories = ExpenseCategory.values();

        System.out.println("\nSelecione a categoria:");

        for (int i = 0; i < categories.length; i++) {
            System.out.println(i + " - " + categories[i]);
        }

        System.out.print("Escolha: ");
        int index = readInt();

        if (index < 0 || index >= categories.length) {
            System.out.println("❌ Categoria inválida.");
            return;
        }

        ExpenseCategory category = categories[index];

        Expense expense = new Expense(
                description,
                amount,
                LocalDate.now(),
                category
        );

        service.addExpense(c, expense);

        System.out.println("\n✔ Despesa adicionada com sucesso!");
    }

    
    private static void listExpenses(Construction c) {

        if (c.getExpenses().isEmpty()) {
            System.out.println("\nNenhuma despesa cadastrada.");
            return;
        }

        System.out.println("\n===== LISTA DE DESPESAS =====");

        for (int i = 0; i < c.getExpenses().size(); i++) {
            System.out.println("[" + i + "] " + c.getExpenses().get(i));
        }

        System.out.println("\nTotal gasto: R$ " + c.getTotalExpenses());
    }
    
    private static void removeExpense(Construction c) {

        if (c.getExpenses().isEmpty()) {
            System.out.println("\nNenhuma despesa cadastrada.");
            return;
        }

        listExpenses(c);

        System.out.print("\nEscolha o índice para remover: ");
        int index = readInt();

        if (index < 0 || index >= c.getExpenses().size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Expense removed = c.getExpenses().get(index);
        service.removeExpense(c, removed);

        System.out.println("\n✔ Despesa removida com sucesso!");
    }
    
    private static void showTotalExpenses(Construction c) {
        System.out.println("\nTotal gasto até agora: R$ " + c.getTotalExpenses());
    }
    
    private static void updateStatus(Construction c) {

        System.out.println("\n===== ATUALIZAR STATUS =====");
        System.out.println("Status atual: " + c.getStatus());

        System.out.println("1 - PLANNING");
        System.out.println("2 - IN_PROGRESS");
        System.out.println("3 - COMPLETED");
        System.out.println("4 - STOPPED");

        System.out.print("Escolha o novo status: ");
        int option = readInt();

        ConstructionStatus newStatus = null;

        switch (option) {
            case 1 -> newStatus = ConstructionStatus.PLANNING;
            case 2 -> newStatus = ConstructionStatus.IN_PROGRESS;
            case 3 -> newStatus = ConstructionStatus.COMPLETED;
            case 4 -> newStatus = ConstructionStatus.STOPPED;
            default -> {
                System.out.println("Opção inválida.");
                return;
            }
        }

        service.updateStatus(c, newStatus);

        System.out.println("\n✔ Status atualizado para: " + newStatus);
    }

    private static int readInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Digite um número válido: ");
            sc.next();
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    private static double readDouble() {
        while (!sc.hasNextDouble()) {
            System.out.print("Digite um valor válido: ");
            sc.next();
        }
        double value = sc.nextDouble();
        sc.nextLine();
        return value;
    }
}
