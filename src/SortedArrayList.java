import java.util.ArrayList;

public class SortedArrayList<E> extends ArrayList<E> {

    public SortedArrayList(){
        super();
    }

    @Override
    public void add(int index, E element) {
        if (element instanceof Client) {
            super.add(element);
        }
        if (element instanceof  Event) {
            super.add(element);
        }
    }
}
