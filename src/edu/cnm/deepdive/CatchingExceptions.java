package edu.cnm.deepdive;

class MuseumClosed extends RuntimeException {

}

class MuseumClosedForLunch extends MuseumClosed {

}

public class CatchingExceptions {

  public static void main(String[] args) {
      try {
        visitMuseum();
      } catch (MuseumClosedForLunch mc) {//subclass
        System.out.println("Museum closed for lunch");
      } catch(MuseumClosed mc) {
        System.out.println("Closed");
        //always check for the subclass and the superclass. The subclass needs to come first. If
        //superclass comes first this class catches both exceptions which results in compilation error.
      }
    System.out.println();
    System.out.println(calculate());
  }

  public static String calculate() {
    String result = "";
    String str = null;

    try {
      try {
        result += "start"; //result = start
        str.length();//this throws a NullPointerException because str = null on line 29 so up to inner
        //catch block
        result += "end";

      } catch (NullPointerException e) {
        result += "npe";// result = startnpe
        throw new RuntimeException();
      } finally {
        result += "finally";//finally is always executed so result = startnpefinally
        throw new Exception();//this exception is caught but the catch block on line 45
      }
    } catch (Exception e) {
      result += "finished"; //result = startnpefinallyfinished
    }
    return result; //prints startnpefinallyfinished
  }

  public static void visitMuseum() {
    boolean b = Math.random() < 0.5;

    if (b) {
      throw new MuseumClosed();
    }
    throw new MuseumClosedForLunch();
  }
}
