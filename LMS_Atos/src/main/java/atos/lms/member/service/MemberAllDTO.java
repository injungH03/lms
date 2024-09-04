package atos.lms.member.service;

import java.util.List;

import atos.lms.exam.service.GeneralModel;

@SuppressWarnings("serial")
public class MemberAllDTO implements GeneralModel {
	
	private List<MemberVO> previewData;
	private String corpBiz;
	
	public List<MemberVO> getPreviewData() {
		return previewData;
	}
	public void setPreviewData(List<MemberVO> previewData) {
		this.previewData = previewData;
	}
	public String getCorpBiz() {
		return corpBiz;
	}
	public void setCorpBiz(String corpBiz) {
		this.corpBiz = corpBiz;
	}

}
