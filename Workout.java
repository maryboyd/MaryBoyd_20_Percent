public abstract class Workout{
  
  public double date;
  public int weather;
  public int temperature;
  public double distance;
  
  public Workout(){
  }
  
  public int getWeather(){
    return weather;
  }
  
  public double getDistance(){
    return distance;
  }
  
  public int getTemperature(){
    return temperature;
  }
  
  
  public String toString(){
  return "a run of " + getDistance() + " miles on " + date + ".2013.";
  
  }
  
  public String weatherToString(){
    String weather = "The weather for my run on" + date + "was ";
    switch (getWeather()){
      case 0: return weather+ "sunny and " + getTemperature() + "degrees.";
      case 1: return weather+ "cloudy and " + getTemperature() + "degrees.";
      case 2: return weather+ "windy and " + getTemperature() + "degrees.";
      case 3: return weather+ "pouring and " + getTemperature() + "degrees.";
      case 4: return weather+ "lightly raining and " + getTemperature() + "degrees.";
      case 5: return weather+ "muddy and " + getTemperature() + "degrees.";
      case 6: return weather+ "humid and " + getTemperature() + "degrees.";
      case 7: return weather+ "icy/snowy and " + getTemperature() + "degrees.";
      default: return weather+ "not recorded.";
    }
  
  
}
}