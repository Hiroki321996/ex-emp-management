package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * Admionistratorのコントローラー
 * 
 * @author sanihiro
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private AdministratorService service;
	
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}
	
	/**
	 * 管理者情報登録画面へ遷移する.
	 * 
	 * @return 管理者情報登録画面
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert.html";
	}
	
	/**
	 * ログイン画面へ繊維する
	 * 
	 * @return　ログイン画面
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login.html";
	}
	
	/**
	 * ユーザー登録画面.
	 * 
	 * @param form Administratorのフォーム
	 * @return ログイン画面（リダイレクト）
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		
		administrator.setName(form.getName());
		administrator.setMailAddress(form.getMailAddress());
		administrator.setPassword(form.getPassword());
		
		service.insert(administrator);
		
		return "redirect:/";
	}
	
	/**
	 * ログイン処理を行い、メールアドレスとパスワードがあっていればその名前を返し従業員のリストへ飛ばす.
	 * 
	 * @param form ログインのリクエストパラメータ
	 * @param model　リクエストスコープ
	 * @return　従業員のリスト画面
	 */
	@RequestMapping("/login")
	public String login(LoginForm form,Model model) {
		Administrator administrator = service.login(form.getMailAddress(), form.getPassword());
		
		if(administrator == null) {
			model.addAttribute("message", "メールアドレス又はパスワードが不正です");
			return toLogin();
		}else {
			session.setAttribute("administratorName", administrator.getName());
			return "forward:/employee/showList";
		}
		
		
		
	}
}
