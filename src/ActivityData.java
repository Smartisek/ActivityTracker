import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ActivityData{
    public static void main(String[] args) throws FileNotFoundException{
        String csvfilename = "ActivitySheet.csv";
//        DataList dataList = new DataList();
//        ArrayList<DataReader> dataList = new ArrayList<>();
        DataList dataList = new DataList();

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
                dataList.addEntry(new DataReader(activity,date,duration,distance,heartRate));
            }
        } catch(FileNotFoundException exception){
            System.out.println("FileNotFoundException caught. The file " +csvfilename+ " may not exist." + exception);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        System.out.println("*** Sorted by natural ordering: ***");
        dataList.sort();
        dataList.Display();

        System.out.println("*** Sort by calories burned descending: ***");
        dataList.sortByCalories();
        dataList.Display();
    }
}

//        System.out.println(dataList);
//        System.out.println(dataList.get(1).getSpeed());
//        for(DataReader datas : dataList){
//           datas.CheckIntensity();
//        }