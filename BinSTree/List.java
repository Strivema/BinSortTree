package BinSTree;

public interface List<T> {
	boolean isEmpty();
	int length();
	T get(int i);
	void set(int i,T x);
	void add(int i,T x);
	T remove(int i);
	int indexOf(T x);
}
