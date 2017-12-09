package com.Library.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DataBase {
	private static Connection con=null;
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;
	/**
	 * ���в�ѯ(select)�����ķ���
	 * @param sql ���͵�SQL���
	 * @return ����ResultSet�����
	 */
	@SuppressWarnings("finally")
	public static ResultSet sendQuerySQL(String sql){
		try {
			con=JdbcUtil.getMysql();
			
			ps=con.prepareStatement(sql);	
			 
			 rs=ps.executeQuery();
	
		} catch (Exception e) {
			return null;
		}finally {
			return rs;
		}
	}
	
	/**
	 * ִ�У�insert/update/delete�����롢���¡�ɾ��������ʹ�õķ���
	 * @param sql ���͵�SQL���
	 * @return	������Ӱ�����
	 */
	@SuppressWarnings("finally")
	public static int sendUpdateSQL(String sql){
			int i=0;
		
			con=JdbcUtil.getMysql();
			
			try {
				ps=con.prepareStatement(sql);
				 i=ps.executeUpdate();
			} catch (Exception e) {
				
				return i;
			}finally {
				return i;
			}
			 
			
	
		
	}
	
	public static void Colse(){
		JdbcUtil.close(con, ps, rs);
	}
}
