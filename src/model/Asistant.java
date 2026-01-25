package model;

import java.time.LocalTime;

/**
 *
 * @author dam2_alu03@inf.ald
 */
public class Asistant extends Employee {
    
    public Asistant(int id, String dni, String name, String email, String direction, Employee supervisor, LocalTime startHour, LocalTime exitTime) {
        super(id, dni, name, email, direction, supervisor, startHour, exitTime);
    }
    

}
