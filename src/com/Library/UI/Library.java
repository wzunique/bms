package com.Library.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

import com.Library.DataBase.DataBase;
import com.Library.Service.*;
import java.awt.event.*;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * ͼ�����ϵͳ 2.0 Mysql ���ݿ��
 * �û���
 * @author PuaChen
 */
public class Library extends JFrame {
	private static final long serialVersionUID = -1169720619929459438L;
	private JPanel imagePanel;
	private ImageIcon background;
	private JPanel pane = null; // ��Ҫ��JPanel
	private CardLayout card = null; // CardLayout���ֹ�����
	private JPanel p_1 = null, p_2 = null, p_3 = null, p_4 = null;
	private String CheckSex;	//�жϸ����û�ѡ����Ա�
	private int bookListWidth;	
	private JScrollPane bookList;
	private JPanel bookListPanel;
	private ImageIcon bookimg = new ImageIcon("userimg/BookDefault.png");// ����ͼƬ
	private ArrayList<String> findsList = new ArrayList<String>();
	private PopupMenu popupMenu1;
	private JTable myBookTabel;
	private Vector<Serializable> rowData,columnNames;
	private DefaultTableModel tabModel;
	private int count = 0;
	private JLabel hasbook,maxbook,credit;
	private int myBookSize=Integer.parseInt(UserServise.getMaxBook());	//��ȡ�û����ܽ������
	


