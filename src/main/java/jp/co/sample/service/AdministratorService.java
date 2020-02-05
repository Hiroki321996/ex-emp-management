package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

/**
 * Administratorのサービスクラスです
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
	 * repositoryのinsertメソッドを呼び出してます
	 * 
	 * @param administrator
	 */
	public void insert(Administrator administrator) {
		repository.insert(administrator);
	}
}
