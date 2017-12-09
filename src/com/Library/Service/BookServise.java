package com.Library.Service;

import java.sql.ResultSet;
import com.Library.Dao.BookDao;

public class BookServise {
	private static BookDao book=new BookDao();
	
	/**
	 * 新增一本新图书
	 * @param bookid 书号
	 * @param bookname	书名
	 * @param bookAuthor	作者
	 * @param bookPress	出版社
	 * @param bookType	类别
	 * @return	返回是否添加成功 boolean
	 */
	public static boolean NewBook(String bookid,String bookname,String bookAuthor,String bookPress,String bookType){
		return book.NewBook(bookid, bookname, bookAuthor, bookPress, bookType);
	}
	
	
	//通过查找书号返回一个对象，对这个对象进行更新操作；
	public static boolean updataBookName(String FindNo,String bookname){
		return book.UpdataBookName(FindNo, bookname);
	}
	public static boolean updataBookON(String FindNo,String bookON){
		return book.UpdataBookID(FindNo, bookON);
	}
	public static boolean updataBookPress(String FindNo,String bookPress){
		return book.UpdataBookPress(FindNo, bookPress);
	}
	public static boolean updataBookAuthor(String FindNo,String bookAuthor){
		return book.UpdataBookAuthor(FindNo, bookAuthor);
	}
	public static boolean updataBookType(String FindNo,String bookType){
		return book.UpdataBookType(FindNo, bookType);
	}
	
	/**
	 * 删除图书的操作
	 * @param FindBookON 提供需要删除的图书的书号
	 * @return 返回是否删除成功
	 */
	public static boolean deleteBook(String FindBookON){
		return book.deleteBook(FindBookON);
	}
	
	/**
	 * 返回查找的图书信息
	 * @param bookON 提供需要查找的图书书号
	 * @return 找到这本书返回true，没找到返回false
	 */
	public static boolean findBookByON(String bookON){
		 if(book.FindsBookByID(bookON)!=null)
			 return true;
		 else
			 return false;
	}
	/**
	 * 按照关键字查找图书详细信息 返回的是一个结果集
	 * @param 参数 提供参数
	 * @return 返回 Result 结果集
	 */
	public static ResultSet FindsBookName(String name){
		return book.FindsBookName(name);
	}
	public static ResultSet FindsBookID(String id){
		return book.FindsBookID(id);
	}
	public static ResultSet FindsBookAuthor(String author){
		return book.FindsBookAuthor(author);
	}
	public static ResultSet FindsBookPress(String press){
		return book.FindsBookPress(press);
	}
	public static ResultSet FindsBookType(String type){
		return book.FindsBookType(type);
	}
	public static ResultSet FindsBookAll(){
		return book.FindsBookAll();
	}
}