	public Library() {		
		setBounds(100, 100, 1208, 840);
		setTitle("Libraryͼ���       User(V2.0)");
		setLocationRelativeTo(null);// �ô��ھ�����ʾ
		setResizable(false); // ���ô�����󻯰�ť
		setImg();
		CardPanle();
		SetLabelBtn();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				DataBase.Colse();//�ر����ݿ�����
				System.exit(0);
			}
		});
		setVisible(true);
	}

	/**
	 * ���ر���ͼƬ
	 */
	public void setImg() {
		background = new ImageIcon("userimg/UserUi.png");// ����ͼƬ
		JLabel label = new JLabel(background);// �ѱ���ͼƬ��ʾ��һ����ǩ����
		// �ѱ�ǩ�Ĵ�Сλ
		// ������ΪͼƬ�պ�����������
		label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		// �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
		imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(null);
		this.getLayeredPane().setLayout(null);
		// �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
	}

	/**
	 * ע�ῨƬ���ֿ��
	 */
	public void CardPanle() {
		card = new CardLayout(5, 5);
		pane = new JPanel(card); // JPanel�Ĳ��ֹ��������ó�CardLayout
		pane.setBounds(242, 81, 960, 724);
		getContentPane().add(pane);

		p_1 = new JPanel();
		p_2 = new JPanel();
		p_3 = new JPanel();
		p_4 = new JPanel();

		p_1.setLayout(null);
		p_2.setLayout(null);
		p_3.setLayout(null);
		p_4.setLayout(null);

		ClickEvent_Userpersonal();
		ClickEvent_UserUpdata();
		FindsBook();
		BookList();
		getBook();
		SetMyBookList();
		MouseEventButtonListener();

		pane.add(p_1, "p1");

		pane.add(p_2, "p2");
		pane.add(p_3, "p3");

		pane.add(p_4, "p4");

	}

	public void SetLabelBtn() {
		JLabel myuser = new JLabel("�ҵ���Ϣ");
		myuser.setHorizontalAlignment(SwingConstants.CENTER);
		myuser.setForeground(SystemColor.textHighlight);
		myuser.setFont(new Font("΢���ź�", Font.BOLD, 20));
		myuser.setBounds(92, 105, 95, 28);
		getContentPane().add(myuser);

		JLabel updata = new JLabel("�����ҵ���Ϣ");
		updata.setHorizontalAlignment(SwingConstants.CENTER);
		updata.setForeground(SystemColor.textHighlight);
		updata.setFont(new Font("΢���ź�", Font.BOLD, 20));
		updata.setBounds(92, 177, 125, 28);
		getContentPane().add(updata);

		JLabel library = new JLabel("ͼ���");
		library.setHorizontalAlignment(SwingConstants.CENTER);
		library.setForeground(SystemColor.textHighlight);
		library.setFont(new Font("΢���ź�", Font.BOLD, 20));
		library.setBounds(92, 251, 95, 28);
		getContentPane().add(library);

		JLabel book = new JLabel("�ҽ����");
		book.setHorizontalAlignment(SwingConstants.CENTER);
		book.setForeground(SystemColor.textHighlight);
		book.setFont(new Font("΢���ź�", Font.BOLD, 20));
		book.setBounds(92, 320, 95, 28);
		getContentPane().add(book);

		JLabel myuserimg = new JLabel();
		myuserimg.setIcon(new ImageIcon("userimg\\user1.png"));
		myuserimg.setBounds(0, 81, 240, 70);
		getContentPane().add(myuserimg);

		JLabel updataimg = new JLabel();
		updataimg
				.setIcon(new ImageIcon("userimg\\updata1.png"));
		updataimg.setBounds(0, 153, 240, 70);
		getContentPane().add(updataimg);

		JLabel lbiraryimg = new JLabel();
		lbiraryimg
				.setIcon(new ImageIcon("userimg\\bookall.png"));
		lbiraryimg.setBounds(0, 225, 240, 70);
		getContentPane().add(lbiraryimg);

		JLabel bookimg = new JLabel();
		bookimg.setIcon(new ImageIcon("userimg\\mybook.png"));
		bookimg.setBounds(0, 297, 240, 70);
		getContentPane().add(bookimg);

		JLabel name = new JLabel("��ӭ��:" + UserServise.getName());
		name.setForeground(Color.WHITE);
		name.setFont(new Font("΢���ź� Light", Font.BOLD, 16));
		name.setBounds(956, 26, 202, 38);
		getContentPane().add(name);

		/************** ���������¼� ***************/
		myuser.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) { // ������¼�
				myuserimg.setIcon(new ImageIcon("userimg\\user.png"));
				updataimg.setIcon(new ImageIcon("userimg\\updata1.png"));
				lbiraryimg.setIcon(new ImageIcon("userimg\\bookall.png"));
				bookimg.setIcon(new ImageIcon("userimg\\mybook.png"));
				card.show(pane, "p1");
			}

			public void mouseEntered(MouseEvent e) { // ����ƶ���������¼�
				myuser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // ������ƶ���
																					// ���С��
				myuser.setForeground(Color.orange); // �ı���ɫ
			}

			public void mouseExited(MouseEvent e) { // ����뿪���¼�
				myuser.setForeground(SystemColor.textHighlight);
			}
		});

		updata.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) { // ������¼�
				myuserimg.setIcon(new ImageIcon("userimg\\user1.png"));
				updataimg.setIcon(new ImageIcon("userimg\\updata.png"));
				lbiraryimg.setIcon(new ImageIcon("userimg\\bookall.png"));
				bookimg.setIcon(new ImageIcon("userimg\\mybook.png"));
				card.show(pane, "p2");
			}

			public void mouseEntered(MouseEvent e) { // ����ƶ���������¼�
				updata.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // ������ƶ���
																					// ���С��
				updata.setForeground(Color.orange); // �ı���ɫ
			}

			public void mouseExited(MouseEvent e) { // ����뿪���¼�
				updata.setForeground(SystemColor.textHighlight);
			}
		});

		library.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) { // ������¼�
				myuserimg.setIcon(new ImageIcon("userimg\\user1.png"));
				updataimg.setIcon(new ImageIcon("userimg\\updata1.png"));
				lbiraryimg.setIcon(new ImageIcon("userimg\\bookall1.png"));
				bookimg.setIcon(new ImageIcon("userimg\\mybook.png"));
				card.show(pane, "p3");
			}

			public void mouseEntered(MouseEvent e) { // ����ƶ���������¼�
				library.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // ������ƶ���
																					// ���С��
				library.setForeground(Color.orange); // �ı���ɫ
			}

			public void mouseExited(MouseEvent e) { // ����뿪���¼�
				library.setForeground(SystemColor.textHighlight);
			}
		});

		book.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) { // ������¼�
				myuserimg.setIcon(new ImageIcon("userimg\\user1.png"));
				updataimg.setIcon(new ImageIcon("userimg\\updata1.png"));
				lbiraryimg.setIcon(new ImageIcon("userimg\\bookall.png"));
				bookimg.setIcon(new ImageIcon("userimg\\mybook1.png"));
				card.show(pane, "p4");
			}

			public void mouseEntered(MouseEvent e) { // ����ƶ���������¼�
				book.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // ������ƶ���
																				// ���С��
				book.setForeground(Color.orange); // �ı���ɫ
			}

			public void mouseExited(MouseEvent e) { // ����뿪���¼�
				book.setForeground(SystemColor.textHighlight);
			}
		});
	}

	/**
	 * �ҵĸ�����Ϣ���
	 */
	public void ClickEvent_Userpersonal() {
		JLabel name = new JLabel("�ҵ�������");
		name.setHorizontalAlignment(SwingConstants.LEFT);
		name.setForeground(new Color(0, 0, 0));
		name.setBounds(14, 117, 218, 53);
		p_1.add(name);
		name.setFont(new Font("΢���ź�", Font.BOLD, 20));
		JLabel name1 = new JLabel(UserServise.getName());
		name1.setHorizontalAlignment(SwingConstants.LEFT);
		name1.setForeground(Color.WHITE);
		name1.setBounds(14, 170, 218, 53);
		p_1.add(name1);
		name1.setFont(new Font("΢���ź�", Font.BOLD, 25));

		JLabel id = new JLabel("�ҵ�ѧ��:");
		id.setHorizontalAlignment(SwingConstants.LEFT);
		id.setForeground(new Color(0, 0, 0));
		id.setFont(new Font("΢���ź�", Font.BOLD, 20));
		id.setBounds(268, 117, 320, 53);
		p_1.add(id);
		JLabel id1 = new JLabel(UserServise.getId());
		id1.setHorizontalAlignment(SwingConstants.LEFT);
		id1.setForeground(Color.RED);
		id1.setBounds(268, 170, 218, 53);
		p_1.add(id1);
		id1.setFont(new Font("΢���ź�", Font.BOLD, 25));

		JLabel sex = new JLabel("null");
		sex.setHorizontalAlignment(SwingConstants.LEFT);
		sex.setForeground(new Color(0, 0, 0));
		sex.setFont(new Font("΢���ź�", Font.BOLD, 20));
		sex.setBounds(770, 117, 218, 53);
		if (UserServise.getSex().equals("��"))
			sex.setText("�ҿ���˧����");
		else
			sex.setText("�ҿ�����Ů��");
		p_1.add(sex);

		JLabel mynumer = new JLabel("�ҵ��û���:");
		mynumer.setHorizontalAlignment(SwingConstants.LEFT);
		mynumer.setForeground(new Color(0, 0, 0));
		mynumer.setFont(new Font("΢���ź�", Font.BOLD, 20));
		mynumer.setBounds(513, 117, 320, 53);
		p_1.add(mynumer);
		JLabel mynumer1 = new JLabel(UserServise.getNumer());
		mynumer1.setHorizontalAlignment(SwingConstants.LEFT);
		mynumer1.setForeground(Color.BLUE);
		mynumer1.setBounds(513, 170, 218, 53);
		p_1.add(mynumer1);
		mynumer1.setFont(new Font("΢���ź�", Font.BOLD, 25));
		/***************** ���ñ���ͼƬ ******************/
		ImageIcon background = new ImageIcon("userimg/my.png");// ����ͼƬ
		JLabel label = new JLabel(background);// �ѱ���ͼƬ��ʾ��һ����ǩ����
		// �ѱ�ǩ�Ĵ�Сλ
		// ������ΪͼƬ�պ�����������
		label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		// �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
		imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(null);
		this.getLayeredPane().setLayout(null);
		// �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����
		p_1.add(label, new Integer(Integer.MIN_VALUE));
	}

	public void ClickEvent_UserUpdata() {
		JRadioButton rdbtnUser = new JRadioButton("��", true);
		rdbtnUser.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		rdbtnUser.setForeground(Color.WHITE);
		rdbtnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// ����¼��������
				CheckSex = "��";
			}
		});

		rdbtnUser.setBounds(184, 475, 61, 27);
		p_2.add(rdbtnUser);

		JRadioButton rdbtnAdmin = new JRadioButton("Ů");
		rdbtnAdmin.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		rdbtnAdmin.setForeground(Color.WHITE);
		rdbtnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckSex = "Ů";
			}
		});
		rdbtnAdmin.setBounds(324, 475, 73, 27);
		p_2.add(rdbtnAdmin);
		ButtonGroup grout = new ButtonGroup();// ����һ���� ����������ϲ�Ϊ��
		grout.add(rdbtnAdmin);
		grout.add(rdbtnUser);
		rdbtnAdmin.setContentAreaFilled(false);// �������͸��
		rdbtnUser.setContentAreaFilled(false);

		JTextField textField_name = new JTextField();
		textField_name.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		textField_name.setColumns(10);
		textField_name.setBounds(269, 149, 208, 35);
		p_2.add(textField_name);

		JLabel label_name = new JLabel("����:");
		label_name.setForeground(Color.WHITE);
		label_name.setHorizontalAlignment(SwingConstants.CENTER);
		label_name.setFont(new Font("΢���ź� Light", Font.PLAIN, 20));
		label_name.setBounds(128, 147, 127, 35);
		p_2.add(label_name);

		JLabel label_1 = new JLabel("�Ա�:");
		label_1.setForeground(Color.WHITE);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("΢���ź� Light", Font.PLAIN, 20));
		label_1.setBounds(128, 405, 127, 35);
		p_2.add(label_1);

		JTextField textField_pwd = new JTextField();
		textField_pwd.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		textField_pwd.setColumns(10);
		textField_pwd.setBounds(269, 303, 208, 35);
		p_2.add(textField_pwd);

		JLabel lblPwd = new JLabel("����:");
		lblPwd.setForeground(Color.WHITE);
		lblPwd.setHorizontalAlignment(SwingConstants.CENTER);
		lblPwd.setFont(new Font("΢���ź� Light", Font.PLAIN, 20));
		lblPwd.setBounds(128, 303, 127, 35);
		p_2.add(lblPwd);

		JButton button = new JButton("ȷ��");
		button.setForeground(Color.BLACK);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // ���ȷ�ϵ���Ӧ�¼�����
				boolean nameis = false, pwdis = false, sexis = false;
				int i = JOptionPane.showConfirmDialog(null, "ȷ��������?", "��ʾ", JOptionPane.YES_NO_OPTION);
				if (i == 0) {
					String name = textField_name.getText();
					String pwd = textField_pwd.getText();
					String sex = CheckSex;
					if (name.length() != 0) {
						if (UserServise.updataUserName(UserServise.getId(), name))
							nameis = true;
						else
							nameis = false;
					}
					if (pwd.length() != 0) {
						if (pwd.matches("[a-zA-Z0-9]{6,15}"))
							if (UserServise.updataUserPassword(UserServise.getId(), pwd))
								pwdis = true;
							else
								pwdis = false;
						else
							JOptionPane.showMessageDialog(null, "������6-15λ���ֺ���ĸ��ɵ�����", "��ʾ", JOptionPane.ERROR_MESSAGE);
					}
					if (UserServise.updataUserSex(UserServise.getId(), sex))
						sexis = true;
					else
						sexis = false;

					if (nameis && pwdis && sexis)
						JOptionPane.showMessageDialog(null, "�ɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "ʧ��", "��ʾ", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		button.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		button.setBounds(616, 607, 183, 35);
		p_2.add(button);

		/***************** ���ñ���ͼƬ ******************/
		ImageIcon background = new ImageIcon("userimg/Updataimg.png");// ����ͼƬ
		JLabel label = new JLabel(background);// �ѱ���ͼƬ��ʾ��һ����ǩ����
		// �ѱ�ǩ�Ĵ�Сλ
		// ������ΪͼƬ�պ�����������
		label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		// �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
		imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(null);
		this.getLayeredPane().setLayout(null);
		// �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����
		p_2.add(label, new Integer(Integer.MIN_VALUE));
	}

	/**
	 * ��ʼ�� ͼ���б�����
	 */
	public void BookList() {
		bookListPanel = new JPanel();
		bookListPanel.setLayout(null);
		bookListPanel.setOpaque(false); // ����͸��

		bookList = new JScrollPane(bookListPanel);
		bookList.setBounds(101, 90, 755, 464);
		bookList.setOpaque(false); // ����͸��
		bookList.getViewport().setOpaque(false); // ����͸��
		bookListWidth = bookList.getWidth();
		// Ϊ�������ָ�� ��� �� ע������Ҫ��̬�仯��,������ӵ�����仯
		bookListPanel.setPreferredSize(new Dimension(bookListWidth, bookListPanel.getWidth()));
		p_3.add(bookList);

		/***************** ���ñ���ͼƬ ******************/
		ImageIcon background = new ImageIcon("userimg/bookimg.png");// ����ͼƬ
		JLabel label = new JLabel(background);// �ѱ���ͼƬ��ʾ��һ����ǩ����
		// �ѱ�ǩ�Ĵ�Сλ
		// ������ΪͼƬ�պ�����������
		label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		// �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
		imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(null);
		this.getLayeredPane().setLayout(null);
		// �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����
		p_3.add(label, new Integer(Integer.MIN_VALUE));
	}

	/**
	 * ���ͼʾ����ͼ���ͼƬ
	 * 
	 * @throws SQLException
	 */
	public void LoadBookList(String bookid, String bookname, String bookInfo, int x) {
		findsList.add(bookid + "-" + x);
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(bookimg);
		lblNewLabel.setBounds(x, 13, 268, 330);
		bookListPanel.add(lblNewLabel);

		JTextPane lblNewLabel_1 = new JTextPane();
		lblNewLabel_1.setFont(new Font("΢���ź�", Font.BOLD, 15));
		lblNewLabel_1.setText(bookInfo);
		lblNewLabel_1.setBounds(x, 342, 268, 133);
		lblNewLabel_1.setOpaque(false);
		bookListPanel.add(lblNewLabel_1);

		lblNewLabel.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) { // ������¼�
				if (e.getButton() == MouseEvent.BUTTON3) {// ������Ҽ�����Ļ�
					int i = JOptionPane.showConfirmDialog(null, "��Ҫ��-" + bookname + "-�Ȿ����", "��ʾ",
							JOptionPane.YES_NO_OPTION);
					if (i == 0) {
						if (BorrowAndReturnService.BookBorrow(bookid, UserServise.getId())){
							JOptionPane.showMessageDialog(null, "����ɹ����ú�ѧϰ��������", "��ʾ", JOptionPane.PLAIN_MESSAGE);		
							BorrowBookUpdataList();
						}
						else
							JOptionPane.showMessageDialog(null, "���������ϣ����Ѿ�������", "����", JOptionPane.ERROR_MESSAGE);
					}

				}
			}

		});
	}
	
	/**
	 * �����ݿ����ͼ����Ϣ����������ʾ
	 */
	public void getBook() {// x+424
		int x = 14;
		try {
			ResultSet rs = BookServise.FindsBookAll();
			;
			String bookname = null;
			String bookid = null;
			String bookAuthor = null;
			String bookPress = null;
			String bookType = null;
			while (rs.next()) {
				bookname = rs.getString(2);
				bookid = rs.getString(1);
				bookAuthor = rs.getString(3);
				bookPress = rs.getString(4);
				bookType = rs.getString(5);
				LoadBookList(bookid, bookname, "����: " + bookname + "\n" + "���:" + bookid + "\n" + "����: " + bookAuthor
						+ "\n" + "������: " + bookPress + "\n" + "����: " + bookType, x);
				bookListPanel.setPreferredSize(new Dimension(bookListWidth += 380, 342));
				x += 424;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����ͼ���Ԫ�����
	 */
	public void FindsBook() {
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBackground(SystemColor.inactiveCaption);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "��ѯ����", "���", "����", "����", "������", "����" }));
		comboBox.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		comboBox.setBounds(183, 566, 119, 32);
		p_3.add(comboBox);

		JTextField findsbook = new JTextField("-����-");
		findsbook.setHorizontalAlignment(SwingConstants.CENTER); // �����ı������ʽ ����
		findsbook.setBackground(SystemColor.inactiveCaption);
		findsbook.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		findsbook.setBounds(360, 567, 199, 30);
		findsbook.addMouseListener(new MouseAdapter() { // ������ı������Ӧ
			@Override
			public void mouseReleased(MouseEvent e) {
				if (findsbook.getText().equals("-����-")) {
					findsbook.setText("");
					findsbook.setForeground(Color.black);
					findsbook.setHorizontalAlignment(SwingConstants.LEFT); // �����ı������ʽ
																			// �����
					findsbook.setEditable(true);
				}
			}
		});

		p_3.addMouseListener(new MouseAdapter() { // �����ý������Ӧ
			@Override
			public void mousePressed(MouseEvent e) {
				findsbook.setText("-����-");
				findsbook.setForeground(Color.black);
				findsbook.setHorizontalAlignment(SwingConstants.CENTER); // �����ı������ʽ
																			// ����
				findsbook.setEditable(false);
			}
		});

		findsbook.addKeyListener(new KeyAdapter() {
			private ResultSet rs = null;
			private String finds;

			@Override
			public void keyReleased(KeyEvent e) { // ���ı��򽹵��� ������ϵ���س�����Ӧ
				if (e.getKeyChar() == '\n') {
					finds = findsbook.getText().toLowerCase();// ת��ΪСд ���Ƚ�
					if (finds.equals("-����-") == false && finds.length() != 0 && finds.equals("") == false) {
						String type = (String) comboBox.getSelectedItem(); // ��ȡ�鼮����

						if (comboBox.getSelectedIndex() != 0) {
							switch (type) {
							case "���":
								rs = BookServise.FindsBookID(finds);
								break;
							case "����":
								rs = BookServise.FindsBookName(finds);
								break;
							case "����":
								rs = BookServise.FindsBookAuthor(finds);
								break;
							case "������":
								rs = BookServise.FindsBookPress(finds);
								break;
							case "����":
								rs = BookServise.FindsBookType(finds);
								break;
							}
							if (rs != null)
								new QueryBookTable(rs);
						}

					}

				}
			}
		});

		p_3.add(findsbook);

	}
	
	/**
	 * ��Ӳ鿴�ҵĽ�����Ϣ���Ԫ�ص����
	 */
	public void SetMyBookList() {
		tabModel = new DefaultTableModel(){
			private static final long serialVersionUID = 3283588614879561211L;
			public boolean isCellEditable(int row, int column)	//����Ϊ���ɱ༭״̬��table �������ܹ�ѡ��
	            {
	                return false;
	            }
		};

		MyBookTabelData();
		/***************** ���ñ���ͼƬ ******************/
		ImageIcon background = new ImageIcon("userimg/MyBookList.png");// ����ͼƬ
		JLabel label = new JLabel(background);// �ѱ���ͼƬ��ʾ��һ����ǩ����
		// �ѱ�ǩ�Ĵ�Сλ
		// ������ΪͼƬ�պ�����������
		label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		// �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
		imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(null);
		this.getLayeredPane().setLayout(null);
		// �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����
		p_4.add(label, new Integer(Integer.MIN_VALUE));
	}

	/**
	 * ���ñ��Ԫ�ص�ֵ
	 */
	public void MyBookTabelData() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(80, 274, 805, 403);
		scrollPane.setOpaque(false); // ����͸��
		scrollPane.getViewport().setOpaque(false); // ����͸��
		p_4.add(scrollPane);

		rowData = new Vector<Serializable>();
		columnNames = new Vector<Serializable>();
		// ��������
		columnNames.add("���");
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("������");
		columnNames.add("����");
		columnNames.add("���ʱ��");

		String book[][] = UserServise.BookDataByUserID(UserServise.getId());
		for (int i = 0; i < book.length; i++) {
			Vector<Serializable> hang = new Vector<Serializable>();
			hang.add(book[i][0]);
			hang.add(book[i][1]);
			hang.add(book[i][2]);
			hang.add(book[i][3]);
			hang.add(book[i][4]);
			hang.add(book[i][5]);
			rowData.add(hang);
			count++;			
		}
				
		tabModel.setDataVector(rowData, columnNames);// ��һ���ǹؼ�
		tabModel.isCellEditable(0, 0);
		
		myBookTabel = new JTable(tabModel);	
		scrollPane.setViewportView(myBookTabel);
		myBookTabel.setRowHeight(25);
		myBookTabel.setBackground(new Color(39, 46, 66));
		myBookTabel.setForeground(Color.WHITE);
		myBookTabel.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		myBookTabel.setGridColor(Color.black);

		JTableHeader tableH = myBookTabel.getTableHeader();
		tableH.setBackground(new Color(47, 213, 249));
		tableH.setFont(new Font("΢���ź� Light", Font.PLAIN, 15));

		hasbook = new JLabel(count + "��");
		hasbook.setForeground(Color.WHITE);
		hasbook.setFont(new Font("΢���ź�", Font.PLAIN, 30));
		hasbook.setBounds(125, 105, 60, 34);
		p_4.add(hasbook);

		
		maxbook = new JLabel(myBookSize+ "��");
		maxbook.setForeground(Color.WHITE);
		maxbook.setFont(new Font("΢���ź�", Font.PLAIN, 30));
		maxbook.setBounds(423, 105, 70, 34);
		p_4.add(maxbook);
		
		credit = new JLabel("100");
		credit.setForeground(Color.WHITE);
		credit.setFont(new Font("΢���ź�", Font.PLAIN, 30));
		credit.setBounds(742, 105, 80, 34);
		p_4.add(credit);

		myBookTabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {// ������Ҽ�����Ļ�
					popupMenu1.show(myBookTabel, e.getX(), e.getY());
				}
			}
		});
	}
	
	/**
	 * ʹ�ý�������Ĺ��� ���и����б�ķ���
	 */
	public void BorrowBookUpdataList(){
		count=0;
		rowData.clear();
		myBookTabel.removeAll();		
		String book[][] = UserServise.BookDataByUserID(UserServise.getId());
		for (int k = 0; k < book.length; k++) {
			Vector<Serializable> hang = new Vector<Serializable>();
			hang.add(book[k][0]);
			hang.add(book[k][1]);
			hang.add(book[k][2]);
			hang.add(book[k][3]);
			hang.add(book[k][4]);
			hang.add(book[k][5]);
			rowData.add(hang);
			count++;			
		}						
		tabModel.setDataVector(rowData, columnNames);// ��һ���ǹؼ�
		hasbook.setText(count + "��");
		maxbook.setText(--myBookSize + "��");
	}
	
	/**
	 * �Ҽ��˵���������
	 */
	public void MouseEventButtonListener() {
		popupMenu1 = new PopupMenu(); // �����˵�����
		MenuItem menuItem1 = new MenuItem(); // ����Ӳ˵�
		menuItem1.setLabel("��Ҫ����"); // �涨����
		popupMenu1.add(menuItem1); // ��ӵ��˵�������
		getContentPane().add(popupMenu1); // ��ӵ�������
		menuItem1.addActionListener(new ActionListener() { // �˵�1���¼�����
			public void actionPerformed(ActionEvent e) {
				int setSelect[] = myBookTabel.getSelectedRows(); // ��ȡѡ�е��б���
																	// ��������
				String bookid = null;
				for (int i : setSelect) {
					bookid = (String) myBookTabel.getValueAt(i, 0);
					BorrowAndReturnService.Bookreturn(bookid, UserServise.getId());
				}
				maxbook.setText(UserServise.getMaxBook() + "��");
				
				int rowcount = tabModel.getRowCount() - 1;
				if (rowcount >= 0) {
					for (int k = setSelect.length - 1; k >= 0; k--) {
						tabModel.removeRow(setSelect[k]);
						hasbook.setText(--count + "��");			//ˢ�±���ͳ��
						tabModel.setRowCount(rowcount);
					}			
				}
				maxbook.setText(++myBookSize+ "��");
				myBookTabel.revalidate();//ˢ�±��

			}
		});
	}


