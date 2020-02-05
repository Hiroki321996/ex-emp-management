package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * administratorのリポジトリ
 * 
 * @author sanihiro
 *
 */
@Repository
public class AdministratorRepository {

	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs,i) -> {
		Administrator administrator = new Administrator();
		
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail-address"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * insert文
	 * 
	 * @param administrator
	 */
	public void insert(Administrator administrator) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		String sql = "INSERT INTO administrators (name,mail_addres,password) VALUES(:name,:mail_address,:password) ;";
		
		template.update(sql, param);
	}
	
	/**
	 * メールアドレスとパスワードを一致した一行を返します
	 * 
	 * @param mailAddress
	 * @param password
	 * @return　administratorのドメイン
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress,String password) {
		String sql = "SELECT id,name,mail_address,password FROM administrators WHERE mail_address = :mailAddress AND password = :password ;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password", password);
		
		Administrator administrator =  template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
		
		if(administrator == null) {
			return null;
		}
		
		return administrator;
	}
}
