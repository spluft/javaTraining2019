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
        P element = findElement(collection, position);

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
    // we can have generic in static methods,
    // but static generic method needs to have a separate generic declaration,
    // as it's not aware about the class one
    static <K> K findElement(Collection<K> collection, int position){
        Iterator<K> it = collection.iterator();

        for(int i = 0; i < position; i++){
            if(it.hasNext()) {
                it.next();
            }
        }

        return it.hasNext()? it.next(): null;
    }
}