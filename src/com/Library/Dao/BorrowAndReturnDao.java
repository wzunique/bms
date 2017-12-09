package com.Library.Dao;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.Library.DataBase.DataBase;

/**
 * 对借还书的的Data操作
 * @author PuaChen
 *
 */
public class BorrowAndReturnDao {
	private UserDao user=new UserDao();
	private BookDao book=new BookDao();
	
	//先判断这个用户借了那些书--返回书籍的名称 进行判断是否相等如果相等，进行删除操作，把书籍的号start设置为1；
	public  boolean Bookreturn(String userid,String bookid){
		ArrayList<String> booksid=user.UserBorrowedBooks(userid);
		if(booksid!=null){
		for(Object i:booksid){
			if(i.equals(bookid)){
				DataBase.sendUpdateSQL("delete from BorrowAndReturn where bookid='"+bookid+"'");
				DataBase.sendUpdateSQL("update bmsbook set state='1' where bookid='"+bookid+"'");
				DataBase.sendUpdateSQL("update bmsuser_numer set booksize=booksize+1 where userid='"+userid+"'");
				return true;
			}
		}
		}
		return false;
	}
	
	public  boolean BookBorrow(String userid ,String bookid){
		String Result[]=book.FindsBookByID(bookid);
		if(bookid.equals(Result[0]) && "1".equals(Result[5])){
			Timestamp t = new Timestamp(System.currentTimeMillis());
			int i=DataBase.sendUpdateSQL("insert into BorrowAndReturn(userid,bookid,time) values('"+userid+"','"+bookid+"','"+t+"')");
			if(i!=0){
				DataBase.sendUpdateSQL("update bmsbook set state='0' where bookid='"+bookid+"'");
				DataBase.sendUpdateSQL("update bmsuser_numer set booksize=booksize-1 where userid='"+userid+"'");
				return true;
			}
				else
					return false;
		}
		else
			return false;
	}
}
