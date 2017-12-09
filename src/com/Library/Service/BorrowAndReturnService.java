package com.Library.Service;


import com.Library.Dao.BorrowAndReturnDao;


public class BorrowAndReturnService {
	private static BorrowAndReturnDao book=new BorrowAndReturnDao();
	/**
	 * 还书操作
	 * @param BookON 键入还的图书书号
	 * @param UserID 键入还的借书人书名
	 * @return 还书成功返回true,否则返回false
	 */
	public static boolean Bookreturn(String BookON,String UserID){
		return book.Bookreturn(UserID, BookON);
	}
	/**
	 * 借书操作
	 * @param BookON 键入借书的书号
	 * @param UserID 键入借书的借书人学号
	 * @return 借书成功返回true,否则返回false
	 */
	public static boolean BookBorrow(String BookON,String UserID){
		return book.BookBorrow(UserID, BookON);
	}
}
