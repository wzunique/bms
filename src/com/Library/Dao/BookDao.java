package com.Library.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.Library.DataBase.DataBase;

/**
 * 对图书的操作
 * 添加，删除，更新，查找
 * @author PuaChen
 *
 */
public class BookDao {
	/**
	 * 新增一本新图书
	 * @param bookid 书号
	 * @param bookname	书名
	 * @param bookAuthor	作者
	 * @param bookPress	出版社
	 * @param bookType	类别
	 * @return	返回是否添加成功 boolean
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
	 * 删除图书的操作 提供书号
	 * @param bookid	书号
	 * @return	返回Boolean 是否修改成功
	 */
	public  boolean deleteBook(String bookid){
		String sql="delete from bmsbook where bookid='"+bookid+"'";
		int i=DataBase.sendUpdateSQL(sql);
		if(i!=0)		
		return true;
		else
			return false;
	}
	
	//更新操作***************************
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
	//更新操作结尾***************************
	
	
	/**
	 * 查找一本图书信息，通过书号
	 * @param bookid 图书的书号
	 * @return	返回一个带6个参数的字符串数组.顺序下表分别是(书号0，书名1，作者2，出版社3，类别4,是否被借5),如果没有找到则返回的是一个全部为空(null)的数组。
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
