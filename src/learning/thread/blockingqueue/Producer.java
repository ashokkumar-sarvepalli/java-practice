package learning.thread.blockingqueue;

import java.util.Random;

public class Producer implements Runnable {

	private Product product;
	private Random random = new Random();

	public Producer(Product product) {
		this.product = product;
	}

	@Override
	public void run() {
		while(true) {

				int rand = random.nextInt(100);
				learning.thread.blockingqueue.Product.Item item = new learning.thread.blockingqueue.Product.Item();
				item.setId(rand);
				item.setName("id " + rand);
				System.out.println("Producer produced : "+item);
				try {
					product.getItemList().put(item);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
