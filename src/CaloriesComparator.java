import java.util.Comparator;

public class CaloriesComparator implements Comparator<DataReader> {
    @Override
    public int compare(DataReader data1, DataReader data2) {
            return Double.compare(data1.getCalories(), data2.getCalories())*(-1);
    }
}
