package com.Library.Service;

import com.Library.Dao.UserDao;



public class UserServise {
	private static UserDao user=new UserDao();
	/**
	 * �˴�Ϊ�û���½�ɹ���ȡ�û���������ϸ��Ϣ
	 */
	private static String name;
	private static String id;
	private static String sex;
	private static String numer;
	private static String password;
	private static String MaxBook;
	/**
	 * ���һ���µ���ͨ�û��˻�
	 * @param uname �û�����
	 * @param uid	�û�ѧ��
	 * @param sex �Ա�
	 * @param unumer	�û��˺�
	 * @param upassword	�û�����
	 */
	public static boolean addNewUser(String uname,String sex, String uid, String unumer, String upassword){
		return user.NewUser(uid, uname, sex, unumer, upassword);
	}
	
	
	/**
	 * ������ͨ�û�����Ϣ
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
	 * ɾ����ѧ����Ϣ
	 * @param FindID ��д��Ҫɾ����ѧ��ѧ��
	 * @return �����Ƿ�ɾ���ɹ�
	 */
	public static boolean deleteUser(String FindID){
		return user.DeleteUser(FindID);
	}
	
	/**
	 * ���ҵ�ѧ��ID ����
	 * @param ID ѧ��
	 * @return  �����ѧ�ŷ��� true �����ڷ���false
	 */
	public static boolean findUserByID(String ID){
		return false;
	}
	/**
	 * �����û����˺��Ƿ����
	 * @param numer �˻���
	 * @return ���ڷ���false, �����ڷ���true
	 */
	public static boolean findUserByNumer(String numer){
		return false;
	}
	
	/**
	 * ����һ���û����������������Ϣ
	 * @param ID  ��Ҫ���ص�ѧ��IDѧ��
	 * @return	�����ַ���
	 */
	
	public static String toStringUser() {
		return "UserServise [name=" + name + ", id=" + id + ", sex=" + sex + ", numer=" + numer + ", password="
				+ password + ", MaxBook=" + MaxBook + "]";
	}


	/**
	 * ��ȡ��¼�ɹ���ѧ��ȫ����Ϣ
	 * @param id
	 */
	public static void ExtractUser(String ID){
		String extract[]=user.FindsUserByID(ID);
		id=extract[0];
		name=extract[1];
		sex=extract[2];
		numer=extract[3];
		password=extract[4];
		MaxBook=extract[5];
	}
	
	public static String QueryUser(String ID){
		String extract[]=user.FindsUserByID(ID);
		return "ѧ��:"+extract[0]+"\n"+"������"+extract[1]+"\n"+"�Ա�"+extract[2]+"\n"+"�˺ţ�"+extract[3]+"\n"+"���룺"+extract[4]+"\n"
		+"�����飺"+extract[5]+"\n";
	}
	
	/**
	 * �ж���ͨ�û�������˺���������ݿ��Ƿ�ƥ��
	 * @param numer �����˺���
	 * @param password	��������
	 * @return	����boolean����
	 */
	public static boolean UserCheck(String numer,String password){
		String is= user.Login(numer, password);
		if(is!=null){
			ExtractUser(is);
			return true;
		}
		return false;
	}
	
	/**
	 * �������ѧ�������н������Ϣ
	 * @param ID
	 * @return
	 */
	public static String[][] BookDataByUserID(String ID){
		return user.PrintTable(ID);
	}

	/**
	 * ��ȡ��Ϣget������
	 * @return
	 */
	public static String getName() {
		return name;
	}


	public static String getId() {
		return id;
	}


	public static String getSex() {
		return sex;
	}


	public static String getNumer() {
		return numer;
	}


	public static String getPassword() {
		return password;
	}


	public static String getMaxBook() {
		return MaxBook;
	}
	
	
}
