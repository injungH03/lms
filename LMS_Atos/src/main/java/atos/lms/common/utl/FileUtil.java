package atos.lms.common.utl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;

@Component
public class FileUtil {
	
    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;

    @Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;
	
	public String handleFileProcessing(Map<String, MultipartFile> files, String atchFileId, String prefix) throws Exception {
	    // 파일이 비어있다면 null 반환
	    if (files.isEmpty()) {
	        return atchFileId;
	    }

	    // atchFileId가 없는 경우 새로 파일을 추가하는 로직
	    if (atchFileId == null || "".equals(atchFileId)) {
	        List<FileVO> fileList = fileUtil.parseFileInf(files, prefix, 0, atchFileId, "");
	        atchFileId = fileMngService.insertFileInfs(fileList);
	    } 
	    // atchFileId가 있는 경우 기존 파일에 업데이트하는 로직
	    else {
	        FileVO fvo = new FileVO();
	        fvo.setAtchFileId(atchFileId);
	        int cnt = fileMngService.getMaxFileSN(fvo);
	        List<FileVO> fileList = fileUtil.parseFileInf(files, prefix, cnt, atchFileId, "");
	        fileMngService.updateFileInfs(fileList);
	    }

	    return atchFileId;
	}

}
