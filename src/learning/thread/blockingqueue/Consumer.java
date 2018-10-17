package learning.thread.blockingqueue;

public class Consumer implements Runnable{
	
	private Product product;
	
	public Consumer(Product product) {
		this.product = product;
	}

	@Override
	public void run() {
		while(true) {
				try {
					System.out.println("Consumer consumed item : "+product.getItemList().take());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
