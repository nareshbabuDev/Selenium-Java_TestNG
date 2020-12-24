package com.medecision.utils;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class DatabaseConfig {
	protected ExtentTest test;
	
	public DatabaseConfig(ExtentTest test) {
	this.test =test;
	}
	public DatabaseConfig DbConnectionError() {
		test.log(LogStatus.FAIL, "Database Communication link failure");
		return this;
	}
	public DatabaseConfig NotificationFailureVerification(String sqlip,String schemaname,String User,String pass ) throws ClassNotFoundException,SQLException {
	        Connection conn = null;
	        try {
	            conn = createDatabaseConnection(sqlip, schemaname, User,pass);
	            List<NotificationAudit> notificationAuditList = executeNotificationVerify(conn, "FAILURE", -1);
	            if(notificationAuditList.size()>0) {
	             for(NotificationAudit notiAudit : notificationAuditList) {
	                 test.log(LogStatus.WARNING, "Notification AuditID : "+"<span style='color: blue'>"+notiAudit.getNotification_audit_id()+"</span><br/>"+
	                         "Notification SubscriptionID : "+"<span style='color: blue'>"+notiAudit.getNotification_subscription_id()+"</span><br/>"+
	                         "Disposition : "+"<span style='color: blue'>"+notiAudit.getDisposition()+"</span><br/>"+
	                         "EndPoint : "+"<span style='color: blue'>"+notiAudit.getEndpoint()+"</span><br/>"+
                     		 "Last Updated Time :"+"<span style='color: blue'>"+notiAudit.getLast_update_date_time()+"</span><br/>"+
	                         "Creation Time :"+"<span style='color: blue'>"+notiAudit.getCREATION_DATE_TIME()+"</span><br/>");
	             }
	            }else {
	            	test.log(LogStatus.PASS,"No Notification Failures");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally {
	            conn.close();
	        }
		return this;
	}
	public Connection createDatabaseConnection(String ip, String db, String user, String pass)throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://"+ip+":3306/"+db, user,pass);
		return con;
	}

	public List<NotificationAudit> executeNotificationVerify(Connection conn, String disposition, int createdDateFrom)
			throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet resultSet = null;
		List<NotificationAudit> notiAuditList = new ArrayList<NotificationAudit>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, createdDateFrom);
		cal.setTimeZone(TimeZone.getTimeZone("GMT"));
		String date = formatter.format(cal.getTime());
		try {
			String selectSql = "SELECT na.notification_audit_id,na.patient_id,na.notification_subscription_id,na.disposition,na.endpoint,nad.outgoing_msg,na.CREATION_DATE_TIME,\r\n"
					+ "nad.response,na.last_update_date_time,na.`COMMUNITY_ID`\r\n"
					+ "FROM gsialerting.notification_audit na \r\n"
					+ "JOIN gsialerting.notification_audit_data nad \r\n"
					+ "WHERE na.notification_audit_id = nad.id\r\n" + "AND ( na.disposition = '" + disposition
					+ "')\r\n" + "AND CREATION_DATE_TIME >= '" + date + "'\r\n"
					+ "ORDER BY na.notification_audit_id DESC LIMIT 10;";

			System.out.println(selectSql);
			resultSet = stmt.executeQuery(selectSql);
			while (resultSet.next()) {
				NotificationAudit notiAudit = new NotificationAudit();
				notiAudit.setCOMMUNITY_ID(BigInteger.valueOf(resultSet.getInt("COMMUNITY_ID")));
				notiAudit.setCREATION_DATE_TIME(resultSet.getDate("CREATION_DATE_TIME"));
				notiAudit.setDisposition(resultSet.getString("disposition"));
				notiAudit.setEndpoint(resultSet.getString("endpoint"));
				notiAudit.setLast_update_date_time(resultSet.getDate("last_update_date_time"));
				notiAudit.setNotification_audit_id(BigInteger.valueOf(resultSet.getInt("notification_audit_id")));
				notiAudit.setNotification_subscription_id(BigInteger.valueOf(resultSet.getInt("notification_subscription_id")));
				notiAudit.setPatient_id(resultSet.getString("patient_id"));
				notiAuditList.add(notiAudit);
			}
			System.out.println(notiAuditList.size());
			return notiAuditList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return notiAuditList;
	}

}

