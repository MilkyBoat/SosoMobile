package cn.milkyship.sosoMobile.model;

/**
 * <H2>文件名称: Scene.java </H2>
 * <p>描述: [场景类，用于储存预设的消费场景] </p>
 * <p>创建时间: 2019.9.23 </p><br>
 * <p><b>成员变量：</b></p>
 * <ul>
 *     <li>public String type</li>
 *     <li>public int data</li>
 *     <li>public String description</li>
 * </ul>
 * <p><b>成员方法：</b></p>
 * <ul>
 *     <li>public Scene(String type, int data, String description)</li>
 * </ul>
 *
 * @author <a href="xuyunkai@mail.nankai.edu.cn"> 徐云凯 </a>
 * @version v1.0
 */

public class Scene {
	
	public String type;
	public int data;
	public String description;
	
	/**
	 * <H2> public Scene(String type, int data, String description) </H2>
	 * 场景类的唯一构造函数
	 *
	 * @param type        场景类型，String
	 * @param data        消费数额, int
	 * @param description 场景描述， String.
	 */
	
	public Scene(String type, int data, String description) {
		this.type = type;
		this.data = data;
		this.description = description;
	}
	
	public String getType() {
		return type;
	}
	
	public int getData() {
		return data;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setData(int data) {
		this.data = data;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
