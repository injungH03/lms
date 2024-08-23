package atos.lms.test;

//데이터베이스 연결 항목(Url, UserName, Password) 인코딩 값 생성 JAVA
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.egovframe.rte.fdl.cryptography.EgovEnvCryptoService;
import org.egovframe.rte.fdl.cryptography.impl.EgovEnvCryptoServiceImpl;

public class EgovEnvCryptoUserTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovEnvCryptoUserTest.class);

	public static void main(String[] args) {
		System.out.println("db");
		String[] arrCryptoString = { "MASTER", // 데이터베이스 접속 계정 설정 - 이부분 정해야 됨.
				"1234", // 데이터베이스 접속 패드워드 설정 - 이부분 정해야 됨.
				"url", // 데이터베이스 접속 주소 설정 - 이부분 정해야 됨.
				"databaseDriver" // 데이터베이스 드라이버 - 이부분 정해야 됨.
		};

		LOGGER.info("------------------------------------------------------");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:/context-crypto-test.xml" });
		EgovEnvCryptoService cryptoService = context.getBean(EgovEnvCryptoServiceImpl.class);
		LOGGER.info("------------------------------------------------------");

		String label = "";
		try {
			for (int i = 0; i < arrCryptoString.length; i++) {
				if (i == 0)
					label = "사용자 아이디";
				if (i == 1)
					label = "사용자 비밀번호";
				if (i == 2)
					label = "접속 주소";
				if (i == 3)
					label = "데이터 베이스 드라이버";
				LOGGER.info(label + " 원본(orignal):" + arrCryptoString[i]);
				LOGGER.info(label + " 인코딩(encrypted):" + cryptoService.encrypt(arrCryptoString[i]));
				LOGGER.info("------------------------------------------------------");
			}
		} catch (IllegalArgumentException e) {
			LOGGER.error("[" + e.getClass() + "] IllegalArgumentException : " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("[" + e.getClass() + "] Exception : " + e.getMessage());
		}

	}
}
