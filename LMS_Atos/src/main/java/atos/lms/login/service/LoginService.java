package atos.lms.login.service;

import egovframework.com.cmm.LoginVO;

public interface LoginService {
	/**
	 * 사용자의 로그인 정보를 처리하고, 로그인 결과를 반환합니다.
	 * 
	 * @param vo 사용자의 로그인 정보가 담긴 LoginVO 객체 (아이디, 비밀번호 등)
	 * @return 로그인 성공 시 로그인된 사용자의 정보를 담은 LoginVO 객체, 실패 시 null
	 * @throws Exception 로그인 처리 중 예외가 발생한 경우
	 */
	LoginVO actionLogin(LoginVO vo) throws Exception;

	/**
	 * 사용자의 아이디를 찾는 로직을 처리하고, 결과를 반환합니다.
	 * 
	 * @param vo 사용자의 이름, 이메일 등의 정보를 담고 있는 LoginVO 객체
	 * @return 사용자의 아이디 정보를 담은 LoginVO 객체, 찾지 못한 경우 null
	 * @throws Exception 아이디 찾기 중 예외가 발생한 경우
	 */
	LoginVO searchId(LoginVO vo) throws Exception;

	/**
	 * 사용자의 비밀번호 찾기를 처리합니다. 비밀번호를 찾으면 임시 비밀번호를 발급할 수 있습니다.
	 * 
	 * @param vo 사용자의 아이디, 비밀번호 힌트 등의 정보를 담고 있는 LoginVO 객체
	 * @return 비밀번호 찾기 성공 시 true, 실패 시 false
	 * @throws Exception 비밀번호 찾기 중 예외가 발생한 경우
	 */
	boolean searchPassword(LoginVO vo) throws Exception;
}