/******************************************************************************************/
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
				if (e.getButton() == MouseEvent.BUTTON3) {// ������Ҽ�����Ļ�
					popupMenu1.show(jt, e.getX(), e.getY());
				}
			}
		});
		jsp = new JScrollPane(jt);
		this.add(jsp);
		this.setSize(600, 300);
		setLocationRelativeTo(null);// �ô��ھ�����ʾ
		setResizable(false);// ���ô�����󻯰�ť
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

	/**
	 * �Ҽ��˵������ �Ͳ˵��ļ�����Ӧ ����
	 */
	public void MouseEventButtonListener() {
		popupMenu1 = new PopupMenu(); // �����˵�����
		MenuItem menuItem1 = new MenuItem(); // ����Ӳ˵�
		menuItem1.setLabel("��������"); // �涨����
		popupMenu1.add(menuItem1); // ��ӵ��˵�������
		getContentPane().add(popupMenu1); // ��ӵ�������
		menuItem1.addActionListener(new ActionListener() { // �˵�1���¼�����
			public void actionPerformed(ActionEvent e) {
				int i = jt.getSelectedRow();
				String bookid = (String) jt.getValueAt(i, 0);
				String bookname = (String) jt.getValueAt(i, 1);
				int k = JOptionPane.showConfirmDialog(null, "��Ҫ��-" + bookname + "-�Ȿ����?", "��ʾ",
						JOptionPane.YES_NO_OPTION);
				if (k == 0) {
					if (BorrowAndReturnService.BookBorrow(bookid, UserServise.getId())){
						JOptionPane.showMessageDialog(null, "����ɹ����ú�ѧϰ��������!!", "��ʾ", JOptionPane.PLAIN_MESSAGE);
						BorrowBookUpdataList();
					}
					else
						JOptionPane.showMessageDialog(null, "���������ϣ����Ѿ�������!!", "����", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

}
}