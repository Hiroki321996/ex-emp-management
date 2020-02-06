package jp.co.sample.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/**
 * employeeテーブルを操作するためのリポジトリです.
 * 
 * @author sanihiro
 *
 */
@Repository
public class EmployeeRepository {
	
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs,i) -> {
		Employee employee = new Employee();
		
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMeilAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		
		return employee;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 *全件検索.
	 * 
	 * @return employeeドメインのリスト
	 */
	public List<Employee> findAll(){
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count FROM employees ORDER BY hire_date DESC ;";
		
		return template.query(sql, EMPLOYEE_ROW_MAPPER);
	}
	
	
	/**
	 *主キー検索.
	 * 
	 * @param id
	 * @return employeeドメイン
	 */
	public Employee load(Integer id) {
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count FROM employees WHERE id = :id ;";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		
		return template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
	}
	
	/**
	 * update文.
	 * 
	 * @param employee
	 */
	public void update(Employee employee) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		String sql = "UPDATE employees SET dependents_count = :dependentsCount WHERE id = :id ;";
		
		template.update(sql, param);
	}
	
	/**
	 * オフセットで指定された10人の従業員情報を抽出する.
	 * 
	 * @param offset ページ番号
	 * @return	10人区切りの従業員情報
	 */
	public List<Employee> findEach10RowsOfAll(Integer page){
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count FROM employees ORDER BY hire_date DESC LIMIT 10 OFFSET :page ;";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("page", (page - 1) * 10);
		
		return template.query(sql, param,EMPLOYEE_ROW_MAPPER);
	}
	
	/**
	 * DB上で従業員情報を全件検索してその数を10で割りページ数を出して、その分だけ要素数をいれたリストを返す.
	 * 
	 * @return 従業員リストのページ数
	 */
	public List<Integer> countPage() {
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count FROM employees ORDER BY hire_date DESC ;";
		
		List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
		int count = employeeList.size() / 10 + 1;
		
		List<Integer> countPageList = new ArrayList<Integer>();
		for (int i = 1; i <= count; i++) {
			countPageList.add(i);
		}
		return countPageList;
	}
	
}
