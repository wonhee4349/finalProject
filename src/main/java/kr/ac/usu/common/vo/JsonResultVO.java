package kr.ac.usu.common.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class JsonResultVO {
	private String result;
	private String message;
	public JsonResultVO(String result, String message) {
		this.result = result;
		this.message = message;
	}
}
