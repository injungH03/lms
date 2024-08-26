package atos.lms.login.service;

import egovframework.com.cmm.LoginVO;

public interface LoginService {
	/**
	 * 일반 로그인을 처리한다
	 * 
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
	LoginVO actionLogin(LoginVO vo) throws Exception;

	/**
	 * 아이디를 찾는다.
	 * 
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
	LoginVO searchId(LoginVO vo) throws Exception;

	/**
	 * 비밀번호를 찾는다.
	 * 
	 * @param vo LoginVO
	 * @return boolean
	 * @exception Exception
	 */
	boolean searchPassword(LoginVO vo) throws Exception;
}
