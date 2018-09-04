package sys.domain;

import java.io.Serializable;

public class Result<T> implements Serializable {
	
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 2277185951173177898L;
	/**
	 * 200:OK<br>
	 * 205:查无数据 <br>
	 * 400:参数异常 <br>
	 * 401:认证未通过 <br>
	 * 500：服务器内部异常<br>
	 */
	private String code;
	/**
	 * true:操作成功<br>
	 * false:操作失败
	 */
	/**
	 * 查询情况说明
	 */
	private String msg;
	/**
	 * 传输具体数据
	 */
	private T data;

	public Result() {

	}

	public Result(String code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public Result(String code,String msg) {
		this.code = code;
		this.msg = msg;
	}
 

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
