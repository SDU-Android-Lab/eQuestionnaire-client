package sdu.equestionnaire.common;

public class Administor extends Person {
	public String adepartment;// 管理员部门
	public String apost;// 管理员职位
	public Double salary;

	public String getAdepartment() {
		return adepartment;
	}

	public void setAdepartment(String adepartment) {
		this.adepartment = adepartment;
	}

	public String getApost() {
		return apost;
	}

	public void setApost(String apost) {
		this.apost = apost;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

}
