package priorityQueue;

public class HeapbasedPQ<K, V> {
	Entry<K, V>[] pq;
	int size;

	HeapbasedPQ() {
		this(1);
	}

	HeapbasedPQ(int initSize) {
		pq = (Entry[]) new Entry[initSize + 1];
		size = 0;
	}

	public void insert(Entry<K, V> x) {
		if (size == pq.length - 1) {
			resize(2 * pq.length);
		}
		pq[++size] = x;
		swim(size);
	}

	private void swim(int swimStart) {
		if (size == 1) {
			return;
		}
		int x = swimStart;
		while ((x / 2) >= 1) {
			if (greater(x / 2, x)) {
				swap(x, x / 2);
			}
			x = x / 2;
		}

	}

	private void swap(int i, int j) {
		Entry<K, V> firstItem = pq[i];
		pq[i] = pq[j];
		pq[j] = firstItem;
	}

	private boolean greater(int parent, int child) {
		Comparable parentKey = (Comparable) pq[parent].getKey();
		Comparable childKey = (Comparable) pq[child].getKey();
		return (parentKey.compareTo(childKey) > 0);
	}

	private void resize(int resize) {
		Entry<K, V>[] newPQ = (Entry<K, V>[]) new Entry[resize];
		for (int i = 0; i < pq.length; i++) {
			newPQ[i] = pq[i];
		}
		pq = newPQ;
	}

	public Entry<K, V> removeMin() {
		if (size == 0) {
			return null;
		} else if (size == 1) {
			Entry<K, V> retItem = pq[1];
			pq[1] = null;
			size--;
			return retItem;
		} else {
			Entry<K, V> retItem = pq[1];
			swap(1, size);
			size--;
			sink(1);
			// size--;
			return retItem;
		}
	}

	private void sink(int k) {
		while (2 * k <= size) {
			int j = 2 * k;
			if (j < size) {
				j = greater(j, j + 1) ? j + 1 : j;
			}
			if (greater(j, k)) {
				break;
			}
			swap(j, k);
			k = j;
		}

	}

	public static void main(String[] args) {
		HeapbasedPQ<Integer, String> hpq = new HeapbasedPQ<Integer, String>();
		hpq.insert(new Entry(1, "Vijay"));
		hpq.insert(new Entry(2, "Vidya"));
		hpq.insert(new Entry(3, "Vinod"));
		hpq.insert(new Entry(0, "JaiRam"));
		System.out.println(hpq.removeMin().getValue());
		System.out.println(hpq.removeMin().getValue());

	}

}
