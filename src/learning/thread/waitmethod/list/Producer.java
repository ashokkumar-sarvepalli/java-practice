package learning.thread.waitmethod.list;

import java.util.Random;

import learning.thread.waitmethod.list.Product.Item;

public class Producer implements Runnable {

	private Product product;
	private int limit;
	private Random random = new Random();

	public Producer(Product product, int limit) {
		this.product = product;
		this.limit = limit;
	}

	@Override
	public void run() {
		while(true) {
			synchronized (product) {
				while (product.getItemList().size()==limit) {
					try {
						product.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				int rand = random.nextInt(100);
				Item item = new Item();
				item.setId(rand);
				item.setName("id " + rand);
				product.getItemList().add(item);
				System.out.println("Producer produced : "+product.getItemList().get(product.getItemList().size()-1));
				product.notify();

			}
			

			// optional to give sleep just to make sure in the console it has some delay
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
