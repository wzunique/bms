package com.Library.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.Library.DataBase.DataBase;

/**
 * ��ͼ��Ĳ���
 * ��ӣ�ɾ�������£�����
 * @author PuaChen
 *
 */
public class BookDao {
	/**
	 * ����һ����ͼ��
	 * @param bookid ���
	 * @param bookname	����
	 * @param bookAuthor	����
	 * @param bookPress	������
	 * @param bookType	���
	 * @return	�����Ƿ���ӳɹ� boolean
	 */
	public  boolean NewBook(String bookid,String bookname,String bookAuthor,String bookPress,String bookType){
		String sql="insert into  bmsbook(bookid,bookname,bookauthor,bookpress,booktype) values('"+bookid+"','"+bookname+"','"+bookAuthor+"','"+bookPress+"','"+bookType+"')";
		int i=DataBase.sendUpdateSQL(sql);
		if(i!=0)
			return true;
			else
				return false;
	}
	
	/**
	 * ɾ��ͼ��Ĳ��� �ṩ���
	 * @param bookid	���
	 * @return	����Boolean �Ƿ��޸ĳɹ�
	 */
	public  boolean deleteBook(String bookid){
		String sql="delete from bmsbook where bookid='"+bookid+"'";
		int i=DataBase.sendUpdateSQL(sql);
		if(i!=0)		
		return true;
		else
			return false;
	}
	
	//���²���***************************
	public  boolean UpdataBookID(String bookid,String newid){
		int i=DataBase.sendUpdateSQL("update bmsbook set bookid='"+newid+"' where bookid='"+bookid+"'");
		if(i!=0)		
		return true;
		else
			return false;
	}
	public  boolean UpdataBookName(String bookid,String newName){
		int i=DataBase.sendUpdateSQL("update bmsbook set bookname='"+newName+"' where bookid='"+bookid+"'");
		if(i!=0)		
		return true;
		else
			return false;
	}
	public  boolean UpdataBookAuthor(String bookid,String newauthor){
		int i=DataBase.sendUpdateSQL("update bmsbook set bookauthor='"+newauthor+"' where bookid='"+bookid+"'");
		if(i!=0)		
		return true;
		else
			return false;
	}
	public  boolean UpdataBookPress(String bookid,String newpress){
		int i=DataBase.sendUpdateSQL("update bmsbook set bookpress='"+newpress+"' where bookid='"+bookid+"'");
		if(i!=0)		
		return true;
		else
			return false;
	}
	public  boolean UpdataBookType(String bookid,String newtype){
		int i=DataBase.sendUpdateSQL("update bmsbook set booktype='"+newtype+"' where bookid='"+bookid+"'");
		if(i!=0)		
		return true;
		else
			return false;
	}
	//���²�����β***************************
	
	
	/**
	 * ����һ��ͼ����Ϣ��ͨ�����
	 * @param bookid ͼ������
	 * @return	����һ����6���������ַ�������.˳���±�ֱ���(���0������1������2��������3�����4,�Ƿ񱻽�5),���û���ҵ��򷵻ص���һ��ȫ��Ϊ��(null)�����顣
	 */
	@SuppressWarnings("finally")
	public  String[] FindsBookByID(String bookid){
		String sql="select * from bmsbook where bookid='"+bookid+"'";
		ResultSet rs=DataBase.sendQuerySQL(sql);
		if(rs!=null){
			String Result[]=new String[6];
			try {
				while(rs.next()){
					Result[0]=rs.getString("bookid");
					Result[1]=rs.getString("bookname");
					Result[2]=rs.getString("bookauthor");
					Result[3]=rs.getString("bookpress");
					Result[4]=rs.getString("booktype");
					Result[5]=String.valueOf(rs.getObject("state"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				return Result;
			}
		}else
			return null;
	}
	
	public ResultSet FindsBookName(String name){
		return DataBase.sendQuerySQL("select *from bmsbook where bookname like '"+name+"%'");
	}
	public ResultSet FindsBookID(String id){
		return  DataBase.sendQuerySQL("select *from bmsbook where bookid like '"+id+"%'");
	}
	public ResultSet FindsBookAuthor(String author){
		return  DataBase.sendQuerySQL("select *from bmsbook where bookauthor like '"+author+"%'");
	}
	public ResultSet FindsBookPress(String press){
		return  DataBase.sendQuerySQL("select *from bmsbook where bookpress like '"+press+"%'");
	}
	public ResultSet FindsBookType(String type){
		return  DataBase.sendQuerySQL("select *from bmsbook where booktype like '"+type+"%'");
	}
	public ResultSet FindsBookAll(){
		return  DataBase.sendQuerySQL("select * from bmsbook");
	}
}
