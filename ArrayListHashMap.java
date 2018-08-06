package hashMap;

import java.util.ArrayList;
import java.util.List;

/**
 * ����ArrayList��ʵ��hashMap
 * 	Ч�ʲ���  ��� ɾ�� ��ѯ ̫��ѭ�� ��ͷ�鵽β  
 * @author ������
 *
 */
public class HashMapForArrayList <Key,Value>{
	
	
	private List<Entry<Key,Value>> tables = new ArrayList<Entry<Key, Value>>() ;
	
	/*
	 * ���ж����key�Ƿ���ڣ���
	 * ������ڵĻ���ֱ�ӽ�֮ǰ��value���Ǿ��� �����ڵĻ���ֱ�����
	 */
	public void put(Key key, Value value) {
		Entry<Key, Value> entry = new Entry<Key, Value>(key, value);
		
		for(Entry<Key, Value> entry2 : tables) {
			if(entry.key.equals(entry2.key)) {
				entry2.value = entry.value;
				return ;
			}
		}
		tables.add(entry);
	}
	
	//ͨ��key����һ��value ��д�ò��� �����治Ӧ��ֱ��ȥ����key �� value Ӧ��Ҫ����������װ���� 
	public  Value get(Key key) {
		for(Entry<Key, Value> entry : tables) {
			if(entry.key.equals(key)) {
				return entry.value;
			}
		}
		return null;
	}
	
	
	//remove����
	public void remove(Key key) {
		for(Entry<Key, Value> entry : tables) {
			if(entry.key.equals(key)) {
				tables.remove(entry);
			}
		}
	}
	
	/*
	 * ��Ҫ���� entry ��Ҫ���ڴ��Map�е�ֵ
	 */
	static class Entry<Key , Value>{
		Key key;  
 		Value value;  
		
		//���췽��
		public Entry(Key key, Value value) {
			super();
			this.key = key;
			this.value = value;
		}
		
	}
	
	//���������ӵ�Ԫ����һ�������Ķ���
	 static class User {
		private String name ;
		private int age;
		
		public User(String name, int age) {
			this.age = age;
			this.name = name;
		}
		
		/*
		 * ��set�����г��ã���map�в�����
		 */
		public boolean equals(User user) {
			if(this.name.equals(user.name) && this.age == user.age)
			return true;
			return false;
		}
	}
	
	public static void main(String[] args) {
		
		HashMapForArrayList<String, User> hashMap = new HashMapForArrayList<String ,User>();
		
		User user1 = new User("jzw", 10);
		User user2 = new User("jzw", 10);
		User user3 = new User("jzw", 20);
		
		hashMap.put("1" , user1);
		hashMap.put("2" , user2);
		hashMap.put("3" , user3);
		
		System.out.println(hashMap.get("1"));
		System.out.println(hashMap.get("2"));
	}

}
