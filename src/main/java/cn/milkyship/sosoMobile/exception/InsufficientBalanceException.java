package cn.milkyship.sosoMobile.exception;

/**
 * <H2>文件名称: InsufficientBalanceException.java </H2>
 * <p>描述: [本项目使用的异常类，继承自Exception] </p>
 * <p>创建时间: 2019.9.23 </p><br>
 * <p><b>成员变量：</b></p>
 * <ul>
 *     <li>private String done</li>
 * </ul>
 * <p><b>成员方法：</b></p>
 * <ul>
 *     <li>public InsufficientBalanceException(String done)</li>
 *     <li>public String done()</li>
 * </ul>
 *
 * @author <a href="xuyunkai@mail.nankai.edu.cn"> 徐云凯 </a>
 * @version v1.0
 * @see Exception
 */

public class InsufficientBalanceException extends Exception {
	private String done;
	
	public InsufficientBalanceException(String done) {
		this.done = done;
	}
	
	public String getDone() {
		return done;
	}
}
