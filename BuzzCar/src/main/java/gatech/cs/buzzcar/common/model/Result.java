package gatech.cs.buzzcar.common.model;

import gatech.cs.buzzcar.common.Globals;
import lombok.Getter;

@Getter
public class Result {

	private int code;
	private String message;
	private Object data;
	
	
	public Result(int code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public Result() {
		super();
	}

	public static Result dataByEffectRows(int rows){
		return rows>0? Result.success(): Result.fail();
	}

	public static Result dataByBool(boolean bool){
		return bool? Result.success(): Result.fail();
	}

	public static Result success() {
		return new Result(Globals.HTTP_OK,  Globals.HTTP_OK_MSG, null);
	}
	
	public static Result success(String message) {
		return new Result(Globals.HTTP_OK,  message, null);
	}

	public static Result success(int code, String message) {
		return new Result(code,  message, null);
	}

	public static Result successData(Object data) {
		return new Result(Globals.HTTP_OK,  Globals.HTTP_OK_MSG, data);
	}
	
	
	public static Result fail() {
		return new Result(Globals.HTTP_ERROR,  Globals.HTTP_ERROR_MSG, null);
	}
	
	public static Result fail(String message) {
		return new Result(Globals.HTTP_ERROR,  message, null);
	}
	
	public static Result fail(int code, String message) {
		return new Result(code,  message, null);
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}
