import java.util.ArrayList;

public class SortedArrayList<E extends  Comparable<E>> extends ArrayList<E> {


    public SortedArrayList(){
        super();
    }

    public void addItem(E element) {
        Integer index = 0;

        for (E elem: this) {
            if (elem.compareTo(element) > 0) {
                index = this.indexOf(elem);
                break;
            }
            else {
                index++;
            }
        }
        this.add(index, element);
    }
}
