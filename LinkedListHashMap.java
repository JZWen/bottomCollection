package hashMap;

import java.util.LinkedList;

import connection.Linked;

/**
 * 基于链表实现hashMap
 * @author 蒋子文
 *
 */
public class HashMapForLinkedList<Key, Value> {
	
	//Entry代表linkedList里面存相应的值 这里是链表数组类型
	private LinkedList<Entry>[] link = new LinkedList[998];  
	
	//写put()
	public void put(Key key,Value value) {
		//先得到对应的hash值
		Entry entry = new Entry(key, value);
		int hashValue = key.hashCode()%link.length;
		LinkedList<Entry> linkedList = new LinkedList<>();
		linkedList = link[hashValue];
		if(linkedList == null) {  	//如果这里直接操作link数组就会出错
			linkedList = new LinkedList<>();
			linkedList.add(entry);
			link[hashValue]=linkedList;
		}else {
			//如果那个地方hashcode值相同的话 那就应该要进行对比这两个key是不是相等 如果key相等的话 那就将你当前的那个key覆盖掉，如果不等的话也是直接加
			//遍历链表
 			for(Entry entry1 : linkedList) {
				if(entry.key.equals(entry1.key)) {    //另外记得这个要是key为一个我们写的对象的话，要重写equals()方法
					//找到一样的key的话 那就覆盖咯
					entry1.value = value;
					link[hashValue]	= linkedList;
					return ;
				}
			}
			//否则的话就在该链表里面添加这个hashcode值
 			linkedList.add(entry);
 			link[hashValue] = linkedList;
		}
	}
	
	public Value get(Key key) {
		//先得到hash值
		int hashValue = key.hashCode()%link.length;
		
		//再从链表中去找key与参数key相等的
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
//	//创建对象数组 在数组中存放对应的值value
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
