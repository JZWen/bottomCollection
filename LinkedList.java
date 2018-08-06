package connection;

/**
 * linkedList�ײ�ʵ��
 * ��������洢 ˫������
 * ��Ҫ�� ����first�ڵ� ��last�ڵ�
 * first�ڵ�  ��ѯ��ʼ�㣬last�ڵ����ɾ����ʼ�㣨�����Ͳ�Ҫȥ�������������ˣ�����ֻ������������ĩ�˲���ɾ���������
 * first��lastҲ�������һ����Ч��
 * 
 * @author ������
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
			//����ǵ�һ���Ļ� ��һ�²���
			Entry entry = new Entry();
			entry.data = data;
			first = entry;
			last = entry;
			this.size++;
		}
		else {
			//���ǵ�һ������Ļ���ֱ�Ӳ���ͺ���
			Entry entry = new Entry();
			entry.data = data;
			entry.prev = last;
			last.next = entry;
			last = entry;
			this.size++;
		}
	}
	//ָ��λ�� ���Ԫ��
	public void add(int index , Object data) {
		Entry entry = new Entry();
		entry.data = data;
		if(!isData(index)) {
			entry = null;
			return;
		}
		/*
		 * ��Ȼ���ﻹ��Ӧ���ж�һ���ǲ�����βλ��
		 */
		else if(index == 0){
			entry.next = first;
			first.prev = entry;
			first = entry;
			this.size++;
		}
		else if(index == this.size-1) {
			add(data); //ֱ�ӵ��÷������
		}
		else {
			Entry oldEntry = new Entry();
			oldEntry = first;
			for(int i=0; i<=index-1 ; i++) {
				oldEntry = oldEntry.next;
			}
			//�����������
			oldEntry.prev.next = entry;
			entry.prev = oldEntry.prev;
			//�ұ���������
			entry.next = oldEntry;
			oldEntry.prev = entry;
			this.size++;
		}
	}
	//ͨ���±�ȥ�õ���Ӧ��ֵ
	public Object get(int index) {
		//���Ϸ��±귵��null
		if(!isData(index)) {
			return null;
		}
		else {
			Entry entry = new Entry();
			entry = first;
			//ѭ���ҵ�λ��
			for(int i=0; i<index-1; i++) {
				entry = entry.next;
			}
			return entry.data;
		}
	}
	
	//ɾ�����һ��Ԫ��
	public void remove() {
		//���û��Ԫ��
		if(this.size == 0) {
			return ;
		}
		//���ֻ��һ��Ԫ��
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
	
	//ָ��λ��ɾ��Ԫ��
	public void remove(int index) {
		if (!isData(index)) {
			return;
		}
		//��һ���ж� �ǲ������һ��index ��Ϊ�ⲻ��ֱ��ɾ ���ǲ���û��last�ڵ�
		else if(index == this.size-1){
			//last�������һ��Ԫ�صı�� ֻҪ��last�ڵ���ǰ��һ���Ϳ���
			System.out.println(this.size);
			last = last.prev;
			this.size --;
		}
		//��һ���ж�  ����ֱ��ɾ����һ��Ԫ��
		else if(index == 0){
			//first�ǿ�ʼ�ڵ��Ǳ�� Ȼ��ֻҪ��first�����һλ
			first = first.next;
			this.size --;
		}else {
			Entry entry = first;
			for(int i=0; i<index; i++) {
				//System.out.println(i);
				entry = entry.next;
			}
			//�����ص� ɾ���м�ڵ�
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
