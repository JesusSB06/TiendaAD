package model;

/**
 *
 * @author dam2_alu03@inf.ald
 */
public abstract class Employee {
    private int id;
    private String dni;
    private String name;
    private String email;
    private String direction;
    private Employee supervisor;

    public Employee(int id, String dni, String name, String email, String direction) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.email = email;
        this.direction = direction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Employee getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }
    
}
