package sdu.equestionnaire.common;

public class employee extends Person{
	private String work;
	private Double salary;
	private  Integer administor_aid;
	public Integer getAdministor_aid() {
		return administor_aid;
	}
	public void setAdministor_aid(Integer administor_aid) {
		this.administor_aid = administor_aid;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
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
