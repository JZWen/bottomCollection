package hashMap;

import com.sun.jdi.Value;

/**
 * 基于数组+链表的方式去实现HashMap
 * @author 蒋子文
 *
 */
public class ArrayLinkHashMap<Key,Value> {

	//切记  初始大小是16 我只是在测试时使用一下
	private Node<Key,Value>[] nodes = new Node[16];
	private int size ;
	
	public void put(Key key, Value value) {
		//先将穿过来的值转换成 Node 
		Node<Key, Value> node = new Node<Key, Value>(key, value);
		
		//hashMap扩容机制
		//首先判断是不是需要扩容
		
		if(size>nodes.length*0.75) {
			//加载因子是0.75
			//创建一个新的table
			Node<Key,Value>[] newNodes = new Node[nodes.length<<1];
			//遍历整个nodes数组 找出那些具有节点的元素
			for(int i=0; i<nodes.length; i++) {
				Node<Key , Value> oldnode = nodes[i];
				while(oldnode!=null) {
					//找到有节点的元素先去得到一个新的index
					int index = getNewKey(oldnode.getKey(), nodes.length);	
					Node<Key,Value> nextNode = oldnode.next;
					nextNode = oldnode.next;  //保存下一个节点
					//oldnode指向newNodes[index]的首位置
					oldnode.next = newNodes[index];
					//再将oldnode添加进来 
					newNodes[index] = oldnode;
					oldnode = nextNode;
				}
			}
			//再把newNode 给 nodes 
			nodes = newNodes ;
			newNodes = null;
		}
		int index = key.hashCode()%nodes.length;
		System.out.println("hash  "+index);
		Node<Key,Value> temp = nodes[index];
		if(temp!=null) {
			//要去看看key，找一下是不是有和key一样的值
			while(temp!=null) {
				//不为空就要去比较
				if(temp.getKey().equals(key)) {
					//两个key一样的话那就覆盖
					temp.setValue(value);
					//System.out.println(key);
					nodes[index] = temp;
					this.size++;
					return;
				}
				temp=temp.next;
			}
			temp=nodes[index];
			while(temp.next != null) {}
			temp.next = node;
			size++;
			System.out.println(key);
			//如果还是没有相同的话 那将最后一个的next指向我们刚刚新建的node 这样就添加了
		}else {
			//为空的话就直接添加就好了
			size++;
			nodes[index]=node;
		}
	}
	
	//通过key获取value
	public Value get(Key key) {
		int index = key.hashCode()%nodes.length;
		Node temp = nodes[index];
		while(temp!=null) {
			if(temp.getKey().equals(key)) {
				return (Value) temp.getValue();
			}
			temp = temp.next;
		}
		
		return null;
	}
	
	public int getNewKey(Key key, int length) {
		return key.hashCode()%length;
	}
	
	public static void main(String[] args) {
		ArrayLinkHashMap<String,Integer> hashMap = new ArrayLinkHashMap<>();
		hashMap.put("a", 1);
		hashMap.put("a" ,3);
		hashMap.put("b", 5);
		hashMap.put("d", 1);
		hashMap.put("f" ,3);
		hashMap.put("g", 5);
		hashMap.put("h", 1);
		hashMap.put("j" ,3);
		hashMap.put("k", 5);
		System.out.println(hashMap.get("k"));
	}
	
}
class Node<Key , Value>{
	private Key key;
	private Value value;
	public Node<Key,Value> next;
	
	//构造方法
	public Node(Key key,Value value){
		this.key = key;
		this.value = value;
	}
	public void setValue(Value value) {
		this.value = value;
	}
	public Value getValue() {
		return this.value;
	}
	public Key getKey() {
		return this.key;
	}
}