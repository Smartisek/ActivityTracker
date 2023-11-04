import java.util.Comparator;

public class DurationComparable implements Comparator<DataReader> {
    @Override
    public int compare(DataReader data1, DataReader data2){
            return Double.compare(data1.getDuration(), data2.getDuration());
    }
}
