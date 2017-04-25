package BinSTree;

public class BinSTree <T extends Comparable<T>> {
	private int num;
	private BinSNode<T> root;
	public BinSTree(){
		root = null;
		num =0;
	}
	public BinSTree(BinSNode<T> root){
		this.root =  root;
		num = 0;
	}
	public int size(){
		return num;
	}
	public SeqList<T> preOrder(){
		SeqList<T> pre = new SeqList<T>(size());
		preOrderBy(root,pre);
		return pre;
	}
	
	private void preOrderBy(BinSNode<T> root,SeqList<T> pre){
		if(root!=null){
			pre.add(root.data);
			preOrderBy(root.lchild,pre);
			preOrderBy(root.rchild,pre);
		}
	}
	
	public SeqList<T> inOrder(){
		SeqList<T> in = new SeqList<T>(size());
		inOrderBy(root,in);
		return in;
	}
	private void inOrderBy(BinSNode<T> root,SeqList<T> in){
		if(root!= null){
			inOrderBy(root.lchild,in);
			in.add(root.data);
			inOrderBy(root.rchild,in);
		}
	}
	public SeqList<T> postOrder(){
		SeqList<T> post = new SeqList<T>(size());
		postOrderBy(root,post);
		return post;
	}
	private void postOrderBy(BinSNode<T> root,SeqList<T> post){
		if(root!=null){
			postOrderBy(root.lchild,post);
			postOrderBy(root.rchild,post);
			post.add(root.data);
		}
	}
	
	public String toString(){
		return inOrder().toString();
	}
	public T max(){
		BinSNode<T> maxNode = findMax(this.root);
		if(maxNode==null) return null;
		else return maxNode.data;
	}
	protected BinSNode<T> findMax(BinSNode<T> node){
		if(node!=null){
			while(node.rchild!=null){
				node = node.rchild;
			}
		}
		return node;
	}
	
	public T min(){
		BinSNode<T> minNode = findMin(this.root);
		if(minNode==null) return null;
		else return minNode.data;
	}
	protected BinSNode<T> findMin(BinSNode<T> node){
		if(node!=null){
			while(node.lchild!=null){
				node = node.lchild;
			}
		}
		return node;
	}
	
	public T search(T key){
		BinSNode<T> d;
		d = searchByRe(this.root,key);
		//d = searchByLoop(this.root,key,new BinSNode[1]);
		if(d == null){
			return null;
		}else return d.data;
	}
	public boolean con(T key){
		if(searchByLoop(this.root,key,new BinSNode[1])==null)
			return false;
		else return true;
	}
	//递规实现
	private BinSNode<T> searchByRe(BinSNode<T> root,T key){
		if(root == null)  return null;
		int cmp = key.compareTo(root.data);
		if(cmp==0) return root;
		else if(cmp<0) return searchByRe(root.lchild,key);
		else return searchByRe(root.rchild,key);
	}
	//循环实现
	private BinSNode searchByLoop(BinSNode<T> root,T key,BinSNode<T> []parent){
		if(root==null) return null;
		BinSNode<T> p = root;
		while(p!=null){
			if(key.equals(p.data)){
				break;
			}
			parent[0] = p;
			if(key.compareTo(p.data)<0) p = p.lchild;
			else p = p.rchild;
		}
		return p;
	}
	public void add(T e){
		this.root = insertByLoop(this.root,e);
		
	}
	//循环插入
	private BinSNode<T> insertByLoop(BinSNode<T> node,T key){
		BinSNode<T> p = null;
		BinSNode<T> parent[] = new BinSNode[1];
		BinSNode<T> s = new BinSNode<T>(key); 
		if(node==null) node = s;
		else{
			p = searchByLoop(node,key,parent);
			if(p==null){
				if(key.compareTo(parent[0].data)<=0){
					parent[0].lchild = s;
				}else
					parent[0].rchild = s;
			}
		}
		this.num++;
		return node;
	}
	private BinSNode<T> insertByRe(BinSNode<T> node,T key){
		if(node ==null){
			BinSNode<T> p = new BinSNode<T>(key);
			this.num++;
			return p;
		}else{
			if(key.compareTo(node.data)<=0){
				node.lchild = insertByRe(node.lchild,key);
			}else
				node.rchild = insertByRe(node.rchild,key);
		}
		return node;
	}
	
	public void remove(T key){
		
	}
	
	private BinSNode<T> deleteByLoop(BinSNode<T> node,T key){
		BinSNode<T> p,q,s;
		BinSNode<T> parent[] = new BinSNode[1];
		p = searchByLoop(node,key,parent);
		if(p==null){
			return null;
		}if(p.lchild!=null&&p.rchild!=null){
			q = p;
			s = p.lchild;
			while(s.rchild!=null){
				q = s;
				s = s.rchild;
			}
			p.data = s.data;
			if(q!=p){
				q.rchild = s.lchild;
			}
			else
				q.lchild = s.lchild;
			num--;
			return s;
		}else{
			if(p.rchild==null)
				q =p.lchild;
			else
				q = p.rchild;
			if(parent[0]==null){
				this.root = q;
			}else if(q==parent[0].lchild){
				parent[0].lchild = q;
			}else
				parent[0].rchild = q;
			num--;
			return p;
		}		
	}
	private BinSNode<T> deleteByRe(BinSNode<T> node,T key){
		BinSNode<T> s = null;
		if(node==null) return null;
		else if(key.compareTo(node.data)<0){
			node.lchild = deleteByRe(node.lchild,key);
		}else if(key.compareTo(node.data)>0)
			node.rchild = deleteByRe(node.rchild,key);
		else if(node.lchild!=null&&node.rchild!=null){
			s = findMax(node.lchild);
			node.data = s.data;
			node.lchild = deleteByRe(node.lchild,s.data);
		}else{
			if(node.lchild==null){
				node = node.rchild;
			}else if(node.rchild==null){
				node = node.lchild;
			}
			num--;
		}
	return node;
	}
}

class BinSNode<T extends Comparable<T>>implements Comparable<BinSNode<T>>{

	protected T data;
	protected BinSNode<T> lchild,rchild;
	public BinSNode(){
		data = null;
		lchild = null;
		rchild = null;
	}
	public BinSNode(T key){
		data = key;
		lchild = null;
		rchild = null;
	}
	public BinSNode(T key,BinSNode lchild,BinSNode rchild){
		data = key;
		this.lchild = lchild;
		this.rchild = rchild;
	}
	public boolean equals(BinSNode<T> e){
		return data.equals(e);
	}
	@Override
	public int compareTo(BinSNode<T> o) {
		// TODO Auto-generated method stub
		return data.compareTo(o.data);
	}
	
}