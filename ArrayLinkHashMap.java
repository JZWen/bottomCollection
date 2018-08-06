package hashMap;

import com.sun.jdi.Value;

/**
 * ��������+����ķ�ʽȥʵ��HashMap
 * @author ������
 *
 */
public class ArrayLinkHashMap<Key,Value> {

	//�м�  ��ʼ��С��16 ��ֻ���ڲ���ʱʹ��һ��
	private Node<Key,Value>[] nodes = new Node[16];
	private int size ;
	
	public void put(Key key, Value value) {
		//�Ƚ���������ֵת���� Node 
		Node<Key, Value> node = new Node<Key, Value>(key, value);
		
		//hashMap���ݻ���
		//�����ж��ǲ�����Ҫ����
		
		if(size>nodes.length*0.75) {
			//����������0.75
			//����һ���µ�table
			Node<Key,Value>[] newNodes = new Node[nodes.length<<1];
			//��������nodes���� �ҳ���Щ���нڵ��Ԫ��
			for(int i=0; i<nodes.length; i++) {
				Node<Key , Value> oldnode = nodes[i];
				while(oldnode!=null) {
					//�ҵ��нڵ��Ԫ����ȥ�õ�һ���µ�index
					int index = getNewKey(oldnode.getKey(), nodes.length);	
					Node<Key,Value> nextNode = oldnode.next;
					nextNode = oldnode.next;  //������һ���ڵ�
					//oldnodeָ��newNodes[index]����λ��
					oldnode.next = newNodes[index];
					//�ٽ�oldnode��ӽ��� 
					newNodes[index] = oldnode;
					oldnode = nextNode;
				}
			}
			//�ٰ�newNode �� nodes 
			nodes = newNodes ;
			newNodes = null;
		}
		int index = key.hashCode()%nodes.length;
		System.out.println("hash  "+index);
		Node<Key,Value> temp = nodes[index];
		if(temp!=null) {
			//Ҫȥ����key����һ���ǲ����к�keyһ����ֵ
			while(temp!=null) {
				//��Ϊ�վ�Ҫȥ�Ƚ�
				if(temp.getKey().equals(key)) {
					//����keyһ���Ļ��Ǿ͸���
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
			//�������û����ͬ�Ļ� �ǽ����һ����nextָ�����Ǹո��½���node �����������
		}else {
			//Ϊ�յĻ���ֱ����Ӿͺ���
			size++;
			nodes[index]=node;
		}
	}
	
	//ͨ��key��ȡvalue
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
	
	//���췽��
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