package railwayapp.Reservation;

import java.util.*;

import railwayapp.Database.Databasehandler;
import railwayapp.Passenger.Passenger;

public class CancelTicket {
  static Databasehandler db = TicketBooking.db;
  public void cancel(int id) throws ClassNotFoundException {
    if (!db.checkId(id)) {
      System.out.println("Passenger id is not found");
    } else {
      cancelTicket(id);
    }
  }

  public static void cancelTicket(int id) throws ClassNotFoundException {
    ArrayList<Integer> lower = TicketBooking.lower;
    ArrayList<Integer> middle = TicketBooking.middle;
    ArrayList<Integer> upper = TicketBooking.upper;
    ArrayList<Integer> rac = TicketBooking.rac;
    ArrayList<Integer> waiting = TicketBooking.waiting;

    Queue<Integer> wlList = TicketBooking.wlList;
    Queue<Integer> racList = TicketBooking.racList;
    ArrayList<Integer> bookedTicktes = TicketBooking.bookedTicktes;
    //Map<Integer, Passenger> passengrData = TicketBooking.passengrData;

    Passenger p = db.getPassenger(id);
    db.remove(Integer.valueOf(id));
    bookedTicktes.remove(Integer.valueOf(id));
    int bookedSeatNumber = p.seatnumber;
    System.out.println("-----------Cancelled Successfully---------");

    if (p.getAlloted().equals("L")) {
      lower.add(bookedSeatNumber);
      TicketBooking.availableLB++;
    } else if (p.getAlloted().equals("M")) {
      middle.add(bookedSeatNumber);
      TicketBooking.availableMB++;
    } else if (p.getAlloted().equals("U")) {
      upper.add(bookedSeatNumber);
      TicketBooking.availableUB++;
    }
    if (racList.size() > 0) {
      TicketBooking tb = new TicketBooking();
      Passenger passengeFromRAC = db.getPassenger(racList.poll()); 
      int seatnumberRAC = passengeFromRAC.getSeatnumber();
      rac.add(seatnumberRAC); 
      TicketBooking.availableRAC++;
      racList.remove(passengeFromRAC.getPassengerId());

      if (wlList.size() > 0) {
        // take the passenger from WL and add them to RAC , increase the free space in
        // waiting list and
        // increase available WL and decrease available RAC by 1
        Passenger passengerFromWaitingList = db.getPassenger(wlList.poll()); 
        int positionWL = passengerFromWaitingList.getSeatnumber();
        waiting.add(positionWL);
        wlList.remove(passengerFromWaitingList.passengerId);

        passengerFromWaitingList.setSeatnumber(rac.get(0));
        passengerFromWaitingList.setAlloted("RAC"); 
        rac.remove(0);
        racList.add(passengerFromWaitingList.passengerId);

        TicketBooking.availableWL++;
        TicketBooking.availableRAC--;
      }
      tb.bookticket(passengeFromRAC);
    }
  }
}
