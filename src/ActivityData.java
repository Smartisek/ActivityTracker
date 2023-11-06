import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Collections;
import java.util.Comparator;



public class ActivityData{
    public static void main(String[] args) throws FileNotFoundException{
        String csvfilename = "activity_data_10.csv"; //Name of the data file
        List<DataReader> dataList = new ArrayList<>(); // Creating new list where all this data will be put in
//Create a new scanner for data file and if it has a next line go to next line
        try(Scanner scanner = new Scanner(new File("activity_data_10.csv"))){
            if(scanner.hasNextLine()){
                scanner.nextLine();
            }
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] tokens = line.split(","); //Make every attribute split with ","
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Making data format for our date data
//Splitting our data with tokens
               String activity = tokens[0];
               Date date = sdf.parse(tokens[1]);
               double duration = Double.parseDouble(tokens[2]);
               double distance = Double.parseDouble(tokens[3]);
               int heartRate = Integer.parseInt(tokens[4]);
//                System.out.printf("%-20s %10s %5.2f %5.2f %5s %n", activity, date, duration, distance, heartRate);
// Feeding our list with data from the file by creating a new class that takes in all below
                dataList.add(new DataReader(activity,date,duration,distance,heartRate));
            }
        } catch(FileNotFoundException exception){ //If file is not found throw an exception
            System.out.println("FileNotFoundException caught. The file " +csvfilename+ " may not exist." + exception);
        } catch (ParseException e) { //Exception for our date type
            throw new RuntimeException(e);
        }
        System.out.println("*** Sorted by natural ordering (activity type): ***");
        Collections.sort(dataList);
        System.out.println(dataList);

        System.out.println("*** Sorted by calories burned descending: ***");
        Collections.sort(dataList, new CaloriesComparator());
        System.out.println(dataList);

        System.out.println("*** Sort by date with lambda ascending: ***");
        dataList.sort((data1, data2) -> data1.getDate().compareTo(data2.getDate()));
        System.out.println(dataList);

        System.out.println("*** Sort by date with lambda descending: ***");
        dataList.sort((DataReader data1, DataReader data2) -> data1.getDate().compareTo(data2.getDate())*(-1));
        System.out.println(dataList);

        System.out.println("*** Sort by duration ascending");
        Collections.sort(dataList, new DurationComparable());
        System.out.println(dataList);

        System.out.println("*** Sort by duration descending with anonymous class: ***");
        Collections.sort(dataList, new Comparator<DataReader>() {
            @Override
            public int compare(DataReader data1, DataReader data2) {
                return Double.compare(data1.getDuration(), data2.getDuration())*(-1);
            }
        });
        System.out.println(dataList);

        System.out.println("*** Sort by distance ascending: ***");
        Collections.sort(dataList, new Comparator<DataReader>() {
            @Override
            public int compare(DataReader data1, DataReader data2) {
                return Double.compare(data1.getDistance(), data2.getDistance());
            }
        });
        System.out.println(dataList);

        System.out.println("*** Sort by distance descending: ***");
        Collections.sort(dataList, new Comparator<DataReader>() {
            @Override
            public int compare(DataReader data1, DataReader data2) {
                return Double.compare(data1.getDistance(), data2.getDistance())*(-1);
            }
        });
        System.out.println(dataList);

// Creating a new comparator to be able to use binary search for activity
        Comparator<DataReader> dataGetActivity = new Comparator<DataReader>() {
            public int compare(DataReader data1, DataReader data2){
                return data1.getActivity().compareTo(data2.getActivity());
            }
        };
//Key component we want to search, activity, other values do not matter here
//We did not manage to search all the activities run here with binary search, we had an idea to use for loop but that
//Just printed the same column just as many times as we have total activities
        DataReader keyRunning = new DataReader("Running", null, 0, 0, 0);
        int index = Collections.binarySearch(dataList, keyRunning, dataGetActivity);
        if(index >= 0){
            System.out.println("Found " + dataList.get(index) + " at index " + index);
        } else{
            System.out.println("Not found in the list");
        }
// For each loop to keep counter of how many of each is in the list and checking separate activity,
// Then for each add its distance to a variable
        double totalDistanceRun = 0;
        double totalDistanceSwim = 0;
        double totalDistanceCycle = 0;
        double totalCalories = 0;
        int counterRun = 0;
        int counterSwim = 0;
        int counterCycle = 0;
        int counterTotal = 0;
        for(DataReader data : dataList){
            if(data.getActivity().equals("Running")){
                counterRun++;
                totalDistanceRun += data.getDistance();
            } else if(data.getActivity().equals("Swimming")){
                counterSwim++;
                totalDistanceSwim += data.getDistance();
            } else if(data.getActivity().equals("Cycling")){
                counterCycle++;
                totalDistanceCycle += data.getDistance();
            }
            totalCalories += data.getCalories();
            counterTotal++;
        }
//        Implementing average formulas into single variables
        double averageDistanceRun = totalDistanceRun/counterRun;
        double averageDistanceSwim = totalDistanceSwim/counterSwim;
        double averageDistanceCycle = totalDistanceCycle/counterCycle;
        double averageCalories = totalCalories/counterTotal;

        System.out.println("*** Average distance for running: " + String.format("%.1f", averageDistanceRun) + " ***");
        System.out.println("*** Average distance for swimming: " + String.format("%.1f", averageDistanceSwim) + " ***");
        System.out.println("*** Average distance for cycling: " + String.format("%.1f", averageDistanceCycle) + " ***");
        System.out.println("*** Average calories burned: " + String.format("%.1f", averageCalories) + " kcal ***");
    }
}