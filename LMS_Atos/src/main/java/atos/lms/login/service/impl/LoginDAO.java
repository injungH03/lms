package atos.lms.login.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository("atosLoginDAO")
public class LoginDAO extends EgovComAbstractDAO {
	/**
	 * 사용자의 로그인 요청을 처리합니다.
	 * 
	 * @param vo 사용자의 로그인 정보가 담긴 LoginVO 객체
	 * @return 로그인 성공 시 사용자의 정보를 담은 LoginVO 객체, 실패 시 null
	 * @throws Exception 로그인 처리 중 데이터베이스 오류 발생 시
	 */
	public LoginVO actionLogin(LoginVO vo) throws Exception {
		return (LoginVO) selectOne("LoginUser.actionLogin", vo);
	}

	/**
	 * 사용자의 아이디를 찾습니다.
	 * 
	 * @param vo 사용자의 이름과 이메일 정보가 담긴 LoginVO 객체
	 * @return 사용자의 아이디가 포함된 LoginVO 객체, 실패 시 null
	 * @throws Exception 아이디 조회 중 데이터베이스 오류 발생 시
	 */
	public LoginVO searchId(LoginVO vo) throws Exception {
		return (LoginVO) selectOne("LoginUser.searchId", vo);
	}

	/**
	 * 사용자의 비밀번호를 찾습니다.
	 * 
	 * @param vo 사용자의 아이디, 이름, 이메일 정보가 담긴 LoginVO 객체
	 * @return 사용자의 비밀번호가 포함된 LoginVO 객체, 실패 시 null
	 * @throws Exception 비밀번호 조회 중 데이터베이스 오류 발생 시
	 */
	public LoginVO searchPassword(LoginVO vo) throws Exception {
		return (LoginVO) selectOne("LoginUser.searchPassword", vo);
	}

	/**
	 * 사용자의 비밀번호를 업데이트합니다.
	 * 
	 * @param vo 변경된 비밀번호가 담긴 LoginVO 객체
	 * @throws Exception 비밀번호 업데이트 중 데이터베이스 오류 발생 시
	 */
	public void updatePassword(LoginVO vo) throws Exception {
		update("LoginUser.updatePassword", vo);
	}
}