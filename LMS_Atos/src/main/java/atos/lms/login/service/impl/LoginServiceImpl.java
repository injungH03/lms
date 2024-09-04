package atos.lms.login.service.impl;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import atos.lms.login.service.LoginService;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cop.ems.service.EgovSndngMailRegistService;
import egovframework.com.cop.ems.service.SndngMailVO;
import egovframework.com.utl.fcc.service.EgovNumberUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sim.service.EgovFileScrty;

@Service("atosLoginService")
public class LoginServiceImpl extends EgovAbstractServiceImpl implements LoginService {
	/** LoginDAO: 로그인 관련 DB 처리 객체 */
	@Resource(name = "atosLoginDAO")
	private LoginDAO loginDAO;

	/** EgovSndngMailRegistService: 이메일 발송을 처리하는 서비스 */
	@Resource(name = "sndngMailRegistService")
	private EgovSndngMailRegistService sndngMailRegistService;

	/**
	 * 사용자의 로그인 요청을 처리하고 결과를 반환합니다.
	 * 
	 * @param vo 로그인 요청 정보가 담긴 LoginVO 객체
	 * @return 로그인 성공 시 LoginVO 객체, 실패 시 null
	 * @exception Exception
	 */
	@Override
	public LoginVO actionLogin(LoginVO vo) throws Exception {

		// 입력한 비밀번호를 암호화
		String enpassword = EgovFileScrty.encryptPassword(vo.getPassword(), vo.getId());
		vo.setPassword(enpassword);

		// DB에서 로그인 정보 확인
		LoginVO loginVO = loginDAO.actionLogin(vo);

		// 로그인 성공 여부 반환
		if (loginVO != null && !loginVO.getId().isEmpty() && !loginVO.getPassword().isEmpty()) {
			return loginVO;
		}
		return null; // 로그인 실패 시 null 반환
	}

	/**
	 * 사용자의 아이디 찾기 요청을 처리합니다.
	 * 
	 * @param vo 이름, 이메일 정보가 담긴 LoginVO 객체
	 * @return 아이디가 포함된 LoginVO 객체, 실패 시 null
	 * @exception Exception
	 */
	@Override
	public LoginVO searchId(LoginVO vo) throws Exception {
		// DB에서 아이디 조회
		LoginVO loginVO = loginDAO.searchId(vo);

		// 결과 반환
		if (loginVO != null && !loginVO.getId().isEmpty()) {
			return loginVO;
		}
		return null; // 아이디 조회 실패 시 null 반환
	}

	/**
	 * 비밀번호 찾기 요청을 처리합니다.
	 * 
	 * @param vo 사용자의 아이디, 이름, 이메일 정보가 담긴 LoginVO 객체
	 * @return 임시 비밀번호 생성 후 이메일 발송 성공 여부
	 * @exception Exception
	 */
	@Override
	public boolean searchPassword(LoginVO vo) throws Exception {
		// DB에서 비밀번호 힌트 정보로 사용자를 조회
		LoginVO loginVO = loginDAO.searchPassword(vo);
		if (loginVO == null || loginVO.getPassword() == null || loginVO.getPassword().isEmpty()) {
			return false; // 사용자 정보가 없거나 비밀번호가 없으면 실패 반환
		}

		// 임시 비밀번호 생성 (영자+숫자 조합으로 8자리)
		StringBuilder newPassword = new StringBuilder();
		for (int i = 1; i <= 8; i++) {
			// 영자
			if (i % 3 != 0) {
				newPassword.append(EgovStringUtil.getRandomStr('a', 'z'));
			} else {
				// 숫자
				newPassword.append(EgovNumberUtil.getRandomNum(0, 9));
			}
		}

		// 생성된 임시 비밀번호를 암호화하여 DB에 저장
		String encryptedPassword = EgovFileScrty.encryptPassword(newPassword.toString(), vo.getId());
		LoginVO pwVO = new LoginVO();
		pwVO.setId(vo.getId());
		pwVO.setPassword(encryptedPassword);
		pwVO.setUserSe(vo.getUserSe());
		loginDAO.updatePassword(pwVO);

		// 임시 비밀번호를 이메일로 발송
		SndngMailVO sndngMailVO = new SndngMailVO();
		sndngMailVO.setDsptchPerson("webmaster");
		sndngMailVO.setRecptnPerson(vo.getEmail());
		sndngMailVO.setSj("[ATOS] 임시 비밀번호를 발송했습니다.");
		sndngMailVO.setEmailCn("고객님의 임시 비밀번호는 " + newPassword.toString() + " 입니다.");
		sndngMailVO.setAtchFileId("");

		// 이메일 발송 결과 반환
		return sndngMailRegistService.insertSndngMail(sndngMailVO);
	}
}