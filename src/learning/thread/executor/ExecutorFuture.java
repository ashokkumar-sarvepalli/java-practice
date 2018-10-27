package learning.thread.executor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public class ExecutorFuture {

	private static final int TOTAL = 1024 * 1024;

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		System.out.println("generating csv");
		generateCSV();

		System.out.println("reading csv");
		List<Employee> employeeList = readCSV();
		
		System.out.println("size "+employeeList.size());

		withStreamComparatorSingleThreaded(employeeList);

		withIterationNormalApproachSingleThreaded(employeeList);

		withStreamMultiThreadedExecutors(employeeList);

	}

	private static void withStreamMultiThreadedExecutors(List<Employee> employeeList)
			throws InterruptedException, ExecutionException {
		// start time before thread starts.
		long startTime = System.currentTimeMillis();

		int numberOfProcessors = Runtime.getRuntime().availableProcessors();

		System.out.println(numberOfProcessors);
		ExecutorService executorService = Executors.newFixedThreadPool(numberOfProcessors);

		int balance = TOTAL % numberOfProcessors == 0 ? 0 : TOTAL % numberOfProcessors;

		int size = TOTAL / numberOfProcessors;

		List<Callable<Employee>> employeeInputList = new ArrayList<>();

		int start = 1;
		for (int i = 1; i <= numberOfProcessors; i++) {
			Callable<Employee> callable = new EmployeeCallable(start - 1, (i * size) - 1, employeeList);
			employeeInputList.add(callable);
			start = start + size;
		}

		if (balance != 0) {
			Callable<Employee> callable = new EmployeeCallable((numberOfProcessors * size),
					((numberOfProcessors * size)) + balance, employeeList);
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

	private static void withIterationNormalApproachSingleThreaded(List<Employee> employeeList) {
		long startTime = System.currentTimeMillis();

		Employee empMax = employeeList.get(0);
		double empMaxSal = employeeList.get(0).getSalary();

		for (int i = 1; i < employeeList.size(); i++) {
			if (employeeList.get(i).getSalary() > empMaxSal) {
				empMax = employeeList.get(i);
				empMaxSal = employeeList.get(i).getSalary();
			}
		}
		System.out.println("withIterationNormalApproachSingleThreaded" + empMax);
		long endTime = System.currentTimeMillis();
		System.out.println("withIterationNormalApproachSingleThreaded " + (endTime - startTime));

	}

	private static void withStreamComparatorSingleThreaded(List<Employee> employeeList) {

		long startTime = System.currentTimeMillis();
		System.out.println("withStreamComparatorSingleThreaded"
				+ employeeList.stream().max(Comparator.comparing(Employee::getSalary)).get());
		long endTime = System.currentTimeMillis();
		System.out.println("withStreamComparatorSingleThreaded " + (endTime - startTime));

	}

	private static List<Employee> readCSV() {
		List<Employee> employeeList = new ArrayList<Employee>();
		String fileName = "/Users/ashok/Documents/employee.csv";
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			stream.forEach(s -> {

				String[] array = s.split(",");
				Employee emp = new Employee(Integer.parseInt(array[0]), array[1], Double.parseDouble(array[2]));
				employeeList.add(emp);

			});

		} catch (IOException e) {
			e.printStackTrace();
		}

		return employeeList;

	}

	private static void generateCSV() {

		String fileName = "/Users/ashok/Documents/employee.csv";
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(fileName);
			bw = new BufferedWriter(fw);
		} catch (Exception ex) {
		}

		for (int i = 1; i <= TOTAL; i++) {

			int empId = (int) (1000 + Math.random() * TOTAL);
			String empName = "test" + empId;
			int salary = (int) (5000 + Math.random() * TOTAL);

			StringBuilder sb = new StringBuilder(String.valueOf(empId));
			sb.append(",");
			sb.append(empName);
			sb.append(",");
			sb.append(String.valueOf(salary));
			try {

				bw.write(sb.toString());
				bw.newLine();

			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

		try {
			bw.close();
			fw.close();
		} catch (Exception ex) {
		}

	}

}
