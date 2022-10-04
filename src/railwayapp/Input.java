package railwayapp;

import java.util.Scanner;

public abstract class Input {
  protected static Scanner in = new Scanner(System.in);

  public static int getNumber() {
    int n = 0;
    while (true) {
      String input = in.nextLine();
      try {
        n = Integer.parseInt(input);
        return n;
      } catch (NumberFormatException e) {
        System.out.println("Enter only number");
      }
    }
  }

  public static String getName() {
    String name = null;
    while (true) {
      name = in.nextLine();
      if (!name.matches(".*[0-9].*") && !name.equals(""))
        break;
      else
        System.out.println("Enter only String!!");
    }
    return name;
  }

  public abstract char getChar();
}
