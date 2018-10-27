package learning.thread.executor;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;

public class EmployeeCallable implements Callable<Employee>{
	
  private int startIndex;
  private int endIndex;
  private List<Employee> employeeList;
  
  public EmployeeCallable(int startIndex, int endIndex,List<Employee> employeeList) {
	  this.startIndex = startIndex;
	  this.endIndex = endIndex;
	  this.employeeList = employeeList;
  }

	@Override
	public Employee call() throws Exception {
		Employee empMin = employeeList.subList(startIndex, endIndex).stream().max(Comparator.comparing(Employee::getSalary)).get();
		return empMin;
	}

}
