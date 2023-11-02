import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ActivityData{
    public static void main(String[] args) throws FileNotFoundException{
        String csvfilename = "ActivitySheet.csv";

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
                DataReader data1 = new DataReader(activity, date, duration, distance, heartRate);
                System.out.println(data1.getActivity() + ", " + data1.getDistance());

            }
        } catch(FileNotFoundException exception){
            System.out.println("FileNotFoundException caught. The file " +csvfilename+ " may not exist." + exception);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }




}
