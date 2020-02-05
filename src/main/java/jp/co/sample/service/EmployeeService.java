package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

/**
 * 従業員情報を登録する.
 * 
 * @author sanihiro
 *
 */
@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	/**
	 * 従業員情報を全件出す.
	 * 
	 * @return　全員分の従業員情報
	 */
	public List<Employee> showList(){
		return repository.findAll();
	}
}
