package model;

import java.time.LocalTime;

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
    private LocalTime startHour;
    private LocalTime exitTime;

    public Employee(int id, String dni, String name, String email, String direction, Employee supervisor, LocalTime startHour, LocalTime exitTime) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.email = email;
        this.direction = direction;
        this.supervisor = supervisor;
        this.startHour = startHour;
        this.exitTime = exitTime;
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

    public LocalTime getStartHour() {
        return startHour;
    }

    public void setStartHour(LocalTime startHour) {
        this.startHour = startHour;
    }

    public LocalTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalTime exitTime) {
        this.exitTime = exitTime;
    }


}
