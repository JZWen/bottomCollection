package hashMap;

import java.util.LinkedList;

import connection.Linked;

/**
 * ��������ʵ��hashMap
 * @author ������
 *
 */
public class HashMapForLinkedList<Key, Value> {
	
	//Entry����linkedList�������Ӧ��ֵ ������������������
	private LinkedList<Entry>[] link = new LinkedList[998];  
	
	//дput()
	public void put(Key key,Value value) {
		//�ȵõ���Ӧ��hashֵ
		Entry entry = new Entry(key, value);
		int hashValue = key.hashCode()%link.length;
		LinkedList<Entry> linkedList = new LinkedList<>();
		linkedList = link[hashValue];
		if(linkedList == null) {  	//�������ֱ�Ӳ���link����ͻ����
			linkedList = new LinkedList<>();
			linkedList.add(entry);
			link[hashValue]=linkedList;
		}else {
			//����Ǹ��ط�hashcodeֵ��ͬ�Ļ� �Ǿ�Ӧ��Ҫ���жԱ�������key�ǲ������ ���key��ȵĻ� �Ǿͽ��㵱ǰ���Ǹ�key���ǵ���������ȵĻ�Ҳ��ֱ�Ӽ�
			//��������
 			for(Entry entry1 : linkedList) {
				if(entry.key.equals(entry1.key)) {    //����ǵ����Ҫ��keyΪһ������д�Ķ���Ļ���Ҫ��дequals()����
					//�ҵ�һ����key�Ļ� �Ǿ͸��ǿ�
					entry1.value = value;
					link[hashValue]	= linkedList;
					return ;
				}
			}
			//����Ļ����ڸ���������������hashcodeֵ
 			linkedList.add(entry);
 			link[hashValue] = linkedList;
		}
	}
	
	public Value get(Key key) {
		//�ȵõ�hashֵ
		int hashValue = key.hashCode()%link.length;
		
		//�ٴ�������ȥ��key�����key��ȵ�
		for(Entry entry : link[hashValue]) {
			if(entry.key.equals(key)) {
				return entry.value;
			}
		}
		return null;
	}
	class Entry{
		Key key;
		Value value;
		
		public Entry(Key key, Value value) {
			super();
			this.key = key;
			this.value = value;
		}
	}
	public static void main(String[] args) {
		HashMapForLinkedList<String, Integer> hashMapForLinkedList = new HashMapForLinkedList<>();
		hashMapForLinkedList.put("a", 1);
		hashMapForLinkedList.put("b", 2);
		hashMapForLinkedList.put("a", 3);
		System.out.println(hashMapForLinkedList.get("a"));
		
		
	}
	
}

	

//class ArrayHashMap{
//	
//	Object key;
//	Object value;
//	
//	//������������ �������д�Ŷ�Ӧ��ֵvalue
//	Object[] tables =  new Object[998];
//	
//	public void put (Object key , Object value) {
//		
//		int keyHash = key.hashCode();
//	 	int	index = keyHash % 998;
//	 	tables[index] = value;
//	}
//	
//	public Object get(Object key) {
//		
//		int index = key.hashCode()%998;
//		return tables[index];
//	}
//	
//}
