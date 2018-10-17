package learning.thread.waitmethod;

import java.util.Random;

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
		for(int i=1;i<=limit;i++) {
			synchronized (product) {
				while (product.isAvailable()) {
					try {
						product.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				int rand = random.nextInt(100);
				product.setId(rand);
				product.setName("id " + rand);
				product.setAvailable(true);
				System.out.println("Producer produced "+product.getId());
				product.notify();

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

}
