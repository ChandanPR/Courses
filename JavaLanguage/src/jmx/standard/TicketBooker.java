package jmx.standard;

import javax.management.MXBean;
@Author("Chandan")
@Version("1.0")
@MXBean
public interface TicketBooker {
	
	@DisplayName("Booking: Book Tickets")
	public void bookTickets();
	
	@DisplayName("Clear : Clear Bookings")
	public void clearBookings();
	
	@DisplayName("GETTER: gets the booking details")
	public Bookings getBookings();

}
