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
        String csvfilename = "ActivitySheet.csv";
//        DataList dataList = new DataList();
//        ArrayList<DataReader> dataList = new ArrayList<>();
//        DataList dataList = new DataList();
        List<DataReader> dataList = new ArrayList<>();

        try(Scanner scanner = new Scanner(new File("ActivitySheet.csv"))){
            if(scanner.hasNextLine()){
                scanner.nextLine();
            }
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] tokens = line.split(",");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

               String activity = tokens[0];
               Date date = sdf.parse(tokens[1]);
               double duration = Double.parseDouble(tokens[2]);
               double distance = Double.parseDouble(tokens[3]);
               int heartRate = Integer.parseInt(tokens[4]);
//                System.out.printf("%-20s %10s %5.2f %5.2f %5s %n", activity, date, duration, distance, heartRate);
//                 dataList.add(new DataReader(activity, date, duration, distance, heartRate));
                dataList.add(new DataReader(activity,date,duration,distance,heartRate));
            }
        } catch(FileNotFoundException exception){
            System.out.println("FileNotFoundException caught. The file " +csvfilename+ " may not exist." + exception);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println("*** Sorted by natural ordering: ***");
        Collections.sort(dataList);
        System.out.println(dataList);

        System.out.println("*** Sorted by calories burned descending: ***");
        Collections.sort(dataList, new CaloriesComparator());
        System.out.println(dataList);

        System.out.println("*** Sort by date with anonymous inner class ascending: ***");
        Collections.sort(dataList, new Comparator<DataReader>(){
            @Override
            public int compare(DataReader d1, DataReader d2){
                return d1.getDate().compareTo(d2.getDate());
            }
        });
        System.out.println(dataList);

        System.out.println("*** Sort by date with lambda descending");
        dataList.sort((DataReader data1, DataReader data2) -> data1.getDate().compareTo(data2.getDate())*(-1));
        System.out.println(dataList);

        System.out.println("*** Sort by duration ascending");
        Collections.sort(dataList, new DurationComparable());
        System.out.println(dataList);

        System.out.println("*** Sort by duration descending");
        Collections.sort(dataList, new Comparator<DataReader>() {
            @Override
            public int compare(DataReader data1, DataReader data2) {
                int IntDuration1 = (int)Math.round(data1.getDuration());
                int IntDuration2 = (int)Math.round(data2.getDuration());
                return (IntDuration2 - IntDuration1);
            }
        });
        System.out.println(dataList);

        System.out.println("*** Sort by distance: ***");
    }
}

//        System.out.println(dataList);
//        System.out.println(dataList.get(1).getSpeed());
//        for(DataReader datas : dataList){
//           datas.CheckIntensity();
//        }