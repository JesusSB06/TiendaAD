package model;

import java.time.LocalTime;

/**
 *
 * @author dam2_alu03@inf.ald
 */
public class Technician extends Employee {
    
    public Technician(int id, String dni, String name, String email, String direction, int supervisor, LocalTime startHour, LocalTime exitTime) {
        super(id, dni, name, email, direction, supervisor, startHour, exitTime);
    }
    

}
