import javax.lang.model.element.Element;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

public class DataReader{
    public String activity;
    public Date date;
    public double duration;
    public double distance;
    public int heartRate;
    public double speed;

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

    public double getSpeed(){
        this.speed = (this.distance / this.duration);
        return speed;
    }

    @Override
    public String toString() {
        return "DataReader{" +
                "activity='" + activity + '\'' +
                ", date=" + date +
                ", duration=" + duration +
                ", distance=" + distance +
                ", heartRate=" + heartRate +
                '}';
    }
}
