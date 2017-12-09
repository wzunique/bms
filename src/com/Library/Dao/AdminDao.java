package com.Library.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.Library.DataBase.DataBase;

/**
 * �Թ���Ա�û����в������࣡
 * �����жԹ���Ա�û�����ӡ�ɾ��������,�˺Ų���
 * @author PuaChen
 *
 */
public class AdminDao {
	/**
	 * ���һ���¹���Ա�����ݿ���
	 * @param id	�˻�
	 * @param pwd	����
	 * @return	����boolean �Ƿ���ӳɹ�
	 */
	public  boolean NewAdmin(String id,String pwd){
		String sql="insert into bmsadmin values ('"+id+"','"+pwd+"')";
		int i=DataBase.sendUpdateSQL(sql);
		if(i!=0)
		return true;
		else
			return false;
	}
	
	/**
	 * �޸Ĺ���Ա����������ķ���
	 * @param id �ṩҪ�޸ĵĹ���Ա�˻�����
	 * @param pwd �µ�����
	 * @return ����Boolean �Ƿ��޸ĳɹ�
	 */
	public  boolean UpdataAdmin(String id,String pwd){	
		String sql="update bmsadmin set pwd='"+pwd+"' where id='"+id+"'";
		int i=DataBase.sendUpdateSQL(sql);
		if(i!=0)		
		return true;
		else
			return false;
	}
	
	/**
	 * ɾ������Ա�Ĳ��� �ṩ�˻�����
	 * @param id	�˺�
	 * @return	����Boolean �Ƿ��޸ĳɹ�
	 */
	public  boolean deleteAdmin(String id){
		String sql="delete from bmsadmin where id='"+id+"'";
		int i=DataBase.sendUpdateSQL(sql);
		if(i!=0)		
		return true;
		else
			return false;
	}
	
	/**
	 * ����Ա���е�¼��������֤����
	 * @param numer ��¼���˺�
	 * @param pwd	��¼������
	 * @return	�����Ƿ��¼�ɹ� boolean
	 */
	public  boolean Login(String id,String pwd){
	
		ResultSet rs=DataBase.sendQuerySQL("select pwd from bmsadmin where id='"+id+"'");
		try {
			rs.next();
			if(pwd.equals(rs.getString("pwd")))
				return true;
		} catch (SQLException e) {
			return false;
		}
		return false;
		
	}
}
