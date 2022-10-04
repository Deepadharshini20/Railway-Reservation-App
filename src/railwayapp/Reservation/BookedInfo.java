package railwayapp.Reservation;

import java.util.Map;

import railwayapp.Passenger.Passenger;

public class BookedInfo {

  public static void details() {
    Map<Integer,Passenger> booked = TicketBooking.passengrData;
    if(booked.size()==0){
      System.out.println("No Passenger details found");
      return;
    }
    else{
      System.out.println("Total Tickets - "+booked.size());
      for(Passenger p : booked.values()){
        System.out.print(p.toString());
        System.out.println(p.cname!=null ? "Child name: "+p.cname+"\nChild age: "+p.cage:"You have no child");
        System.out.println("\n");   
      }
    }
  }
  public static void available() {
    Map<Integer,Passenger> booked = TicketBooking.passengrData;
    System.out.println("Available Total tickets- "+(5-booked.size()));
    System.out.println("Available Lower: "+TicketBooking.availableLB);
    System.out.println("Available Middle: "+TicketBooking.availableMB);
    System.out.println("Available Upper: "+TicketBooking.availableUB);
    System.out.println("Available Rac: "+TicketBooking.availableRAC);
    System.out.println("Available Waiting: "+TicketBooking.availableWL);
  }
}
