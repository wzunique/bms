/*package Test;



import java.sql.SQLException;

import com.Library.DataBase.DataBase;
import com.Library.Service.*;

public class test {

	public static void main(String[] arge) throws SQLException{
	//public test(){
		
		System.out.println(AdminDao.NewAdmin("160569", "123"));
		
//		System.out.println(UserDao.NewUser("001", "³ÂÔóµÇ", "ÄÐ", "123", "456"));
	
		String a[][]=user.BookDataByUserID("001");
		for(String[] i:a){
			  for(String x : i)
			  System.out.print(x+"-");
			  System.out.println();
		}
		//System.out.println(UserServise.UserCheck("123", "456"));
	
		System.out.println(UserServise.toStringUser());
		//System.out.println(new BorrowAndReturnDao().BookBorrow("001", "201703"));
		String a[][]=UserServise.BookDataByUserID(UserServise.getId());
		for(String[] i:a){
			  for(String x : i)
			  System.out.print(x+"-");
			  System.out.println();
		}
		System.out.println(BookServise.FindsBookAll().getString(1));
		
		DataBase.Colse();

		
	}

}
*/