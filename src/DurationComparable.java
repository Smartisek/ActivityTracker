import java.util.Comparator;

public class DurationComparable implements Comparator<DataReader> {
    @Override
    public int compare(DataReader data1, DataReader data2){
        int IntDuration1 = (int) data1.getDuration();
        int IntDuration2 = (int) data2.getDuration();
        return (IntDuration1 - IntDuration2);
    }
}
