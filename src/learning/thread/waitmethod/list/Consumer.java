package learning.thread.waitmethod.list;

public class Consumer implements Runnable{
	
	private Product product;
	private int limit;
	
	public Consumer(Product product, int limit) {
		this.product = product;
		this.limit = limit;
	}

	@Override
	public void run() {
		while(true) {
			synchronized (product) {
				while (product.getItemList().isEmpty()) {
					try {
						product.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				System.out.println("Consumer consumed item : "+product.getItemList().get(0));
				product.getItemList().remove(0);
				product.notify();
				

			}
			

			// optional to give sleep just to make sure in the console it has some delay
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

		}

		
	}
	
	

}
