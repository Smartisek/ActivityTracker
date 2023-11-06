import java.util.Date;

public class DataReader implements Comparable<DataReader>{
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
    public double getCalories(){return calories;}
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
                ", Intensity= " +  CheckIntensity() +
                ", Calories= " + String.format("%.1f", calories) + " kcal" +
                '}' +"\n";
    }

//if statement for each activity and then if for checking its speed and assigning its performance with enum Intensity
//also implementing our calories formula in this nested ifs
    public String CheckIntensity(){
        if(this.getActivity().equals(ActivityName.Swimming.toString())){
            if(this.getSpeed() <= 0.5){
                this.setCalories(5*(this.duration));
               return Inensity.VeryLight.toString();
            } else if(this.getSpeed() >0.5 && this.getSpeed() <= 1.25){
                this.setCalories(6.3*(this.duration));
                return Inensity.Light.toString();
            } else if(this.getSpeed() >1.25 && this.getSpeed() <=2.00){
                this.setCalories(7.6*(this.duration));
                return Inensity.Moderate.toString();
            } else if(this.getSpeed() >2.00 && this.getSpeed() <= 2.75){
                this.setCalories(8.9*(this.duration));
                return Inensity.Vigorous.toString();
            } else if(this.getSpeed() >2.75){
                this.setCalories(10.2*(this.duration));
                return Inensity.VeryVigorous.toString();
            }
        }
        if(this.getActivity().equals(ActivityName.Running.toString())){
            if(this.getSpeed() < 4.00){
                this.setCalories(4.1*(this.duration));
                return Inensity.VeryLight.toString();
            } else if(this.getSpeed() >= 4.00 && this.getSpeed() <8.00){
                this.setCalories(7.2*(this.duration));
                return Inensity.Light.toString();
            } else if(this.getSpeed() >= 8.00 && this.getSpeed() < 12.00){
                this.setCalories(10*(this.duration));
                return Inensity.Moderate.toString();
            } else if(this.getSpeed() >=12.00 && this.getSpeed() < 16.00){
                this.setCalories(15.4*(this.duration));
                return Inensity.Vigorous.toString();
            } else if(this.getSpeed() >= 16.00){
                this.setCalories(20.8*(this.duration));
                return Inensity.VeryVigorous.toString();
            }
        }
        if(this.getActivity().equals(ActivityName.Cycling.toString())){
            if(this.getSpeed() < 8.00){
                this.setCalories(2*(this.duration));
                return Inensity.VeryLight.toString();
            } else if(this.getSpeed() >= 8.00 && this.getSpeed() <= 16.00){
                this.setCalories(5*(this.duration));
                return Inensity.Light.toString();
            } else if(this.getSpeed() >= 17.00 && this.getSpeed() < 25.00 ){
                this.setCalories(7*(this.duration));
                return Inensity.Moderate.toString();
            } else if(this.getSpeed() >= 25.00 && this.getSpeed() <33.00){
                this.setCalories(13*(this.duration));
                return Inensity.Vigorous.toString();
            } else if(this.getSpeed() >= 33.00){
                this.setCalories(15*(this.duration));
                return Inensity.VeryVigorous.toString();
            }
        }
        return "!=intensity";
    }

//    Override the compareTo method from superclass to get sorted by natural ordering, name, otherwise it would just print hash
    @Override
    public int compareTo(DataReader other){
        return this.activity.compareTo(other.activity);
    }

}
