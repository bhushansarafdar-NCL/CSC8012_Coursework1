import java.util.ArrayList;

public class SortedArrayList<E> extends ArrayList<E> {

    public SortedArrayList(){
        super();
    }

    @Override
    public boolean add(E element) {
        if (element instanceof Client) {
            E minClient = this.get(0);
            for (E elem: this) {
//                if (elem.compareTo(minClient) < 0)
            }
            super.add(element);
        }
        if (element instanceof  Event) {
            super.add(element);
        }
        return true;
    }
}
