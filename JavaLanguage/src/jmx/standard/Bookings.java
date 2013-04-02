package jmx.standard;

import java.beans.ConstructorProperties;
import java.util.Date;

public class Bookings {
	
	private final Date date;
	private final int seats;
	private final String name;
	
	@ConstructorProperties({"date", "seats", "name"}) 
    public Bookings(Date date, int seats, String name) { 
        this.date = date; 
        this.seats = seats; 
        this.name = name; 
    } 
     
    public Date getDate() { 
        return date; 
    } 
     
    public int getSeats() {
		return seats;
	}
    
    public String getName() {
		return name;
	}

}
