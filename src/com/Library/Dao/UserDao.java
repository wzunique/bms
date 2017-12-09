package com.Library.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.Library.DataBase.DataBase;

/**
 * 	����ͨ�û����еĲ���
 * 	���û��ģ���ӣ����£�ɾ��������
 * @author PuaChen
 *
 */
public class UserDao {
	
	
	/**
	 * ���һ���µ���ͨ�û������ݿ���
	 * @param id	ѧ��
	 * @param name	����	
	 * @param sex	�Ա�
	 * @param numer	�˺�	
	 * @param pwd	����
	 * @return	����boolean �Ƿ���ӳɹ�
	 */
	public  boolean NewUser(String id,String name,String sex,String numer,String pwd){
		String sql="insert into bmsuser values('"+id+"','"+name+"','"+sex+"')";
		String sql2="insert into bmsuser_numer(userid,numer,pwd) values('"+id+"','"+numer+"','"+pwd+"')";
		int i=DataBase.sendUpdateSQL(sql);
		if(i!=0){     //���ѧ�ű���ӳɹ� ������˺ű����� 
			int j=DataBase.sendUpdateSQL(sql2);
			if(j!=0)
				return true;
			else{			//������ʧ�ܻع���֮ǰ��ԭʼ����
				DataBase.sendUpdateSQL("delete from bmsuser where id='"+id+"'"); //���ݻع�ɾ������
				return false;
			}
			
		}else
			return false;
	}
	/**
	 * �޸����ݿ�����ͨ�û����Ա��������� �����޸ĵ�����null
	 * @param id	�޸��˵�ѧ��
	 * @param sex	�µ��Ա�
	 * @param name	�µ�����
	 * @return	����boolean �Ƿ��޸ĳɹ�
	 */
	public  boolean UpdataUser(String id,String sex,String name){
		String sql;
		if(name==null && sex!=null)
			sql="update bmsuser set sex='"+sex+"' where id='"+id+"'";
		else if(sex==null && name!=null)
			sql="update bmsuser set Uname='"+name+"' where id='"+id+"'";
		else 
			sql="update bmsuser set Uname='"+name+"',sex='"+sex+"' where id='"+id+"'";
		
		int i=DataBase.sendUpdateSQL(sql);
		if(i!=0)
			return true;
			else
				return false;
	}
	
	/**
	 * �޸����ݿ�����ͨ�û����������
	 * @param id	�޸��˵�ѧ��
	 * @param pwd	������
	 * @return ����boolean �Ƿ��޸ĳɹ�
	 */
	public  boolean UpdataUser(String id,String pwd){
		String sql="update bmsuser_numer set pwd='"+pwd+"' where userid='"+id+"'";
		int i=DataBase.sendUpdateSQL(sql);
		if(i!=0)
			return true;
			else
				return false;
	}
	/**
	 * ɾ����ͨ�û�����Ϣ����
	 * @param id	�ṩɾ���û���ѧ��
	 * @return	����boolean �Ƿ�ɾ���ɹ�
	 */
	public  boolean DeleteUser(String id){
		int i=DataBase.sendUpdateSQL("delete from bmsuser where id='"+id+"'");
		if(i!=0){
			return true;
		}
		else
			return false;
	}
	
	/**
	 * ����ͨ�û��Ĳ��Ҳ���,��ѧ�Ž��в���
	 * @param id ����Ҫ���ҵ�ѧ��
	 * @return ����һ����6���������ַ���.˳���±�ֱ���(ѧ��0������1���Ա�2���˺�3������4��ͼ�����������5),���û���ҵ��򷵻ص���һ��ȫ��Ϊ��(null)�����顣
	 */
	@SuppressWarnings("finally")
	public  String[] FindsUserByID(String id){
		String Result[]=new String[6];
		ResultSet rs1;
		ResultSet rs;
		String sql="select * from bmsuser where id='"+id+"'";
		rs=DataBase.sendQuerySQL(sql);
		if(rs!=null){
			try {
				while(rs.next()){
					Result[0]=rs.getString("id");
					Result[1]=rs.getString("Uname");
					Result[2]=rs.getString("sex");
				}
			} catch (SQLException e) {
				return Result=null;
			}finally {
				rs1=DataBase.sendQuerySQL("select * from bmsuser_numer where userid='"+id+"'");
				try {
					while(rs1.next()){
						Result[3]=rs1.getString("numer");
						Result[4]=rs1.getString("pwd");
						Result[5]=String.valueOf(rs1.getInt("booksize"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
					return Result=null;
				}finally{
					return Result;
				}
			}
		}else
			return null;
		
	}
	
	/**
	 * �û����е�¼��������֤����
	 * @param numer ��¼���˺�
	 * @param pwd	��¼������
	 * @return	�����Ƿ��¼�ɹ���ѧ��IDѧ��
	 */
	public  String Login(String numer,String pwd){
	
		ResultSet rs=DataBase.sendQuerySQL("select pwd,userid from bmsuser_numer where numer='"+numer+"'");
			if(rs!=null){
			try {
				while(rs.next()){
				if(pwd.equals(rs.getString("pwd")))
					return rs.getString("userid");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			}	
		
		return null;
		
	}
	
	/**
	 * �鿴һ���û������Ǳ���
	 * @param userid	�û���id
	 * @return	����һ��ArrayList��������¼����û����н��ĵ��鼮�����
	 */
	@SuppressWarnings("finally")
	public  ArrayList<String> UserBorrowedBooks(String userid){
		ArrayList<String> booksid=new ArrayList<String>();
		ResultSet rs=DataBase.sendQuerySQL("select * from BorrowAndReturn where userid='"+userid+"'");
		if(rs!=null){
			try {
				while(rs.next()){
					booksid.add(rs.getString("bookid"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				return booksid;
			}
		}else
			return null;
	}
	
	/**
	 * �ṩһ���û�ѧ�ŷ��� ���ĵ�����ͼ����ϸ��Ϣ
	 * @param userid ѧ��
	 * @return	����һ���ַ������鳤��Ϊ6��  �±�ֱ����(���0������1������2��������3������4������ʱ��5)�������쳣���ؿ�
	 */
	@SuppressWarnings("finally")
	public  String[][] PrintTable(String userid){
		ArrayList<String> booksid=UserBorrowedBooks(userid);
		
		if(booksid!=null){		//���ж��Ƿ�Ϊ��
			String Result[][]=new String[booksid.size()][6];
			String bid;
			for(int i=0;i<booksid.size();i++){
				bid=booksid.get(i);
				ResultSet rs=DataBase.sendQuerySQL("select * from BorrowAndReturn where bookid='"+bid+"'");
				if(rs!=null){
					try {
						while(rs.next()){
							Result[i][0]=rs.getString("bookid");
							Result[i][5]=rs.getTimestamp("time").toString();
						}
					} catch (SQLException e) {
						return Result=null;
					}finally{	//���û�������쳣    ȡ����
						ResultSet rs1=DataBase.sendQuerySQL("select * from bmsbook where bookid='"+bid+"'");
						if(rs1!=null){
							try {
								while(rs1.next()){
									Result[i][1]=rs1.getString("bookname");
									Result[i][2]=rs1.getString("bookauthor");
									Result[i][3]=rs1.getString("bookpress");
									Result[i][4]=rs1.getString("booktype");
								}
							} catch (SQLException e) {
								e.printStackTrace();
								return Result=null;
							}finally{
								continue;
							}
						}
					}
				}
			}
			return Result;
			
		}else
		return null;
	}
		
}
