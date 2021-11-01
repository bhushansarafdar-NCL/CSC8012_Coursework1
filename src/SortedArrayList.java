import java.util.ArrayList;
import java.util.Comparator;

public class SortedArrayList<E> extends ArrayList<E> {

    public SortedArrayList(){
        super();
    }

    @Override
    public void add(int index, E element) {
        super.add(index, element);
    }

    @Override
    public void sort(Comparator<? super E> c) {
        super.sort(c);
    }
}
