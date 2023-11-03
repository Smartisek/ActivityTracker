
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class DataReader{
    private String activity;
    private Date date;
    private double duration;
    private double distance;
    private int heartRate;
    private double speed;
    private double calories;


    public DataReader(String activity, Date date, double duration, double distance, int heartRate) {
        this.activity = activity;
        this.date = date;
        this.duration = duration;
        this.distance = distance;
        this.heartRate = heartRate;
    }

//    Getters, no setters we don't want to set anything
    public String getActivity() {return activity;}
    public Date getDate() {return date;}
    public double getDuration() {return duration;}
    public double getDistance() {return distance;}
    public int getHeartRate() {return heartRate;}
    public void setCalories(double calories){ this.calories = calories;}

    public double getSpeed(){
        this.speed = (this.distance / (this.duration/60));
        return speed;
    }

    @Override
    public String toString() {
        return "{" +
                "activity='" + activity + '\'' +
                ", date=" + date +
                ", duration=" + duration +
                ", distance=" + distance +
                ", heartRate=" + heartRate +
                ", Speed=" + String.format("%.1f",getSpeed()) + "km/h" +
                ", Intensity= " + CheckIntensity() +
                '}' +"\n";
    }

    String [] intensity = new String[] {"Very Light", "Light", "Moderate", "Vigorous", "Very Vigorous"};
    public String CheckIntensity(){
        if(this.getActivity().equals("Swimming")){
            if(this.getSpeed() <= 0.5){
//                this.setCalories(this.distance * 5);
//                System.out.println("Very Light " + calories);
               return intensity[0];
            } else if(this.getSpeed() >0.5 && this.getSpeed() <= 1.25){
                return intensity[1];
            } else if(this.getSpeed() >1.25 && this.getSpeed() <=2.00){
                return intensity[2];
            } else if(this.getSpeed() >2.00 && this.getSpeed() <= 2.75){
                return intensity[3];
            } else if(this.getSpeed() >2.75){
                return intensity[4];
            }
        }
        if(this.getActivity().equals("Running")){
            if(this.getSpeed() < 4.00){
                return intensity[0];
            } else if(this.getSpeed() >= 4.00 && this.getSpeed() <8.00){
                return intensity[1];
            } else if(this.getSpeed() >= 8.00 && this.getSpeed() < 12.00){
               return intensity[2];
            } else if(this.getSpeed() >=12.00 && this.getSpeed() < 16.00){
                return intensity[3];
            } else if(this.getSpeed() >= 16.00){
                return intensity[4];
            }
        }
        if(this.getActivity().equals("Cycling")){
            if(this.getSpeed() < 8.00){
                return intensity[0];
            } else if(this.getSpeed() >= 8.00 && this.getSpeed() <= 16.00){
                return intensity[1];
            } else if(this.getSpeed() >= 17.00 && this.getSpeed() < 25.00 ){
                return intensity[2];
            } else if(this.getSpeed() >= 25.00 && this.getSpeed() <33.00){
                return intensity[3];
            } else if(this.getSpeed() >= 33.00){
                return intensity[4];
            }
        }
        return "!=intensity";
    }

//    public void GetSwimmingIntensity(){
//        System.out.println(this.getActivity());
//        if(this.getActivity().equals("Swimming")){
//            System.out.println("swim");
//        }
//
//    }
}
