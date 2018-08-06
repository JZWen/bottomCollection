package hashMap;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于ArrayList的实现hashMap
 * 	效率不高  添加 删除 查询 太多循环 从头查到尾  
 * @author 蒋子文
 *
 */
public class HashMapForArrayList <Key,Value>{
	
	
	private List<Entry<Key,Value>> tables = new ArrayList<Entry<Key, Value>>() ;
	
	/*
	 * 先判断这个key是否存在？？
	 * 如果存在的话就直接讲之前的value覆盖就行 不存在的话就直接添加
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
	
	//通过key返回一个value 还写得不好 这里面不应该直接去访问key 和 value 应该要将这两个封装起来 
	public  Value get(Key key) {
		for(Entry<Key, Value> entry : tables) {
			if(entry.key.equals(key)) {
				return entry.value;
			}
		}
		return null;
	}
	
	
	//remove方法
	public void remove(Key key) {
		for(Entry<Key, Value> entry : tables) {
			if(entry.key.equals(key)) {
				tables.remove(entry);
			}
		}
	}
	
	/*
	 * 重要的类 entry 主要用于存放Map中的值
	 */
	static class Entry<Key , Value>{
		Key key;  
 		Value value;  
		
		//构造方法
		public Entry(Key key, Value value) {
			super();
			this.key = key;
			this.value = value;
		}
		
	}
	
	//如果我们添加的元素是一个这样的对象
	 static class User {
		private String name ;
		private int age;
		
		public User(String name, int age) {
			this.age = age;
			this.name = name;
		}
		
		/*
		 * 在set集合中常用，在map中不常用
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
