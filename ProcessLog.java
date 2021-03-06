import java.util.Scanner;
import java.util.LinkedList;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JButton;

public class ProcessLog{
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
  public static void process(){
    Scanner scanner = new Scanner( System.in );
    System.out.println("Would you like to enter a new run? Yes or No?");
    if(scanner.nextLine().equals("Yes")){
      newRun();
    }
    else{
      System.out.println("Would you like to look through past runs? Yes or No");
      if(scanner.nextLine().equals("Yes")){
        oldRuns();
        System.out.println("good");
        scanner.close();
      }
      else{
        scanner.close();
        System.out.println("Closing RunningLog.");
      }
    }
  }
  
  public static void newRun(){
    Scanner scanner = new Scanner( System.in );
    System.out.print("What type of run did you do? Please enter one of the following: Tempo, Race, Jog, Intervals, or Distance. ");  
    String type = scanner.nextLine(); 
    Workout ab;
    if((type.equals("Intervals"))){
      ab = new Intervals();
      questions(ab);
    }
    else if(type.equals("Tempo")){
      ab = new Tempo();
      
      questions(ab);
    }
    else if(type.equals("Race")){
      ab = new Race();
      questions(ab);
    }
    else if(type.equals("Jog")){
      ab = new Jog();
      questions(ab);
    }
    else{
      ab = new DistanceRun();
      questions(ab);
    }
    RunningLog.runs = ab;
  }
  
  public static void questions(Workout a){
    
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
    if(a instanceof Jog){
      System.out.print("Who did you run with?");
      ((Jog)a).buddies = scanner.nextLine();
    }
    System.out.print("What was the temperature in degrees Farenheit?");  
    a.temperature = scanner.nextInt();  
    
    System.out.print("What day was the run? Use the format 1.1 for January 1st or 1.2 for January 2nd.");  
    a.date = scanner.nextDouble();  
    System.out.println("Great! I have recorded " + a.toString() + " Do you want to enter another run? ");
    if(scanner.nextLine().equals("Yes")){
      process();
    }
    else{
      System.out.println("Ok. You should run again tomorrow ;)");
    }
    RunningLog.writeRun();
    
  }
  
  public static void oldRuns(){
    Object[][] runs = analyzeLog();
    Scanner ask = new Scanner(System.in);
    System.out.println("Would you like to see your total distance?");
    if(ask.nextLine().equals("Yes")){
      double totalMiles = calculateTotalMiles(runs);
      System.out.println("Your total mileage to date: " + totalMiles + "miles");
      return;
    }
    System.out.println("Would you like to find your longest recorded run?");
    if(ask.nextLine().equals("Yes")){
      System.out.println("Your longest run to date is" + findLongestRunEver(runs) + "miles");
      return;
    }
    System.out.println("Would you like to find your fastest time for a given workout?");
    if(ask.nextLine().equals("Yes")){
      double matchdist = 0;
      System.out.println("What distance is the workout you're looking for? Put 0 for Intervals.");
      if(ask.nextInt() == 0){
        System.out.println("What interval distance are you running? In meters please.");
        int intDist = ask.nextInt();
        System.out.println("How many intervals of " + intDist + " meters are you running?");
        int nInt = ask.nextInt();
        double val = bestINTERVALworkout(intDist, nInt, runs);
        System.out.println("Your fastest average time for " + nInt + " intervals that are each " + intDist + " meters long is " + val + " seconds");
      }
      else{
        matchdist = ask.nextInt();
        double val = bestWorkout(matchdist, runs);
        System.out.println("Your fastest time for a distance of " + matchdist + " miles is " + val + " minutes");
      }
      ask.close();
      
      
      /* See fastest time for a specific distance or workout.
       * Have I run this workout before?
       * When was the last time I ran this workout?
       * What is the longest run I've done?
       * Compute my total mileage
       * How many runs have 
       * */
    }
  }
  
  public static Object[][] analyzeLog(){
    Scanner scan = new Scanner("fakelog.csv");
    scan.nextLine();
    scan.useDelimiter(",");
    Object[][] data = new Object[31][9];
    for(int row = 0; row < 31; row++){
      for(int col = 0; col < 9; col++){
        if(scan.hasNext()){
          data[row][col] = scan.next();
        }
      }
    }
    return data;
  }
  
  public static double bestWorkout(double matchdist, Object[][] data){
    double fastestT = 99999999.9;
    for(int row = 1; row<31; row++){
      if(data[row][1] instanceof Double && data[row][4] instanceof Double){
        if((Double)(data[row][1]) == matchdist){
          if((Double)(data[row][4]) < fastestT){
            fastestT = (Double)(data[row][4]);
          }
        }
      }
    } 
    return fastestT;
  }
  
  public static double bestINTERVALworkout(int intDist, int nInts, Object[][] data){
    double fastestAvgT =  400000000.1;
    for(int row = 1; row < 31; row++){
      if(data[row][7] instanceof Integer && data[row][8] instanceof Integer && data[row][9] instanceof Integer){
        if((Integer)(data[row][7]) == nInts && (Integer)(data[row][8]) == intDist){
          if((Double)(data[row][9]) < fastestAvgT){
            fastestAvgT = (Double)(data[row][9]);
          }
        }
      }
    }
    return fastestAvgT;
  }
  
  
  
  public static double calculateTotalMiles(Object[][] data){
    double totalMiles = 0;
    for(int row = 1; row < 31; row++){
      if(data[row][1] instanceof Double){
        totalMiles += ((Double)(data[row][1]));
      }
      if(data[row][7] != null && data[row][8] != null){
        totalMiles+= ((Double)(data[row][7])*(Double)(data[row][8])/1600.0);
      }
    }
    return totalMiles;
  }
  
  public static double findLongestRunEver(Object[][] data){
    double longestRun = (Double)(data[1][1]);
    for(int row = 2; row < 31; row++){
      if(data[row][1] instanceof Double && (Double)data[row][1] > longestRun){
        longestRun = (Double)data[row][1];
      }
    }
    return longestRun;
  }
}
