import java.lang.reflect.Array;
import java.util.Arrays;

public class LinearProbing<K, V> {
    private static final int INIT_CAPACITY = 10;
    private double loadFactor = 0.5;
    private int treshold;
    private int countOfItems;
    private int capasity;
    private K[] keys;
    private V[] values;

    public LinearProbing() {
        countOfItems = 0;
        keys = (K[]) new Object[INIT_CAPACITY];
        values = (V[]) new Object[INIT_CAPACITY];
        capasity = INIT_CAPACITY;
        treshold = (int) (capasity * loadFactor);
    }

    public LinearProbing(int capasity) {
        countOfItems = 0;
        this.capasity = capasity;
        keys = (K[]) new Object[this.capasity];
        values = (V[]) new Object[this.capasity];
        treshold = (int) (capasity * loadFactor);
    }

    public int size() {
        return countOfItems;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public int hash(K key) {
        return key.hashCode() % capasity;
    }

    @Override
    public String toString() {
        return "LinearProbing{" + "loadFactor=" + loadFactor + ", treshold=" + treshold + ", countOfItems=" + countOfItems + ", capasity=" + capasity + ", keys=" + Arrays.toString(keys) + ", values=" + Arrays.toString(values) + '}';
    }

    public void resize(int capasity) {
        this.capasity = capasity;
        values = Arrays.copyOf(values, this.capasity);
        keys = Arrays.copyOf(keys, this.capasity);
        treshold = (int) (capasity * loadFactor);
    }

    public void put(K key, V value) {
        if (treshold == countOfItems) {
            resize(capasity + 5);
        }
        int tmp = hash(key);
        int i = tmp;

        do {
            if (keys[i] == null) {
                keys[i] = key;
                values[i] = value;
                countOfItems++;
                return;
            }
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
            i = (i + 1) % capasity;
        } while (i != tmp);
    }

    public V get(K key) {
        int i = hash(key);
        while (keys[i] != null) {
            if (keys[i].equals(key)) {
                return values[i];
            }
            i = (i + 1) % capasity;
        }
        return null;
    }

    public int getBucketIndex(K key) {
        return hash(key);
    }

    public void delete(K key) {
        if (!contains(key)) {
            return;
        }
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % capasity;
        }
        keys[i] = null;
        values[i] = null;

        for (i = (i + 1) % capasity; keys[i] != null; i = (i + 1) % capasity) {
            K tmp1 = keys[i];
            V tmp2 = values[i];
            keys[i] = null;
            values[i] = null;
            countOfItems--;
            put(tmp1, tmp2);
        }
        countOfItems--;
    }

    public Iterable<K> keysI() {
        return Arrays.stream(keys).toList();
    }


}


