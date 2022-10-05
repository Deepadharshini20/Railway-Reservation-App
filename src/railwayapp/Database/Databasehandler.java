package railwayapp.Database;
import java.sql.*;
import railwayapp.Passenger.Passenger;

public class Databasehandler {
  static String url = null;
  static Connection con = null;
  public Databasehandler(){
    url = "jdbc:mysql://localhost:3306/railwaybooking";
    try {
      //Class.forName("com.mysql.jdbc.Driver");
      con = DriverManager.getConnection(url, "root", "root");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    // } catch(ClassNotFoundException c){
    //   c.printStackTrace();
    // }
  }
  public void postPassenger(Passenger p) throws ClassNotFoundException {
   
    String query = "insert into passenger(seatnum,name,age,gender,cname,cage,preference,alloted)"+ 
    " values(?,?,?,?,?,?,?,?)";
    try {  
      PreparedStatement st = con.prepareStatement(query);
      st.setInt(1,p.getSeatnumber());
      st.setString(2, p.getName());
      st.setInt(3, p.getAge());
      st.setString(4, Character.toString(p.getGender()));
      st.setString(5, p.getCname());
      st.setInt(6, p.getCage());
      st.setString(7, Character.toString(p.getPreference()));
      st.setString(8, p.getAlloted());
      st.executeUpdate();    
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public boolean checkId(int id) {
    String query = "select * from passenger where id="+id;
    try{
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery(query);
      if(rs.next())
        return true;
    }catch(SQLException e){
      e.printStackTrace();
    }
    return false;
  }
  public Passenger getPassenger(int id) {
    String query = "select * from passenger where id="+id;
    Passenger p = new Passenger();
    try{
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery(query);
      if(rs.next()){
        p.setSeatnumber(rs.getInt(2));
        p.setName(rs.getString(3));
        p.setAge(rs.getInt(4));
        p.setGender(rs.getString(5).charAt(0));
        if(rs.getString(6)!=null){
          p.setCname(rs.getString(6));
          p.setCage(rs.getInt(7));
        }
        else{
          p.setCname(null);
          p.setCage(0);
        }
        p.setPreference(rs.getString(8).charAt(0));
        p.setAlloted(rs.getString(9));
      }
        
    }catch(SQLException e){
      e.printStackTrace();
    }
    return p;
  }

  public void remove(int id) {
    String query = "delete from passenger where id="+id;
    try{
      PreparedStatement st = con.prepareStatement(query);
      st.executeUpdate();

    }catch (SQLException e) {
      e.printStackTrace();
    }
  }
  public int size() {
    String query = "select count(*) from passenger";
    int count = 0;
    try {
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery(query);
      rs.next();
      count= rs.getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return count;
  }
  public void passengers() {
    String query = "select * from passenger";
    try{
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery(query);
      while(rs.next()){
        Passenger p = new Passenger();
        p.setSeatnumber(rs.getInt(2));
        p.setName(rs.getString(3));
        p.setAge(rs.getInt(4));
        p.setGender(rs.getString(5).charAt(0));
        if(rs.getString(6)!=null){
          p.setCname(rs.getString(6));
          p.setCage(rs.getInt(7));
        }
        else{
          p.setCname(null);
          p.setCage(0);
        }
        p.setPreference(rs.getString(8).charAt(0));
        p.setAlloted(rs.getString(9));
        
        System.out.print(p.toString());
        System.out.println(p.cname!=null ? "Child name: "+p.cname+"\nChild age: "+p.cage:"You have no child");
        System.out.println("\n");   
      }
        
    }catch(SQLException e){
      e.printStackTrace();
    }
  }
}

