package sdu.equestionnaire.common;

public class employee extends Person{
	private String work;
	private String salary;
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public employee(){
		super();
	}

}
