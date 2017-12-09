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
 * 图书管理系统 2.0 Mysql 数据库版
 * 用户类
 * @author PuaChen
 */
public class Library extends JFrame {
	private static final long serialVersionUID = -1169720619929459438L;
	private JPanel imagePanel;
	private ImageIcon background;
	private JPanel pane = null; // 主要的JPanel
	private CardLayout card = null; // CardLayout布局管理器
	private JPanel p_1 = null, p_2 = null, p_3 = null, p_4 = null;
	private String CheckSex;	//判断更新用户选择的性别
	private int bookListWidth;	
	private JScrollPane bookList;
	private JPanel bookListPanel;
	private ImageIcon bookimg = new ImageIcon("userimg/BookDefault.png");// 背景图片
	private ArrayList<String> findsList = new ArrayList<String>();
	private PopupMenu popupMenu1;
	private JTable myBookTabel;
	private Vector<Serializable> rowData,columnNames;
	private DefaultTableModel tabModel;
	private int count = 0;
	private JLabel hasbook,maxbook,credit;
	private int myBookSize=Integer.parseInt(UserServise.getMaxBook());	//获取用户还能借多少书
	


	public Library() {		
		setBounds(100, 100, 1208, 840);
		setTitle("Library图书馆       User(V2.0)");
		setLocationRelativeTo(null);// 让窗口居中显示
		setResizable(false); // 禁用窗口最大化按钮
		setImg();
		CardPanle();
		SetLabelBtn();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				DataBase.Colse();//关闭数据库连接
				System.exit(0);
			}
		});
		setVisible(true);
	}

	/**
	 * 加载背景图片
	 */
	public void setImg() {
		background = new ImageIcon("userimg/UserUi.png");// 背景图片
		JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
		// 把标签的大小位
		// 置设置为图片刚好填充整个面板
		label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(null);
		this.getLayeredPane().setLayout(null);
		// 把背景图片添加到分层窗格的最底层作为背景
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
	}

	/**
	 * 注册卡片布局框架
	 */
	public void CardPanle() {
		card = new CardLayout(5, 5);
		pane = new JPanel(card); // JPanel的布局管理将被设置成CardLayout
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
		JLabel myuser = new JLabel("我的信息");
		myuser.setHorizontalAlignment(SwingConstants.CENTER);
		myuser.setForeground(SystemColor.textHighlight);
		myuser.setFont(new Font("微软雅黑", Font.BOLD, 20));
		myuser.setBounds(92, 105, 95, 28);
		getContentPane().add(myuser);

		JLabel updata = new JLabel("更新我的信息");
		updata.setHorizontalAlignment(SwingConstants.CENTER);
		updata.setForeground(SystemColor.textHighlight);
		updata.setFont(new Font("微软雅黑", Font.BOLD, 20));
		updata.setBounds(92, 177, 125, 28);
		getContentPane().add(updata);

		JLabel library = new JLabel("图书馆");
		library.setHorizontalAlignment(SwingConstants.CENTER);
		library.setForeground(SystemColor.textHighlight);
		library.setFont(new Font("微软雅黑", Font.BOLD, 20));
		library.setBounds(92, 251, 95, 28);
		getContentPane().add(library);

		JLabel book = new JLabel("我借的书");
		book.setHorizontalAlignment(SwingConstants.CENTER);
		book.setForeground(SystemColor.textHighlight);
		book.setFont(new Font("微软雅黑", Font.BOLD, 20));
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

		JLabel name = new JLabel("欢迎您:" + UserServise.getName());
		name.setForeground(Color.WHITE);
		name.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
		name.setBounds(956, 26, 202, 38);
		getContentPane().add(name);

		/************** 按键监听事件 ***************/
		myuser.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) { // 鼠标点击事件
				myuserimg.setIcon(new ImageIcon("userimg\\user.png"));
				updataimg.setIcon(new ImageIcon("userimg\\updata1.png"));
				lbiraryimg.setIcon(new ImageIcon("userimg\\bookall.png"));
				bookimg.setIcon(new ImageIcon("userimg\\mybook.png"));
				card.show(pane, "p1");
			}

			public void mouseEntered(MouseEvent e) { // 鼠标移动到这里的事件
				myuser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 让鼠标移动到
																					// 变成小手
				myuser.setForeground(Color.orange); // 改变颜色
			}

			public void mouseExited(MouseEvent e) { // 鼠标离开的事件
				myuser.setForeground(SystemColor.textHighlight);
			}
		});

		updata.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) { // 鼠标点击事件
				myuserimg.setIcon(new ImageIcon("userimg\\user1.png"));
				updataimg.setIcon(new ImageIcon("userimg\\updata.png"));
				lbiraryimg.setIcon(new ImageIcon("userimg\\bookall.png"));
				bookimg.setIcon(new ImageIcon("userimg\\mybook.png"));
				card.show(pane, "p2");
			}

			public void mouseEntered(MouseEvent e) { // 鼠标移动到这里的事件
				updata.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 让鼠标移动到
																					// 变成小手
				updata.setForeground(Color.orange); // 改变颜色
			}

			public void mouseExited(MouseEvent e) { // 鼠标离开的事件
				updata.setForeground(SystemColor.textHighlight);
			}
		});

		library.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) { // 鼠标点击事件
				myuserimg.setIcon(new ImageIcon("userimg\\user1.png"));
				updataimg.setIcon(new ImageIcon("userimg\\updata1.png"));
				lbiraryimg.setIcon(new ImageIcon("userimg\\bookall1.png"));
				bookimg.setIcon(new ImageIcon("userimg\\mybook.png"));
				card.show(pane, "p3");
			}

			public void mouseEntered(MouseEvent e) { // 鼠标移动到这里的事件
				library.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 让鼠标移动到
																					// 变成小手
				library.setForeground(Color.orange); // 改变颜色
			}

			public void mouseExited(MouseEvent e) { // 鼠标离开的事件
				library.setForeground(SystemColor.textHighlight);
			}
		});

		book.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) { // 鼠标点击事件
				myuserimg.setIcon(new ImageIcon("userimg\\user1.png"));
				updataimg.setIcon(new ImageIcon("userimg\\updata1.png"));
				lbiraryimg.setIcon(new ImageIcon("userimg\\bookall.png"));
				bookimg.setIcon(new ImageIcon("userimg\\mybook1.png"));
				card.show(pane, "p4");
			}

			public void mouseEntered(MouseEvent e) { // 鼠标移动到这里的事件
				book.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 让鼠标移动到
																				// 变成小手
				book.setForeground(Color.orange); // 改变颜色
			}

			public void mouseExited(MouseEvent e) { // 鼠标离开的事件
				book.setForeground(SystemColor.textHighlight);
			}
		});
	}

	/**
	 * 我的个人信息面板
	 */
	public void ClickEvent_Userpersonal() {
		JLabel name = new JLabel("我的姓名：");
		name.setHorizontalAlignment(SwingConstants.LEFT);
		name.setForeground(new Color(0, 0, 0));
		name.setBounds(14, 117, 218, 53);
		p_1.add(name);
		name.setFont(new Font("微软雅黑", Font.BOLD, 20));
		JLabel name1 = new JLabel(UserServise.getName());
		name1.setHorizontalAlignment(SwingConstants.LEFT);
		name1.setForeground(Color.WHITE);
		name1.setBounds(14, 170, 218, 53);
		p_1.add(name1);
		name1.setFont(new Font("微软雅黑", Font.BOLD, 25));

		JLabel id = new JLabel("我的学号:");
		id.setHorizontalAlignment(SwingConstants.LEFT);
		id.setForeground(new Color(0, 0, 0));
		id.setFont(new Font("微软雅黑", Font.BOLD, 20));
		id.setBounds(268, 117, 320, 53);
		p_1.add(id);
		JLabel id1 = new JLabel(UserServise.getId());
		id1.setHorizontalAlignment(SwingConstants.LEFT);
		id1.setForeground(Color.RED);
		id1.setBounds(268, 170, 218, 53);
		p_1.add(id1);
		id1.setFont(new Font("微软雅黑", Font.BOLD, 25));

		JLabel sex = new JLabel("null");
		sex.setHorizontalAlignment(SwingConstants.LEFT);
		sex.setForeground(new Color(0, 0, 0));
		sex.setFont(new Font("微软雅黑", Font.BOLD, 20));
		sex.setBounds(770, 117, 218, 53);
		if (UserServise.getSex().equals("男"))
			sex.setText("我可是帅哥呢");
		else
			sex.setText("我可是美女呢");
		p_1.add(sex);

		JLabel mynumer = new JLabel("我的用户名:");
		mynumer.setHorizontalAlignment(SwingConstants.LEFT);
		mynumer.setForeground(new Color(0, 0, 0));
		mynumer.setFont(new Font("微软雅黑", Font.BOLD, 20));
		mynumer.setBounds(513, 117, 320, 53);
		p_1.add(mynumer);
		JLabel mynumer1 = new JLabel(UserServise.getNumer());
		mynumer1.setHorizontalAlignment(SwingConstants.LEFT);
		mynumer1.setForeground(Color.BLUE);
		mynumer1.setBounds(513, 170, 218, 53);
		p_1.add(mynumer1);
		mynumer1.setFont(new Font("微软雅黑", Font.BOLD, 25));
		/***************** 设置背景图片 ******************/
		ImageIcon background = new ImageIcon("userimg/my.png");// 背景图片
		JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
		// 把标签的大小位
		// 置设置为图片刚好填充整个面板
		label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(null);
		this.getLayeredPane().setLayout(null);
		// 把背景图片添加到分层窗格的最底层作为背景
		p_1.add(label, new Integer(Integer.MIN_VALUE));
	}

	public void ClickEvent_UserUpdata() {
		JRadioButton rdbtnUser = new JRadioButton("男", true);
		rdbtnUser.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		rdbtnUser.setForeground(Color.WHITE);
		rdbtnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 点击事件处理机制
				CheckSex = "男";
			}
		});

		rdbtnUser.setBounds(184, 475, 61, 27);
		p_2.add(rdbtnUser);

		JRadioButton rdbtnAdmin = new JRadioButton("女");
		rdbtnAdmin.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		rdbtnAdmin.setForeground(Color.WHITE);
		rdbtnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckSex = "女";
			}
		});
		rdbtnAdmin.setBounds(324, 475, 73, 27);
		p_2.add(rdbtnAdmin);
		ButtonGroup grout = new ButtonGroup();// 创建一个组 把两个对象合并为组
		grout.add(rdbtnAdmin);
		grout.add(rdbtnUser);
		rdbtnAdmin.setContentAreaFilled(false);// 设置组件透明
		rdbtnUser.setContentAreaFilled(false);

		JTextField textField_name = new JTextField();
		textField_name.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField_name.setColumns(10);
		textField_name.setBounds(269, 149, 208, 35);
		p_2.add(textField_name);

		JLabel label_name = new JLabel("姓名:");
		label_name.setForeground(Color.WHITE);
		label_name.setHorizontalAlignment(SwingConstants.CENTER);
		label_name.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		label_name.setBounds(128, 147, 127, 35);
		p_2.add(label_name);

		JLabel label_1 = new JLabel("性别:");
		label_1.setForeground(Color.WHITE);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		label_1.setBounds(128, 405, 127, 35);
		p_2.add(label_1);

		JTextField textField_pwd = new JTextField();
		textField_pwd.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		textField_pwd.setColumns(10);
		textField_pwd.setBounds(269, 303, 208, 35);
		p_2.add(textField_pwd);

		JLabel lblPwd = new JLabel("密码:");
		lblPwd.setForeground(Color.WHITE);
		lblPwd.setHorizontalAlignment(SwingConstants.CENTER);
		lblPwd.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		lblPwd.setBounds(128, 303, 127, 35);
		p_2.add(lblPwd);

		JButton button = new JButton("确认");
		button.setForeground(Color.BLACK);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 点击确认的响应事件处理
				boolean nameis = false, pwdis = false, sexis = false;
				int i = JOptionPane.showConfirmDialog(null, "确定更改吗?", "提示", JOptionPane.YES_NO_OPTION);
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
							JOptionPane.showMessageDialog(null, "请输入6-15位数字和字母组成的密码", "提示", JOptionPane.ERROR_MESSAGE);
					}
					if (UserServise.updataUserSex(UserServise.getId(), sex))
						sexis = true;
					else
						sexis = false;

					if (nameis && pwdis && sexis)
						JOptionPane.showMessageDialog(null, "成功", "提示", JOptionPane.PLAIN_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "失败", "提示", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button.setBounds(616, 607, 183, 35);
		p_2.add(button);

		/***************** 设置背景图片 ******************/
		ImageIcon background = new ImageIcon("userimg/Updataimg.png");// 背景图片
		JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
		// 把标签的大小位
		// 置设置为图片刚好填充整个面板
		label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(null);
		this.getLayeredPane().setLayout(null);
		// 把背景图片添加到分层窗格的最底层作为背景
		p_2.add(label, new Integer(Integer.MIN_VALUE));
	}

	/**
	 * 初始化 图书列表的组件
	 */
	public void BookList() {
		bookListPanel = new JPanel();
		bookListPanel.setLayout(null);
		bookListPanel.setOpaque(false); // 设置透明

		bookList = new JScrollPane(bookListPanel);
		bookList.setBounds(101, 90, 755, 464);
		bookList.setOpaque(false); // 设置透明
		bookList.getViewport().setOpaque(false); // 设置透明
		bookListWidth = bookList.getWidth();
		// 为这个容器指定 宽和 高 注：高需要动态变化的,根据添加的组件变化
		bookListPanel.setPreferredSize(new Dimension(bookListWidth, bookListPanel.getWidth()));
		p_3.add(bookList);

		/***************** 设置背景图片 ******************/
		ImageIcon background = new ImageIcon("userimg/bookimg.png");// 背景图片
		JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
		// 把标签的大小位
		// 置设置为图片刚好填充整个面板
		label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(null);
		this.getLayeredPane().setLayout(null);
		// 把背景图片添加到分层窗格的最底层作为背景
		p_3.add(label, new Integer(Integer.MIN_VALUE));
	}

	/**
	 * 添加图示设置图书的图片
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
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel_1.setText(bookInfo);
		lblNewLabel_1.setBounds(x, 342, 268, 133);
		lblNewLabel_1.setOpaque(false);
		bookListPanel.add(lblNewLabel_1);

		lblNewLabel.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) { // 鼠标点击事件
				if (e.getButton() == MouseEvent.BUTTON3) {// 如果是右键点击的话
					int i = JOptionPane.showConfirmDialog(null, "你要借-" + bookname + "-这本书吗", "提示",
							JOptionPane.YES_NO_OPTION);
					if (i == 0) {
						if (BorrowAndReturnService.BookBorrow(bookid, UserServise.getId())){
							JOptionPane.showMessageDialog(null, "借书成功，好好学习天天向上", "提示", JOptionPane.PLAIN_MESSAGE);		
							BorrowBookUpdataList();
						}
						else
							JOptionPane.showMessageDialog(null, "你来晚了呦，书已经被借了", "错误", JOptionPane.ERROR_MESSAGE);
					}

				}
			}

		});
	}
	
	/**
	 * 从数据库添加图书信息到窗口上显示
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
				LoadBookList(bookid, bookname, "书名: " + bookname + "\n" + "书号:" + bookid + "\n" + "作者: " + bookAuthor
						+ "\n" + "出版社: " + bookPress + "\n" + "类型: " + bookType, x);
				bookListPanel.setPreferredSize(new Dimension(bookListWidth += 380, 342));
				x += 424;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 搜索图书的元素组件
	 */
	public void FindsBook() {
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBackground(SystemColor.inactiveCaption);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "查询类型", "书号", "书名", "作者", "出版社", "类型" }));
		comboBox.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		comboBox.setBounds(183, 566, 119, 32);
		p_3.add(comboBox);

		JTextField findsbook = new JTextField("-搜索-");
		findsbook.setHorizontalAlignment(SwingConstants.CENTER); // 设置文本段落格式 居中
		findsbook.setBackground(SystemColor.inactiveCaption);
		findsbook.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		findsbook.setBounds(360, 567, 199, 30);
		findsbook.addMouseListener(new MouseAdapter() { // 鼠标点击文本框的响应
			@Override
			public void mouseReleased(MouseEvent e) {
				if (findsbook.getText().equals("-搜索-")) {
					findsbook.setText("");
					findsbook.setForeground(Color.black);
					findsbook.setHorizontalAlignment(SwingConstants.LEFT); // 设置文本段落格式
																			// 左对齐
					findsbook.setEditable(true);
				}
			}
		});

		p_3.addMouseListener(new MouseAdapter() { // 窗体获得焦点的响应
			@Override
			public void mousePressed(MouseEvent e) {
				findsbook.setText("-搜索-");
				findsbook.setForeground(Color.black);
				findsbook.setHorizontalAlignment(SwingConstants.CENTER); // 设置文本段落格式
																			// 居中
				findsbook.setEditable(false);
			}
		});

		findsbook.addKeyListener(new KeyAdapter() {
			private ResultSet rs = null;
			private String finds;

			@Override
			public void keyReleased(KeyEvent e) { // 在文本框焦点上 输入完毕点击回车的响应
				if (e.getKeyChar() == '\n') {
					finds = findsbook.getText().toLowerCase();// 转化为小写 来比较
					if (finds.equals("-搜索-") == false && finds.length() != 0 && finds.equals("") == false) {
						String type = (String) comboBox.getSelectedItem(); // 获取书籍类型

						if (comboBox.getSelectedIndex() != 0) {
							switch (type) {
							case "书号":
								rs = BookServise.FindsBookID(finds);
								break;
							case "书名":
								rs = BookServise.FindsBookName(finds);
								break;
							case "作者":
								rs = BookServise.FindsBookAuthor(finds);
								break;
							case "出版社":
								rs = BookServise.FindsBookPress(finds);
								break;
							case "类型":
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
	 * 添加查看我的借书信息表格元素的组件
	 */
	public void SetMyBookList() {
		tabModel = new DefaultTableModel(){
			private static final long serialVersionUID = 3283588614879561211L;
			public boolean isCellEditable(int row, int column)	//设置为不可编辑状态的table ，但是能够选中
	            {
	                return false;
	            }
		};

		MyBookTabelData();
		/***************** 设置背景图片 ******************/
		ImageIcon background = new ImageIcon("userimg/MyBookList.png");// 背景图片
		JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
		// 把标签的大小位
		// 置设置为图片刚好填充整个面板
		label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(null);
		this.getLayeredPane().setLayout(null);
		// 把背景图片添加到分层窗格的最底层作为背景
		p_4.add(label, new Integer(Integer.MIN_VALUE));
	}

	/**
	 * 设置表格元素的值
	 */
	public void MyBookTabelData() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(80, 274, 805, 403);
		scrollPane.setOpaque(false); // 设置透明
		scrollPane.getViewport().setOpaque(false); // 设置透明
		p_4.add(scrollPane);

		rowData = new Vector<Serializable>();
		columnNames = new Vector<Serializable>();
		// 设置列名
		columnNames.add("书号");
		columnNames.add("书名");
		columnNames.add("作者");
		columnNames.add("出版社");
		columnNames.add("类型");
		columnNames.add("借出时间");

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
				
		tabModel.setDataVector(rowData, columnNames);// 这一行是关键
		tabModel.isCellEditable(0, 0);
		
		myBookTabel = new JTable(tabModel);	
		scrollPane.setViewportView(myBookTabel);
		myBookTabel.setRowHeight(25);
		myBookTabel.setBackground(new Color(39, 46, 66));
		myBookTabel.setForeground(Color.WHITE);
		myBookTabel.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		myBookTabel.setGridColor(Color.black);

		JTableHeader tableH = myBookTabel.getTableHeader();
		tableH.setBackground(new Color(47, 213, 249));
		tableH.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));

		hasbook = new JLabel(count + "本");
		hasbook.setForeground(Color.WHITE);
		hasbook.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		hasbook.setBounds(125, 105, 60, 34);
		p_4.add(hasbook);

		
		maxbook = new JLabel(myBookSize+ "本");
		maxbook.setForeground(Color.WHITE);
		maxbook.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		maxbook.setBounds(423, 105, 70, 34);
		p_4.add(maxbook);
		
		credit = new JLabel("100");
		credit.setForeground(Color.WHITE);
		credit.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		credit.setBounds(742, 105, 80, 34);
		p_4.add(credit);

		myBookTabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {// 如果是右键点击的话
					popupMenu1.show(myBookTabel, e.getX(), e.getY());
				}
			}
		});
	}
	
	/**
	 * 使用借书操作的功能 进行更新列表的方法
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
		tabModel.setDataVector(rowData, columnNames);// 这一行是关键
		hasbook.setText(count + "本");
		maxbook.setText(--myBookSize + "本");
	}
	
	/**
	 * 右键菜单的组件组件
	 */
	public void MouseEventButtonListener() {
		popupMenu1 = new PopupMenu(); // 创建菜单对象
		MenuItem menuItem1 = new MenuItem(); // 添加子菜单
		menuItem1.setLabel("我要还书"); // 规定名称
		popupMenu1.add(menuItem1); // 添加到菜单对象中
		getContentPane().add(popupMenu1); // 添加到窗体上
		menuItem1.addActionListener(new ActionListener() { // 菜单1的事件监听
			public void actionPerformed(ActionEvent e) {
				int setSelect[] = myBookTabel.getSelectedRows(); // 获取选中的列表项
																	// 升序排列
				String bookid = null;
				for (int i : setSelect) {
					bookid = (String) myBookTabel.getValueAt(i, 0);
					BorrowAndReturnService.Bookreturn(bookid, UserServise.getId());
				}
				maxbook.setText(UserServise.getMaxBook() + "本");
				
				int rowcount = tabModel.getRowCount() - 1;
				if (rowcount >= 0) {
					for (int k = setSelect.length - 1; k >= 0; k--) {
						tabModel.removeRow(setSelect[k]);
						hasbook.setText(--count + "本");			//刷新本书统计
						tabModel.setRowCount(rowcount);
					}			
				}
				maxbook.setText(++myBookSize+ "本");
				myBookTabel.revalidate();//刷新表格

			}
		});
	}


/******************************************************************************************/
class QueryBookTable extends JFrame {

	private static final long serialVersionUID = 8456094523646445002L;
	// 从数据库中取出信息
	// rowData用来存放行数据
	// columnNames存放列名
	private JScrollPane jsp;
	private ResultSet rs;
	private Vector<Serializable> rowData, columnNames;
	private PopupMenu popupMenu1;
	private JTable jt;

	// 构造函数
	public QueryBookTable(ResultSet rs) {
		columnNames = new Vector<Serializable>();
		columnNames.add("书号");
		columnNames.add("书名");
		columnNames.add("作者");
		columnNames.add("出版社");
		columnNames.add("类型");
		columnNames.add("时间");
		this.rs = rs;
		rowData = gethang();
		jt = new JTable(rowData, columnNames);
		MouseEventButtonListener();
		jt.setRowHeight(20);
		jt.setFont(new Font("微软雅黑", Font.PLAIN, 16));

		jt.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {// 如果是右键点击的话
					popupMenu1.show(jt, e.getX(), e.getY());
				}
			}
		});
		jsp = new JScrollPane(jt);
		this.add(jsp);
		this.setSize(600, 300);
		setLocationRelativeTo(null);// 让窗口居中显示
		setResizable(false);// 禁用窗口最大化按钮
		setLocationRelativeTo(null);
		setTitle("查询结果");
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
	 * 右键菜单的添加 和菜单的监听响应 方法
	 */
	public void MouseEventButtonListener() {
		popupMenu1 = new PopupMenu(); // 创建菜单对象
		MenuItem menuItem1 = new MenuItem(); // 添加子菜单
		menuItem1.setLabel("借来看看"); // 规定名称
		popupMenu1.add(menuItem1); // 添加到菜单对象中
		getContentPane().add(popupMenu1); // 添加到窗体上
		menuItem1.addActionListener(new ActionListener() { // 菜单1的事件监听
			public void actionPerformed(ActionEvent e) {
				int i = jt.getSelectedRow();
				String bookid = (String) jt.getValueAt(i, 0);
				String bookname = (String) jt.getValueAt(i, 1);
				int k = JOptionPane.showConfirmDialog(null, "你要借-" + bookname + "-这本书吗?", "提示",
						JOptionPane.YES_NO_OPTION);
				if (k == 0) {
					if (BorrowAndReturnService.BookBorrow(bookid, UserServise.getId())){
						JOptionPane.showMessageDialog(null, "借书成功，好好学习天天向上!!", "提示", JOptionPane.PLAIN_MESSAGE);
						BorrowBookUpdataList();
					}
					else
						JOptionPane.showMessageDialog(null, "你来晚了呦，书已经被借了!!", "错误", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

}
}