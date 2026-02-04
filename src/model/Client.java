package model;

public class Client {

    private static int autoIncrement = 1;
    private final Integer id;
    private String name;
    private String phone;

    public Client(String name, String phone) {
        this.id = autoIncrement++;
        this.name = name;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return String.format(
            "Client [id=%d, name=%s, phone=%s]",
            id, name, phone
        );
    }
}
