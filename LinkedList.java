package connection;

/**
 * linkedList底层实现
 * 采用链表存储 双向链表
 * 重要点 就有first节点 和last节点
 * first节点  查询起始点，last节点插入删除起始点（这样就不要去遍历整个链表了，但是只对于那种在最末端插入删除的情况）
 * first和last也是提高了一定的效率
 * 
 * @author 蒋子文
 *
 */

public class MyLinkedList {
	
	Entry first = new Entry();
	Entry last = new Entry();
	int size = 0;
	
	class Entry{
		Object data;
		Entry next; 
		Entry prev;
	}
	
	public void add(Object data) {
		if(this.size == 0) {
			//如果是第一个的话 做一下操作
			Entry entry = new Entry();
			entry.data = data;
			first = entry;
			last = entry;
			this.size++;
		}
		else {
			//不是第一个插入的话就直接插入就好了
			Entry entry = new Entry();
			entry.data = data;
			entry.prev = last;
			last.next = entry;
			last = entry;
			this.size++;
		}
	}
	//指定位置 添加元素
	public void add(int index , Object data) {
		Entry entry = new Entry();
		entry.data = data;
		if(!isData(index)) {
			entry = null;
			return;
		}
		/*
		 * 当然这里还是应该判断一下是不是首尾位置
		 */
		else if(index == 0){
			entry.next = first;
			first.prev = entry;
			first = entry;
			this.size++;
		}
		else if(index == this.size-1) {
			add(data); //直接调用方法添加
		}
		else {
			Entry oldEntry = new Entry();
			oldEntry = first;
			for(int i=0; i<=index-1 ; i++) {
				oldEntry = oldEntry.next;
			}
			//左边连接起来
			oldEntry.prev.next = entry;
			entry.prev = oldEntry.prev;
			//右边连接起来
			entry.next = oldEntry;
			oldEntry.prev = entry;
			this.size++;
		}
	}
	//通过下标去得到对应的值
	public Object get(int index) {
		//不合法下标返回null
		if(!isData(index)) {
			return null;
		}
		else {
			Entry entry = new Entry();
			entry = first;
			//循环找到位置
			for(int i=0; i<index-1; i++) {
				entry = entry.next;
			}
			return entry.data;
		}
	}
	
	//删除最后一个元素
	public void remove() {
		//如果没有元素
		if(this.size == 0) {
			return ;
		}
		//如果只有一个元素
		else if(this.size == 1) {
			first = null;
			last = null;
			this.size --;
		}
		else {
			last = last.prev;
			this.size --;
		}
	}
	
	//指定位置删除元素
	public void remove(int index) {
		if (!isData(index)) {
			return;
		}
		//加一个判断 是不是最后一个index 因为这不能直接删 我们不能没有last节点
		else if(index == this.size-1){
			//last就是最后一个元素的标记 只要把last节点向前移一个就可以
			System.out.println(this.size);
			last = last.prev;
			this.size --;
		}
		//加一个判断  不能直接删掉第一个元素
		else if(index == 0){
			//first是开始节点是标记 然后只要将first向后移一位
			first = first.next;
			this.size --;
		}else {
			Entry entry = first;
			for(int i=0; i<index; i++) {
				//System.out.println(i);
				entry = entry.next;
			}
			//这是重点 删除中间节点
			entry.next.prev = entry.prev;
			entry.prev.next = entry.next;
			this.size --;
		}
	}
	
	public void print() {
		Entry entry = first;
		while(entry!=last.next) {
			System.out.println(entry.data);
			entry = entry.next;
		}
	}
	
	public boolean isData(int index) {
		return index < this.size && index >= 0;
	} 
	
	public static void main(String[] args) {
		MyLinkedList list = new MyLinkedList();
		list.add("123");
		list.add("456");
		list.add("789");
		list.add("000");
		list.add(1,"123");
		list.remove(3);
		System.out.println(list.get(2));
		list.print();
	}
}
