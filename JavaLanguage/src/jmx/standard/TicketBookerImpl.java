package jmx.standard;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketBookerImpl implements TicketBooker {
	
	List<String> passengers = new ArrayList<>(); 
	
	public TicketBookerImpl(){
		bookTickets();
		bookTickets();
		bookTickets();
	}

	@Override
	public void bookTickets() {
		passengers.add("Test"+(passengers.size()+1));
	}

	@Override
	public void clearBookings() {
		passengers.clear();
	}

	@Override
	public Bookings getBookings() {
		return new Bookings(new Date(), passengers.size(), "CurrentBooking");
	}

}
