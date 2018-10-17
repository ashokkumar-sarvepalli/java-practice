package learning.thread.waitmethod.list;

import java.util.ArrayList;
import java.util.List;

public class Product {

	private List<Item> itemList = new ArrayList<Item>();
	private int capacity;

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
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
