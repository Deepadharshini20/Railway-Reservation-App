package railwayapp;

import railwayapp.Reservation.BookedInfo;
import railwayapp.Reservation.CancelTicket;
import railwayapp.Reservation.TicketBooking;

public class Main {
  public static void main(String[] args) throws ClassNotFoundException {
    while(true){
      System.out.println("1.Book\n2.Cancel\n3.Available Tickets\n4.Booked tickets\n5.Exit");
      int choice = Input.getNumber();
      switch (choice) {
        case 1:
          TicketBooking t = new TicketBooking();
          t.book();
          break;
        case 2:
          CancelTicket c = new CancelTicket();
          System.out.println("Enter passenger id to cancel: ");
          int id = Input.getNumber();
          c.cancel(id);
          break;
        case 3:
          BookedInfo.available();
          break;
        case 4:
          BookedInfo.details();
          break;
        case 5:
          System.exit(0);
        default:
          break;
      }
    }
  }
}
