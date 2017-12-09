/*package Test;

import java.awt.Font;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.*;

import com.Library.Service.BorrowAndReturnService;
import com.Library.Service.UserServise;


 class QueryBookTable extends JFrame {

	private static final long serialVersionUID = 8456094523646445002L;
	// �����ݿ���ȡ����Ϣ
	// rowData�������������
	// columnNames�������
	private JScrollPane jsp;
	private ResultSet rs;
	private Vector<Serializable> rowData, columnNames;
	private PopupMenu popupMenu1;
	private JTable jt;

	// ���캯��
	public QueryBookTable(ResultSet rs) {
		columnNames = new Vector<Serializable>();
		columnNames.add("���");
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("������");
		columnNames.add("����");
		columnNames.add("ʱ��");
		this.rs = rs;
		rowData = gethang();
		jt = new JTable(rowData, columnNames);
		MouseEventButtonListener();
		jt.setRowHeight(20);
		jt.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		
		jt.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	            	 if(e.getButton()==MouseEvent.BUTTON3 ){//������Ҽ�����Ļ�             	
		                	popupMenu1.show(jt,e.getX(),e.getY());	               	                	
		                }
	            }
		});
		jsp = new JScrollPane(jt);
		this.add(jsp);
		this.setSize(600, 300);
		setLocationRelativeTo(null);
		setTitle("��ѯ���");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}

		});
		this.setVisible(true);
	}

	@SuppressWarnings("finally")
	public Vector<Serializable> gethang() {
		Vector<Serializable> rowData = new Vector<Serializable>();
		try {

			while (rs.next()) {
				Vector<Serializable> hang = new Vector<Serializable>();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getString(4));
				hang.add(rs.getString(5));
				rowData.add(hang);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return rowData;
		}
	}
	
	*//**
	 * �Ҽ��˵������ �Ͳ˵��ļ�����Ӧ ����
	 *//*
	public void MouseEventButtonListener(){
		popupMenu1 = new PopupMenu();	//�����˵�����
		MenuItem menuItem1 = new MenuItem();	//����Ӳ˵�
		menuItem1.setLabel("��������");	// �涨����
		popupMenu1.add(menuItem1);	// ��ӵ��˵�������
		getContentPane().add(popupMenu1);	//��ӵ�������
		menuItem1.addActionListener(new ActionListener() { //�˵�1���¼�����
			public void actionPerformed(ActionEvent e) {
				int i=jt.getSelectedRow() ;
				String bookid=(String) jt.getValueAt(i,0);	
				String bookname=(String) jt.getValueAt(i,1);	
				int k = JOptionPane.showConfirmDialog(null, "��Ҫ��-" + bookname + "-�Ȿ����", "��ʾ",
						JOptionPane.YES_NO_OPTION);
				if (k == 0) {
					if (BorrowAndReturnService.BookBorrow(bookid, UserServise.getId()))
						JOptionPane.showMessageDialog(null, "����ɹ����ú�ѧϰ��������", "��ʾ", JOptionPane.PLAIN_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "���������ϣ����Ѿ�������", "����", JOptionPane.ERROR_MESSAGE);	
				}
			}
			});
	}
}
*/