package learning.thread.blockingqueue;

public class ProductTest {

	public static void main(String[] args) {
	
		Product product = new Product(10);
		Producer producer = new Producer(product);
		Consumer consumer = new Consumer(product);
		
		Thread prodThread = new Thread(producer);
		Thread conThread = new Thread(consumer);
		
		
		prodThread.start();
		conThread.start();

	}

}
