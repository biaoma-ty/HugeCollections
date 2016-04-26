package hugecollections;

/**
 * An oversize collection is made to be interpreted in the same way as the default
 * Collection interface.
 */
public interface OversizeList<E> {

    public E get(long index);

    public void set(long index, E object);

    public long size();
}
