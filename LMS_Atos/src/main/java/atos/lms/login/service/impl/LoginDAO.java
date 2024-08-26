package atos.lms.login.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository("loginDAO_")
public class LoginDAO extends EgovComAbstractDAO {

	/**
	 * 일반 로그인을 처리한다
	 * 
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
	public LoginVO actionLogin(LoginVO vo) throws Exception {
		return (LoginVO) selectOne("LoginUsr_.actionLogin", vo);
	}

	/**
	 * 아이디를 찾는다.
	 * 
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
	public LoginVO searchId(LoginVO vo) throws Exception {

		return (LoginVO) selectOne("LoginUsr_.searchId", vo);
	}

	/**
	 * 비밀번호를 찾는다.
	 * 
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
	public LoginVO searchPassword(LoginVO vo) throws Exception {

		return (LoginVO) selectOne("LoginUsr_.searchPassword", vo);
	}

	/**
	 * 변경된 비밀번호를 저장한다.
	 * 
	 * @param vo LoginVO
	 * @exception Exception
	 */
	public void updatePassword(LoginVO vo) throws Exception {
		update("LoginUsr_.updatePassword", vo);
	}

}
