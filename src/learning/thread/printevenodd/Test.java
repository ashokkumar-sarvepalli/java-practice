package learning.thread.printevenodd;

public class Test {

	public static void main(String[] args) {
		
		Range range = new Range(1,100);
		
		PrinterThread printerOdd = new PrinterThread(range, true);
		PrinterThread printerEven = new PrinterThread(range,false);
		
		
		Thread t1 = new Thread(printerOdd);
		Thread t2 = new Thread(printerEven);
		
		t1.start();
		t2.start();

	}

}
