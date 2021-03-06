import java.util.Scanner;
import java.util.LinkedList;
import java.io.*;

public class RunningLog{
  
  public static Workout runs;
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
      ProcessLog.process();
  }
      
      
      public static void writeRun(){
      try{
          Scanner sc = new Scanner("RunningLog.csv");
          LinkedList<Double> prev = new LinkedList();
     while(sc.hasNextDouble()){
    prev.add(sc.nextDouble());
     }

    BufferedWriter bw = new BufferedWriter(new FileWriter("RunningLog.csv"));
    bw.newLine();
        for(double x : prev){
          bw.write("" + x);
        }
    bw.write(runs.date + ", " + runs.distance + ", " + runs.weather + ", " + runs.temperature);
    if(runs instanceof Intervals){
      bw.write(", " + (((Intervals)runs).nIntervals*((Intervals)runs).intervalDist/1600) + ", N/A, N/A, " + ((Intervals)runs).nIntervals + ", " + ((Intervals)runs).intervalDist + ", " + ((Intervals)runs).avgTime);
    }
    if(runs instanceof Tempo){
      bw.write(", " + ((Tempo)runs).totalTime + ", " + ((Tempo)runs).perMileTime);
    }
        if(runs instanceof Race){
      bw.write(", " + ((Race)runs).totalTime + ", " + ((Race)runs).perMileTime);
    }
    if(runs instanceof Jog){
      bw.write(", " + ((Jog)runs).totalTime + ", N/A ," + ((Jog)runs).buddies);
    }
    if(runs instanceof DistanceRun){
      bw.write(", " + ((DistanceRun)runs).totalTime);
    }
    bw.newLine();
    bw.flush();
    bw.close();
}
  
  catch(IOException e){
    System.out.println("Something is wrong.");
  }
}
      
  }
