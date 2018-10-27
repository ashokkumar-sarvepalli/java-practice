package learning.thread.executor.giga;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;

public class EmployeeCallable implements Callable<Employee> {

	private int startIndex;
	private int endIndex;
	private static int numOfThreads;
	private static BufferedReader br;

	static {
		try {
			br = new BufferedReader(new FileReader("/Users/ashok/Documents/employee-giga.csv"));
			numOfThreads = 1024;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public EmployeeCallable(int startIndex, int endIndex) {
		this.startIndex = startIndex;
		this.endIndex = endIndex;

	}

	@Override
	public Employee call() throws Exception {
		long startTime = System.currentTimeMillis();
		System.out.println("Entered in the call method " + Thread.currentThread().getName()+" start index :"+startIndex + " end Index "+endIndex);
		List<Employee> employeeList = readCSV();
		Employee empMax = employeeList.stream()
				.max(Comparator.comparing(Employee::getSalary)).get();
		System.out.println("In this thread the max emnployee is " + empMax);
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken for thread "+Thread.currentThread().getName()+" is :"+(endTime-startTime));
		System.out.println("Remaining Threads");
		synchronized(EmployeeCallable.class) {
			numOfThreads = numOfThreads-1;
			System.out.println("Total number of threads remaining is "+numOfThreads);
		}
		return empMax;
	}

	private List<Employee> readCSV() {
		List<Employee> employeeList = new ArrayList<Employee>();
		
		for (int i = startIndex; i < endIndex; i++) {
			String s;
			try {
				s = br.readLine();
				String[] array = s.split(",");
				Employee emp = new Employee(Integer.parseInt(array[0]), array[1], Double.parseDouble(array[2]));
				employeeList.add(emp);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return employeeList;

	}
}
