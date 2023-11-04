import java.util.Comparator;

public class CaloriesComparator implements Comparator<DataReader> {
    @Override
    public int compare(DataReader data1, DataReader data2) {
        int IntData1 = (int) data1.getCalories();
        int IntData2 = (int) data2.getCalories();
        return (IntData1 - IntData2)*(-1);
    }
}
