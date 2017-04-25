package BinSTree;

import java.util.Arrays;
import java.util.Iterator;

public class SeqList<T> extends AbsList<T> implements Iterable<T>{
	
	private int increSize;
	protected T []el;
	public SeqList(){
		//this(16);
	}
	public SeqList(T[]elem){
	length = elem.length;
	increSize = 0;
	el = Arrays.copyOf(elem, length);
	}
	public void setInc(int inc){
		this.increSize = inc;
	}
	public int size(){
		return length;
	}
	
	public int indexOf(T o){
		if(o==null){
			for(int i=0;i<length;i++){
				if(el[i]==null){
					return i;
				}
				else{
					for(int j=0;j<length;j++){
						if(compare(o,(T)el[j])==0){
						return i;	
						}
					}
				}
			}
		}
		return -1;
	}
	
	private int compare(T a, T b) {
		// TODO Auto-generated method stub
		if(a instanceof Comparable && b instanceof Comparable)
			return ((Comparable) a).compareTo((Comparable)b);
		else
			return ((String) a).compareTo((String)b);
	}
	public SeqList(int size) {
		// TODO Auto-generated constructor stub
		if(size<=0) size = 16;
		length =0;
		increSize =0;
		//data = new Object[];
	}

	public void add(T data) {
		// TODO Auto-generated method stub
		el[length] = data;
		length++;
	}

	@Override
	public T get(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set(int i, T x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int indexOf(int begin, int end, T o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void add(int i, T x) {
		// TODO Auto-generated method stub
		if(i<0) i=0;
		if(i>length) i = length;
		for(int j = length-1;j>=i;j--){
			el[j+1] =el[j];
			el[i] = x;
			length++;
		}
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T remove(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
