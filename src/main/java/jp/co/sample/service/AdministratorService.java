package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

/**
 * 管理者情報を操作するサービス.
 * 
 * @author sanihiro
 *
 */
@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository repository;
	
	/**
	 * 管理者情報を登録する.
	 * 
	 * @param administrator 管理者情報
	 */
	public void insert(Administrator administrator) {
		repository.insert(administrator);
	}
	
	/**
	 * メールアドレスとパスワードでデータベース内を検索します.
	 * 
	 * @param mailAddress　メールアドレス
	 * @param password　パスワード
	 * @return 検索された管理者情報
	 */
	public Administrator login(String mailAddress,String password) {
		return repository.findByMailAddressAndPassword(mailAddress, password);
	}
}
