//raw array solution one
class LRUCache {

    private int[] a;
    private int N;

    public LRUCache(int capacity) { 
      a = new int[2 * capacity]; 
      for (int i = 0; i < 2 * capacity; i++)
        a[i] = -1;
    }

    public void overrideAndInsert(int k, int key, int value) {
      for (int i = k; i > 1; i--) {
        a[i+1] = a[i-1];
        a[i] = a[i-2];
      } 
      a[0] = key; a[1] = value;
    }

    public void shiftAndInsert(int k, int key, int value) {
      for (int i = k; i > 1; i--)
            a[i] = a[i - 2];
      a[0] = key; a[1] = value;
    }

    public int get(int key) {
      for (int i = 0; i < a.length; i += 2) 
        if (a[i] == key) {
          if (i != 0) 
            overrideAndInsert(i, key, a[i+1]);
          return a[1];
        }
      return -1;
    }

    public int getIndex(int key) {
      for (int i = 0; i < a.length; i += 2) 
        if (a[i] == key) return i;
      return -1;
    }
    
    public void put(int key, int value) {
      int k = getIndex(key);
      if (k != -1) {
        overrideAndInsert(k, key, value);
      } else {
        if (N == a.length / 2)
          shiftAndInsert(a.length-1, key, value);
        else {
          shiftAndInsert(2*N+1, key, value);
          N++;
        }
      }
    }
}
