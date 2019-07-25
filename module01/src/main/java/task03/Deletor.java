package task03;

import java.util.Collection;
import java.util.Iterator;

@FunctionalInterface
public interface Deletor<P, T> {
    P delete(T pointer);

    /**
     * Deleting item from given collection by its position.
     * */
    default P deleteItemFromCollection(Collection<P> collection, int position){
        P element = (P) findElement(collection, position);

        if(element == null){
            throw new IndexOutOfBoundsException("Element with index " +
                    position + " can't be removed.");
        }

        collection.remove(element);

        return element;
    }

    /**
     * Looking for and returning an element by position in the given
     * collection.
     * */
    static Object findElement(Collection collection, int position){
        Iterator it = collection.iterator();

        for(int i = 0; i < position; i++){
            if(it.hasNext()) {
                it.next();
            }
        }

        return it.hasNext()? it.next(): null;
    }
}