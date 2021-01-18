package com.nationalbank.dao;

import com.nationalbank.entity.Employee;

public interface EmployeeDAO {

	Employee getEmployee(String empName, String password);
}
