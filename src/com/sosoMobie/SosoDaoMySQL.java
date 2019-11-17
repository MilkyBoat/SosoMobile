package com.sosoMobie;

import java.sql.*;

/**
 * <p>项目名称: SosoMobie </p>
 * <p>文件名称: SosoDaoMySQL.java </p>
 * <p>描述: DAO的MySQL实现 </p>
 * <p>创建时间:  </p>
 *
 * @author xuyunkai@mail.nankai.edu.cn 徐云凯
 * @version v1.0
 */

public class SosoDaoMySQL extends SosoDaoUtil implements SosoDao {
	
	public SosoDaoMySQL() {
		init();
	}
	
	/**
	 * @param mobileCard :
	 *                   Title: save
	 *                   Description: [将电话卡信息存入数据库]
	 * @author 徐云凯
	 * Datetime: 2019/11/13 22:34
	 */
	@Override
	public void save(MobileCard mobileCard) {
		
		String sql = "INSERT INTO user_info " +
				"( card_number, user_name, password, service_package, money, real_talk_time, real_SMS_count, real_flow) " +
				"VALUES (?, ?, ?, ?, ?, 0, 0, 0)";
		
		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, mobileCard.cardNumber);
			preparedStatement.setString(2, mobileCard.userName);
			preparedStatement.setString(3, mobileCard.password);
			preparedStatement.setString(4, mobileCard.serPackage.type);
			preparedStatement.setDouble(5, mobileCard.money);
			
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection(connection, preparedStatement, null);
	}
	
	/***
	 * Title: del
	 * Description: [从数据库删除电话卡信息]
	 * @param number:
	 * @author 徐云凯
	 * Datetime:  2019/11/13 22:35
	 */
	@Override
	public void del(String number) {
		String sql = "DELETE FROM user_info WHERE card_number = ?";
		
		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, number);
			
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection(connection, preparedStatement, null);
	}
	
	/***
	 * Title: update
	 * Description: [更改数据库中的某一个电话卡信息]
	 * @param mobileCard :
	 * @author 徐云凯
	 * Datetime: 2019/11/13 22:35
	 */
	@Override
	public void update(MobileCard mobileCard) {
		
		String sql = "UPDATE user_info SET " +
				"card_number = ?, " +
				"user_name = ?, " +
				"password = ?, " +
				"service_package = ?, " +
				"money = ?, " +
				"real_talk_time = ?, " +
				"real_SMS_count = ?, " +
				"real_flow = ? " +
				"WHERE card_number = ?";
		
		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, mobileCard.cardNumber);
			preparedStatement.setString(2, mobileCard.userName);
			preparedStatement.setString(3, mobileCard.password);
			preparedStatement.setString(4, mobileCard.serPackage.type);
			preparedStatement.setDouble(5, mobileCard.money);
			preparedStatement.setInt(6, mobileCard.realTalkTime);
			preparedStatement.setInt(7, mobileCard.realSMSCount);
			preparedStatement.setInt(8, mobileCard.realFlow);
			preparedStatement.setString(9, mobileCard.cardNumber);
			
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection(connection, preparedStatement, null);
	}
	
	/***
	 * Title: findCardByNumber
	 * Description: [由卡号查找电话卡]
	 * @param Number :
	 * @return com.sosoMobie.MobileCard
	 * @author 徐云凯
	 * Datetime: 2019/11/13 22:36
	 */
	@Override
	public MobileCard findCardByNumber(String Number) {
		MobileCard mobileCard = null;
		
		String sql = "SELECT * FROM user_info WHERE card_number = ?";
		Connection connection = getConnection();
		PreparedStatement preparedStatement;
		ResultSet rs;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, Number);
			
			rs = preparedStatement.executeQuery();
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		try {
			rs.last();
			if (rs.getRow() == 0) {
				return null;  //判断是否查询到结果
			}
			mobileCard = new MobileCard(
					rs.getString("card_number"),
					rs.getString("user_name"),
					rs.getString("password"),
					findPackage(rs.getString("service_package")),
					rs.getDouble("money")
			);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection(connection, preparedStatement, rs);
		return mobileCard;
	}
	
	/***
	 * Title: isCardExit
	 * Description: [由号码判断电话卡存在]
	 * @param number:
	 * @return boolean:
	 * @author 徐云凯
	 * Datetime:  2019/11/13 22:36
	 */
	@Override
	public boolean isCardExit(String number) {
		
		String sql = "SELECT user_name FROM user_info WHERE card_number = ?";
		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, number);
			
			rs = preparedStatement.executeQuery();
			
			rs.last();
			if (rs.getRow() == 0) {
				return false;  //判断是否查询到结果
			}
			if (rs.getString("user_name") != null) {
				return true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection(connection, preparedStatement, rs);
		return false;
	}
	
	/***
	 * Title: isCardExit
	 * Description: [由号码与密码判断电话卡存在]
	 * @param number:
	 * @param password:
	 * @return boolean:
	 * @author 徐云凯
	 * Datetime:  2019/11/13 22:36
	 */
	public boolean isCardExit(String number, String password) {
		
		String sql = "SELECT user_name, password FROM user_info WHERE card_number = ?";
		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, number);
			
			rs = preparedStatement.executeQuery();
			rs.last();
			if (rs.getRow() == 0) {
				return false;   //判断是否查询到结果
			}
			if (rs.getString(1) != null) {
				if (rs.getString(2).equals(password)) {
					return true;
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection(connection, preparedStatement, rs);
		return false;
	}
	
	/***
	 * Title: save
	 * Description: [向数据库中存入通话记录]
	 * @param consumInfo :
	 * @author 徐云凯
	 * Datetime: 2019/11/13 22:36
	 */
	@Override
	public void save(ConsumInfo consumInfo) {
		String sql = "INSERT INTO call_record " +
				"( card_number, type, consum_data, time) " +
				"VALUES (?, ?, ?, NOW())";
		
		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, consumInfo.cardNumber);
			preparedStatement.setString(2, consumInfo.type);
			preparedStatement.setInt(3, consumInfo.consumData);
			
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection(connection, preparedStatement, null);
	}
	
	/***
	 * Title: del
	 * Description: [从数据库中删除通话记录]
	 * @param consumInfo :
	 * @author 徐云凯
	 * Datetime: 2019/11/13 22:37
	 */
	@Override
	public void del(ConsumInfo consumInfo) {
		String sql = "DELETE FROM call_record WHERE card_number = ?";
		
		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, consumInfo.cardNumber);
			
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection(connection, preparedStatement, null);
	}
	
	/***
	 * Title: update
	 * Description: [更改数据库中已经存在的通话记录]
	 * @param consumInfo :
	 * @author 徐云凯
	 * Datetime: 2019/11/13 22:37
	 */
	@Override
	public void update(ConsumInfo consumInfo) {
		
		String sql = "UPDATE call_record SET " +
				"card_number = ?, " +
				"type = ?, " +
				"consum_data = ?, " +
				"time = NOW()" +
				"WHERE card_number = ?";
		
		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, consumInfo.cardNumber);
			preparedStatement.setString(2, consumInfo.type);
			preparedStatement.setInt(3, consumInfo.consumData);
			preparedStatement.setString(4, consumInfo.cardNumber);
			
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection(connection, preparedStatement, null);
	}
	
	/***
	 * Title: findRecByNumber
	 * Description: [由卡号查找通话记录]
	 * @param Number :
	 * @return com.sosoMobie.ConsumInfo
	 * @author 徐云凯
	 * Datetime: 2019/11/13 22:37
	 */
	@Override
	public ConsumInfo[] findRecByNumber(String Number) {
		ConsumInfo[] consumInfos;
		
		String sql = "SELECT type, consum_data FROM call_record WHERE card_number = ? ";
		Connection connection = getConnection();
		PreparedStatement preparedStatement;
		ResultSet rs;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, Number);
			rs = preparedStatement.executeQuery();
			consumInfos = new ConsumInfo[rs.getMetaData().getColumnCount()];
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		try {
			int i = 0;
			while (rs.next()) {
				ConsumInfo tempConsumInfo = new ConsumInfo(
						Number,
						rs.getString(1),
						rs.getInt(2)
				);
				consumInfos[i++] = tempConsumInfo;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection(connection, preparedStatement, rs);
		return consumInfos;
	}
	
	/***
	 * Title: findPackage
	 * Description: [由套餐名称查找套餐]
	 * @param name:
	 * @return com.sosoMobie.ServicePackage
	 * @author 徐云凯
	 * Datetime:  2019/11/16 20:39
	 */
	public ServicePackage findPackage(String name) {
		ServicePackage servicePackage = null;
		
		String sql = "SELECT price, talktime, smscount, flow FROM servicePackage WHERE name = ?";
		Connection connection = getConnection();
		PreparedStatement preparedStatement;
		ResultSet rs;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			
			rs = preparedStatement.executeQuery();
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		try {
			rs.last();
			if (rs.getRow() == 0) {
				return null;
			}
			servicePackage = new ServicePackage(
					name,
					rs.getDouble(1),
					rs.getInt(2),
					rs.getInt(3),
					rs.getInt(4)
			);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection(connection, preparedStatement, rs);
		return servicePackage;
	}
	
	/***
	 * Title: findPackage
	 * Description: [查找全部套餐]
	 * @return com.sosoMobie.ServicePackage
	 * @author 徐云凯
	 * Datetime:  2019/11/17 10:22
	 */
	
	public ServicePackage[] findPackage() {
		
		ServicePackage[] servicePackage;
		
		String sql = "SELECT name, price, talkTime, smsCount, flow, zhName FROM servicePackage";
		Connection connection = getConnection();
		PreparedStatement preparedStatement;
		ResultSet rs;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			servicePackage = new ServicePackage[rs.getMetaData().getColumnCount()];
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		try {
			int i = 0;
			while (rs.next()) {
				ServicePackage tempServicePackage = new ServicePackage(
						rs.getString(1),
						rs.getDouble(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getInt(5)
				);
				tempServicePackage.name = rs.getString("zhName");
				servicePackage[i++] = tempServicePackage;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection(connection, preparedStatement, rs);
		return servicePackage;
	}
}


