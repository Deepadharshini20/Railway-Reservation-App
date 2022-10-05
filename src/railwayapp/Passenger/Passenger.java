package railwayapp.Passenger;

public class Passenger {
  static int id = 1;
  public String name;
  public int age;
  public char gender;
  public String cname;
  public int cage;
  public char preference;
  public int passengerId = id++;
  public String alloted;
  public int seatnumber;
  public Passenger(){
    
  }
  public Passenger(String name, int age, char gender, char preference) {
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.preference = preference;
    cname = null;
    alloted = " ";
    seatnumber = -1;
  }

  public Passenger(String name, int age, char gender, String cname, int cage,char preference) {
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.cname = cname;
    this.cage = cage;
    this.preference = preference;
    alloted = " ";
    seatnumber = -1;
  }

  @Override
  public String toString() {
    return "Passenger Id: " + passengerId + 
            "\nPassenger name: " + name + 
            "\nPassenger age: " + age +
            "\nPassenger gender: " + gender +  
            "\nAlloted Berth: " + seatnumber+alloted+"\n";
  }

  public int getPassengerId() {
    return passengerId;
  }

  public void setPassengerId(int passengerId) {
    this.passengerId = passengerId;
  }

  public String getAlloted() {
    return alloted;
  }

  public void setAlloted(String alloted) {
    this.alloted = alloted;
  }

  public int getSeatnumber() {
    return seatnumber;
  }

  public void setSeatnumber(int seatnumber) {
    this.seatnumber = seatnumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public char getGender() {
    return gender;
  }

  public void setGender(char gender) {
    this.gender = gender;
  }

  public char getPreference() {
    return preference;
  }

  public void setPreference(char preference) {
    this.preference = preference;
  }
  public String getCname() {
    return cname;
  }

  public void setCname(String cname) {
    this.cname = cname;
  }

  public int getCage() {
    return cage;
  }

  public void setCage(int cage) {
    this.cage = cage;
  }
}
