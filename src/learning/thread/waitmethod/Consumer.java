package learning.thread.waitmethod;

public class Consumer implements Runnable{
	
	private Product product;
	private int limit;
	
	public Consumer(Product product, int limit) {
		this.product = product;
		this.limit = limit;
	}

	@Override
	public void run() {
		for(int i=1;i<=limit;i++) {
			synchronized (product) {
				while (!product.isAvailable()) {
					try {
						product.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				System.out.println("Consumer consumed id : "+product.getId());
				product.setAvailable(false);
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
