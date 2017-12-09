package com.Library.Service;

import java.sql.ResultSet;
import com.Library.Dao.BookDao;

public class BookServise {
	private static BookDao book=new BookDao();
	
	/**
	 * ����һ����ͼ��
	 * @param bookid ���
	 * @param bookname	����
	 * @param bookAuthor	����
	 * @param bookPress	������
	 * @param bookType	���
	 * @return	�����Ƿ���ӳɹ� boolean
	 */
	public static boolean NewBook(String bookid,String bookname,String bookAuthor,String bookPress,String bookType){
		return book.NewBook(bookid, bookname, bookAuthor, bookPress, bookType);
	}
	
	
	//ͨ��������ŷ���һ�����󣬶����������и��²�����
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
	 * ɾ��ͼ��Ĳ���
	 * @param FindBookON �ṩ��Ҫɾ����ͼ������
	 * @return �����Ƿ�ɾ���ɹ�
	 */
	public static boolean deleteBook(String FindBookON){
		return book.deleteBook(FindBookON);
	}
	
	/**
	 * ���ز��ҵ�ͼ����Ϣ
	 * @param bookON �ṩ��Ҫ���ҵ�ͼ�����
	 * @return �ҵ��Ȿ�鷵��true��û�ҵ�����false
	 */
	public static boolean findBookByON(String bookON){
		 if(book.FindsBookByID(bookON)!=null)
			 return true;
		 else
			 return false;
	}
	/**
	 * ���չؼ��ֲ���ͼ����ϸ��Ϣ ���ص���һ�������
	 * @param ���� �ṩ����
	 * @return ���� Result �����
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
