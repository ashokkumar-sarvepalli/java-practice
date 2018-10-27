package learning.thread.executor.giga;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorFuture {

	private static final int TOTAL = 1024 * 1024 * 1024;

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		// start time before thread starts.
		long startTime = System.currentTimeMillis();
		int numberOfProcessors = Runtime.getRuntime().availableProcessors();
		int systemProcessors = numberOfProcessors;
		System.out.println(numberOfProcessors);
		
		int size = TOTAL / numberOfProcessors;
		
		// if size exceeds 1 Million then increase that numberOfPrcoessors as single thread difficult to hold large size
		
		if(size> (1024*1024)) {
			size = 1024*1024;
			numberOfProcessors = TOTAL/size;
			
			System.out.println("Total threads to be executed" +numberOfProcessors);
		}
		
		
		
		int balance = TOTAL % numberOfProcessors == 0 ? 0 : TOTAL % numberOfProcessors;
		System.out.println("Thread Pool size "+systemProcessors);
		ExecutorService executorService = Executors.newFixedThreadPool(systemProcessors);

		List<Callable<Employee>> employeeInputList = new ArrayList<>();

		int start = 1;
		for (int i = 1; i <= numberOfProcessors; i++) {
			Callable<Employee> callable = new EmployeeCallable(start - 1, (i * size) - 1);
			employeeInputList.add(callable);
			start = start + size;
		}

		if (balance != 0) {
			Callable<Employee> callable = new EmployeeCallable((numberOfProcessors * size),
					((numberOfProcessors * size)) + balance);
			employeeInputList.add(callable);
		}

		List<Future<Employee>> result = executorService.invokeAll(employeeInputList);

		List<Employee> employeeResultList = new ArrayList<Employee>();
		for (Future<Employee> empFuture : result) {
			if (empFuture.get() != null) {
				employeeResultList.add(empFuture.get());
			}
		}

		System.out.println("withStreamMultiThreadedExecutors"
				+ employeeResultList.stream().max(Comparator.comparing(Employee::getSalary)).get());
		long endTime = System.currentTimeMillis();
		System.out.println("withStreamMultiThreadedExecutors" + (endTime - startTime));

	}

}
