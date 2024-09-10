package atos.lms.common.utl;

import java.util.List;

import org.springframework.http.HttpStatus;

import atos.lms.exam.service.GeneralModel;

@SuppressWarnings("serial")
public class ResponseVO implements GeneralModel {
	
    private HttpStatus httpStatus;
    private String message;
    private List<?> result;
    private int count;
    private boolean status;
    
    public int getCode() {
    	return httpStatus.value();
    }

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<?> getResult() {
		return result;
	}

	public void setResult(List<?> result) {
		this.result = result;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

    
}
