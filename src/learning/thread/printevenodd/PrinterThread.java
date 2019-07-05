package learning.thread.printevenodd;

public class PrinterThread implements Runnable {

	private Range range;

	private boolean isOdd;

	private int maxIterations;

	public PrinterThread(Range range, boolean isOdd) {
		this.range = range;
		this.isOdd = isOdd;

		maxIterations = range.getMaxValue() / 2;
	}

	public void run() {

		if (isOdd) {
			printOddNumbers();
			return;
		}

		printEvenNumbers();
	}

	private void printEvenNumbers() {
		for (int i = 1; i <= maxIterations; i++) {

			synchronized (range) {

				while (range.getCurrentValue() % 2 == 1) {
					try {
						range.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				System.out.println("Printing even number from even Thread "+range.getCurrentValue());
				range.setCurrentValue(range.getCurrentValue()+1);
				
				// sleep just for output purpose -- to show delay
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				range.notifyAll();
			}
		}
	}

	private void printOddNumbers() {

		for (int i = 1; i <= maxIterations; i++) {

			synchronized (range) {

				while (range.getCurrentValue() % 2 == 0) {
					try {
						range.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				System.out.println("Printing odd number from odd Thread "+range.getCurrentValue());
				range.setCurrentValue(range.getCurrentValue()+1);
				// sleep just for output purpose -- to show delay
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				range.notifyAll();
			}
		}
	}

}
