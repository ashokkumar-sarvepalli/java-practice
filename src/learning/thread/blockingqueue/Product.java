package learning.thread.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Product {

	private BlockingQueue<Item> itemList;
	private int capacity;
	
	public Product(int capacity) {
		itemList = new ArrayBlockingQueue<Item>(capacity);
	}

	public BlockingQueue<Item> getItemList() {
		return itemList;
	}

	public void setItemList(BlockingQueue<Item> itemList) {
		this.itemList = itemList;
	}

	
	public static class Item {
		int id;
		String name;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		public String toString() {
			return "Item : " +this.id +":" + this.name;
		}

	}

}
