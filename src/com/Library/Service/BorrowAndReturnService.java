package com.Library.Service;


import com.Library.Dao.BorrowAndReturnDao;


public class BorrowAndReturnService {
	private static BorrowAndReturnDao book=new BorrowAndReturnDao();
	/**
	 * �������
	 * @param BookON ���뻹��ͼ�����
	 * @param UserID ���뻹�Ľ���������
	 * @return ����ɹ�����true,���򷵻�false
	 */
	public static boolean Bookreturn(String BookON,String UserID){
		return book.Bookreturn(UserID, BookON);
	}
	/**
	 * �������
	 * @param BookON �����������
	 * @param UserID �������Ľ�����ѧ��
	 * @return ����ɹ�����true,���򷵻�false
	 */
	public static boolean BookBorrow(String BookON,String UserID){
		return book.BookBorrow(UserID, BookON);
	}
}
