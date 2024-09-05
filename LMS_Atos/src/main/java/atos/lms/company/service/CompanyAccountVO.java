package atos.lms.company.service;

import atos.lms.exam.service.GeneralModel;

@SuppressWarnings("serial")
public class CompanyAccountVO implements GeneralModel {

	private String id;
	private String password;
	private String name;
	private String status;

	@Override
	public String toString() {
		return "CompanyAccountVO [id=" + id + ", password=" + password + ", name=" + name + ", status=" + status + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
