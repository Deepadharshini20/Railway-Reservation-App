package railwayapp.Reservation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import railwayapp.Input;
import railwayapp.Passenger.Passenger;

class Gender extends Input {
  @Override
  public char getChar() {
    char gender = '\0';
    while (true) {
      String input = in.nextLine().toUpperCase();
      if(!input.equals("")){
        gender = input.charAt(0);
        if (gender == 'F' || gender == 'M')
          break;
      }
      else
        System.out.println("Ente only [M,F]");
    }
    return gender;
  }
}

class Preference extends Input {
  @Override
  public char getChar() {
    char preference = '\0';
    while (true) {
      String input = in.nextLine().toUpperCase();
      if(!input.equals("")){
        preference = input.charAt(0);
        if (preference == 'L' || preference == 'U' || preference == 'M')
          break;
      }
      else
        System.out.println("Ente only [L,M,U]");
    }
    return preference;
  }
}
public class TicketBooking {
  
  static int availableLB = 1;
  static int availableMB = 1;
  static int availableUB = 1;
  static int availableRAC = 1;
  static int availableWL = 1;

  static ArrayList<Integer> lower = new ArrayList<>(Arrays.asList(1));
  static ArrayList<Integer> middle = new ArrayList<>(Arrays.asList(1));
  static ArrayList<Integer> upper = new ArrayList<>(Arrays.asList(1));
  static ArrayList<Integer> rac = new ArrayList<>(Arrays.asList(1));
  static ArrayList<Integer> waiting = new ArrayList<>(Arrays.asList(1));

  static Queue<Integer> wlList = new LinkedList<>();
  static Queue<Integer> racList = new LinkedList<>();
  static ArrayList<Integer> bookedTicktes = new ArrayList<>();
  static Map<Integer, Passenger> passengrData = new HashMap<>();

  public void book() {
    Preference p = new Preference();
    Gender g = new Gender();
    
    System.out.println("Enter the passenger name: ");
    String name = Input.getName();
    System.out.println("Enter the passenger age");
    int age = Input.getNumber();
    System.out.println("Enter the gender [M,F]");
    char gender = g.getChar();

    if(gender=='F'){
      System.out.println("1.If u have child,press 1\n2.If u haven't child,press 2");
      int childChoice = Input.getNumber();
      if(childChoice==1){
        System.out.println("Enter child name");
        String cname = Input.getName();
        System.out.println("Enter child age");
        int cage = Input.getNumber();
        if(cage>5){
          System.out.println("Below age 5 is considered for child.So u have to book for ur childeren too");
          System.out.println("Enter the gender of child [M,F]");
          gender = g.getChar();
          System.out.println("Enter your preference [L,M,U]");
          char preference = p.getChar();
          Passenger passenger = new Passenger(name, age, gender,preference);
          Passenger passenger1 = new Passenger(cname, cage, gender,preference);
          bookticket(passenger);
          bookticket(passenger1);
        }
        else{
          System.out.println("Enter your preference [L,M,U]");
          char preference = p.getChar();
          Passenger passenger = new Passenger(name, age, gender,cname,cage,preference);
          bookticket(passenger);
        }
    
      } 
      else{
        System.out.println("Enter your preference [L,M,U]");
        char preference = p.getChar();
        Passenger passenger = new Passenger(name, age, gender, preference);
        bookticket(passenger);
      }
    }
    else{
      System.out.println("Enter your preference [L,M,U]");
      char preference = p.getChar();
      Passenger passenger = new Passenger(name, age, gender, preference);
      bookticket(passenger);
    } 
  }

  public void bookticket(Passenger p, int seatnumber, String alloted) {
    p.setSeatnumber(seatnumber);
    p.setAlloted(alloted);

    passengrData.put(p.getPassengerId(), p);
    bookedTicktes.add(p.passengerId);

    System.out.print(p.toString());
    System.out.println(p.cname!=null ? "Child name: "+p.cname+"\nChild age: "+p.cage:"You have no child");
    System.out.println("--------Booked successfully-------");
  }

  public void addToRAC(Passenger p, int seatnumber, String allotedRac) {
    p.setSeatnumber(seatnumber);
    p.setAlloted(allotedRac);

    passengrData.put(p.getPassengerId(), p);
    racList.add(p.passengerId);
    
    System.out.print(p.toString());
    System.out.println(p.cname!=null ? "Child name: "+p.cname+"\nChild age: "+p.cage:"You have no child");
    System.out.println("--------Rac Berth Given-------");
  }

  public void addToWaiting(Passenger p, int seatnumber, String allotedWaiting) {
    p.setSeatnumber(seatnumber);
    p.setAlloted(allotedWaiting);

    passengrData.put(p.getPassengerId(), p);
    wlList.add(p.passengerId);

    System.out.print(p.toString());
    System.out.println(p.cname!=null ? "Child name: "+p.cname+"\nChild age: "+p.cage:"You have no child");
    System.out.println("--------You're in Waiting List-------");
  }

  public void bookticket(Passenger p) {
    if (availableWL == 0) {
      System.out.println("No tickets available");
      return;
    }
    else if(p.age > 60 && availableLB>0){
      System.out.println("You're old age.so we are given lower birth");
      bookticket(p, lower.get(0), "L");
      lower.remove(0);
      availableLB--;
    }
    else if(p.cname!=null && availableLB>0){
      System.out.println("You have a child.so we are given lower birth");
      bookticket(p, lower.get(0), "L");
      lower.remove(0);
      availableLB--;
    }
    else if ((p.preference == 'L' && availableLB > 0) || (p.preference == 'M' && availableMB > 0) ||
        (p.preference == 'U' && availableUB > 0)) {
      if (p.preference == 'L') {
        System.out.println("Lower birth given");
        bookticket(p, lower.get(0), "L");
        lower.remove(0);
        availableLB--;
      } else if (p.preference == 'M') {
        System.out.println("Middle birth given");
        bookticket(p, middle.get(0), "M");
        middle.remove(0);
        availableMB--;
      } else if (p.preference == 'U') {
        System.out.println("Upper birth given");
        bookticket(p, upper.get(0), "U");
        upper.remove(0);
        availableUB--;
      }
    } else if (availableLB > 0) {
      System.out.println("Lower birth given");
      bookticket(p, lower.get(0), "L");
      lower.remove(0);
      availableLB--;
    } else if (availableMB > 0) {
      System.out.println("Middle birth given");
      bookticket(p, middle.get(0), "M");
      middle.remove(0);
      availableMB--;
    } else if (availableUB > 0) {
      System.out.println("Upper birth given");
      bookticket(p, upper.get(0), "U");
      upper.remove(0);
      availableUB--;
    } else if (availableRAC > 0) {
      System.out.println("Rac birth given");
      addToRAC(p, rac.get(0), "RAC");
      rac.remove(0);
      availableRAC--;
    } else if (availableWL > 0) {
      System.out.println("Waiting list birth given");
      addToWaiting(p, waiting.get(0), "WL");
      waiting.remove(0);
      availableWL--;
    }

  }
}
