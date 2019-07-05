package learning.thread.printevenodd;

public class Range {

	private int currentValue;

	private int maxValue;
	
	public Range(int currentValue, int maxValue) {
		this.currentValue = currentValue;
		this.maxValue = maxValue;
	}

	public int getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(int currentValue) {
		this.currentValue = currentValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

}
