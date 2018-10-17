package learning.thread.waitmethod;

public class ProductTest {

	public static void main(String[] args) {
	
		Product product = new Product();
		int noOfProduce = 5;
		Producer producer = new Producer(product,noOfProduce);
		Consumer consumer = new Consumer(product,noOfProduce);
		
		Thread prodThread = new Thread(producer);
		Thread conThread = new Thread(consumer);
		
		
		prodThread.start();
		conThread.start();

	}

}
