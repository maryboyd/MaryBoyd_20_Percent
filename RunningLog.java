import java.util.Scanner;
import java.util.ArrayList;

public class RunningLog{
  /*
   * What type of run was it?
   * Tempo
   * Intervals
   * Jog
   * Race
   * Time Trial
   * Hill Workout
   * Distance
   * Combo
   * 
   * What was your total time?
   * 
   * What was your distance?
   * 
   * What was your time per mile?
   * 
   * Record workout ideas
   * 
   * What day was it?
   * 
   * What was the weather like?
   * 
   * ArrayLists can take different types
   * 
   * */
  public static void main(String[] args) {
    Scanner scanner = new Scanner( System.in );
    System.out.println("Would you like to enter a new run? Yes or No?");
    if(scanner.nextLine().equals("Yes")){
      newRun();
    }
    else{}
  }
  
  public static Workout newRun(){
    Scanner scanner = new Scanner( System.in );
    System.out.println("Would you like to enter a new run? Yes or No?");
    if(scanner.nextLine().equals("Yes")){
      newRun();
      System.out.print("What type of run did you do? Please enter one of the following: Tempo, Race, Jog, Intervals, or Distance. ");  
      String type = scanner.nextLine(); 
      Workout ab;
      if((type.equals("Intervals"))){
        ab = new Intervals();
        questions(ab);
      }
      if(type.equals("Tempo")){
        ab = new Tempo();
        questions(ab);
      }
      if(type.equals("Race")){
        ab = new Race();
        questions(ab);
      }
      if(type.equals("Jog")){
        ab = new Jog();
        questions(ab);
      }
      else{
        ab = new DistanceRun();
        questions(ab);
      }
    }
   // return ((Workout)ab);
    Workout a = new Tempo();
      return a;
  }
  
  public static Workout questions(Workout a){
     Scanner scanner = new Scanner( System.in );
    if(!(a instanceof Intervals)){
      System.out.print("What was your total distance? Decimals, not fractions please.");  
      a.distance = scanner.nextDouble();  
    }
    else{
      System.out.println("How many intervals did you run?");
      ((Intervals)a).nIntervals = scanner.nextInt();
      System.out.println("What distance was each of your intervals (in meters)");
      ((Intervals)a).intervalDist = scanner.nextInt();
      System.out.println("What was your average time per interval?");
      ((Intervals)a).avgTime = scanner.nextDouble();
    }
      
    System.out.print("What was the temperature in degrees Farenheit?");  
    a.temperature = scanner.nextInt();  
    
    System.out.print("What day was the run? Use the format 1.1 for January 1st or 1.2 for January 2nd.");  
    a.date = scanner.nextDouble();  
    
    return a;
  }
}
