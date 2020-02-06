package jp.co.sample.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員情報の処理制御を行うコントローラ.
 * 
 * @author sanihiro
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	@ModelAttribute
	public UpdateEmployeeForm setUpUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}
	
	@Autowired
	private HttpSession session;
	
	/**
	 * 従業員全員の情報をリクエストスコープに入れて、従業員リスト画面に飛ばす.
	 * 加えてページ数を下に表示(aタグで飛ぶ)
	 * 
	 * @param model リクエストスコープ
	 * @return　従業員リスト画面
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = service.showList();
		model.addAttribute("employeeList", employeeList);
		
		List<Integer> pageCount = service.countPage();
		session.setAttribute("pageCount",pageCount);
		
		return "employee/list.html";
	}
	
	@RequestMapping("/showDetail")
	public String showDetail(String id,Model model) {
		int idInt = Integer.parseInt(id);
		
		Employee employee = service.showDetail(idInt);
		model.addAttribute("employee", employee);
		
		return "employee/detail.html";
	}
	
	@RequestMapping("/update")
	public String update(UpdateEmployeeForm form) {
		Employee employee = new Employee();
		employee.setId(form.getIntId());
		employee.setDependentsCount(form.getIntDependentsCount());
		service.update(employee);
		
		return "redirect:/employee/showList";
	}
	
	@RequestMapping("/showListPage")
	public String showListPage(int page,Model model) {
		model.addAttribute("employees10",service.showEmployee10(page));
		session.setAttribute("pageNow", page);
		
		return "employee/showList10";
		
	}
}
