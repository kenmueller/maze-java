public final class UnionFind {
	private final int[] ids;
	private int count;

	public UnionFind(final int size) {
		ids = new int[size];
		count = size;

		for (int i = 0; i < size; i++)
			ids[i] = i;
	}

	public int getCount() {
		return count;
	}

	public boolean isConnected(final int a, final int b) {
		return find(a) == find(b);
	}

	public int find(final int i) {
		return ids[i];
	}

	public void union(final int a, final int b) {
		final int aId = find(a);
		final int bId = find(b);

		if (aId == bId)
			return;

		for (int i = 0; i < ids.length; i++)
			if (ids[i] == aId)
				ids[i] = bId;

		count--;
	}
}
