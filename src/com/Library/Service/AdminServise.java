package com.Library.Service;

import com.Library.Dao.*;

public class AdminServise {
	private static AdminDao admin=new AdminDao();
	private static UserDao user=new UserDao();
	private static String ID;
	private static String PWD;
	/**
	 * ���һ���µĹ���Ա�˻�
	 * @param number
	 * @param password
	 */
	public static boolean addNewAdmin(String number,String password){
		return admin.NewAdmin(number, password);
	}
	
	/**
	 * �޸Ĺ���Ա����������ķ���
	 * @param id �ṩҪ�޸ĵĹ���Ա�˻�����
	 * @param pwd �µ�����
	 * @return ����Boolean �Ƿ��޸ĳɹ�
	 */
	public static boolean UpdataAdmin(String id,String pwd){	
		return admin.UpdataAdmin(id, pwd);
		
	}
	/**
	 * ɾ������Ա�Ĳ���,���չ���;
	 */
	public static boolean deleteAdmin(String ID){
		return admin.deleteAdmin(ID);
	}
	
	
	/**
	 * ����ԱȨ�� ɾ����ͨ�û�����
	 * @param ID �ṩɾ�������ѧ��
	 * @return	�����Ƿ�ɾ���ɹ�
	 */
	public static boolean AdminDeleteByUser(String ID){
		return user.DeleteUser(ID);
	}
	
	
	/**
	 * ������ͨ�û�����Ϣ
	 * @param FindID ��Ҫ���µ��û�IDѧ��
	 * @param UserName	�µ��û�����
	 * @return �����Ƿ���³ɹ�
	 */
public static boolean updataUserName(String FindID,String UserName){
		return user.UpdataUser(FindID, null, UserName);
	}
	
	public static boolean updataUserSex(String FindID,String UserSex){
		return user.UpdataUser(FindID, UserSex, null);
	}
	
	public static boolean updataUserPassword(String FindID,String UserPassword){
		return user.UpdataUser(FindID, UserPassword);
	}

	
	/**
	 * �жϹ���Ա�û�������˺���������ݿ��Ƿ�ƥ��
	 * @param numer �����˺���
	 * @param password	������Ĥ
	 * @return	����boolean����
	 */
	public static boolean AdminCheck(String numer,String password){
		boolean is= admin.Login(numer, password);
		if(is){
			ID=numer;
			PWD=password;
		}
		return is;
}

	public static String getID() {
		return ID;
	}

	public static String getPWD() {
		return PWD;
	}		
}
