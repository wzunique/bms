package com.Library.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import com.Library.DataBase.DataBase;
import com.Library.Service.AdminServise;
import com.Library.Service.UserServise;

/**
 * ͼ�����ϵͳ�û���¼����
 * @author PuaChen
 */
public class LoginWindow extends JFrame {
private static final long serialVersionUID = -3331349633487502332L;
	 private JPanel imagePanel;
	 private ImageIcon background;
	 private JTextField text_numer;
	 private JPasswordField text_password;
	 private int Checkuser=0; //�����ж� ѡ����û����    0����user  1����admin;
	
	


	public LoginWindow() {
		setTitle("ͼ�����ݹ���ϵͳ��V2.0");
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				DataBase.Colse();//�ر����ݿ�����
				System.exit(0);
			}
			
		});
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);//�ô��ھ�����ʾ
		setResizable(false);//���ô�����󻯰�ť
		setImg();  //����ͼƬ
		addRdbtn();  //���ص�ѡ��ť��
		ButtonAndLabelSet(); //���ذ�ť�ͱ�ǩ
		//Icon i=new ImageIcon("images/01.png");//���ð�ť�ı�����ɫ	
		setVisible(true);
	}
	
	public void ButtonAndLabelSet(){
		JButton btnOK = new JButton("��¼");
		btnOK.setFont(new Font("΢���ź�", Font.BOLD, 16));
		btnOK.setForeground(Color.WHITE);
		btnOK.setContentAreaFilled(false);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //����¼�
				String numer=text_numer.getText();
				String pwd=String.valueOf(text_password.getPassword());
				if(numer.length()!=0 && pwd.length()!=0){
					
				if(Checkuser==0){//USER
					if(UserServise.UserCheck(numer, pwd)){
						new Library();
						dispose();
				}else
					JOptionPane.showMessageDialog(null, "������˻�������", "��ʾ", JOptionPane.ERROR_MESSAGE); 

					}
				else if(Checkuser==1){//ADMIN
					if(AdminServise.AdminCheck(numer, pwd)){
						new ManagementWindow();
						dispose();
					}else
						JOptionPane.showMessageDialog(null, "������˻�������", "��ʾ", JOptionPane.ERROR_MESSAGE); 

				}
			}else
				JOptionPane.showMessageDialog(null, "����������", "��ʾ", JOptionPane.ERROR_MESSAGE); 

			}
		});
		btnOK.setBounds(360, 455, 100, 25);
		getContentPane().add(btnOK);
		
		text_numer = new JTextField();
		text_numer.setForeground(Color.WHITE);
		text_numer.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		text_numer.setBounds(339, 352, 153, 24);
		getContentPane().add(text_numer);
		text_numer.setColumns(10);
		text_numer.setOpaque(false);  //���������͸��
		
		text_password = new JPasswordField();
		text_password.setForeground(Color.WHITE);
		text_password.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		text_password.setBounds(339, 391, 153, 24);
		getContentPane().add(text_password);
		text_password.setColumns(10);
		text_password.setOpaque(false);
		text_password.addKeyListener(new KeyAdapter() {
		     	@Override
		     	public void keyReleased(KeyEvent e) {   //���ı��򽹵��� ������ϵ���س�����Ӧ		 
		     		if(e.getKeyChar()=='\n'){
		     			String numer=text_numer.getText();
						String pwd=String.valueOf(text_password.getPassword());
						if(numer.length()!=0 && pwd.length()!=0){
							
						if(Checkuser==0){//USER
							if(UserServise.UserCheck(numer, pwd)){
								new Library();
								dispose();
						}else
							JOptionPane.showMessageDialog(null, "������˻�������", "��ʾ", JOptionPane.ERROR_MESSAGE); 

							}
						else if(Checkuser==1){//ADMIN
							if(AdminServise.AdminCheck(numer, pwd)){
								new ManagementWindow();
								dispose();
							}else
								JOptionPane.showMessageDialog(null, "������˻�������", "��ʾ", JOptionPane.ERROR_MESSAGE); 

						}
					}else
						JOptionPane.showMessageDialog(null, "����������", "��ʾ", JOptionPane.ERROR_MESSAGE); 
		     		}
		     	}
		     });	     	 
		
		JLabel label = new JLabel("�˺�:");
		label.setFont(new Font("΢���ź� Light", Font.PLAIN, 15));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(235, 358, 72, 18);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("����:");
		label_1.setFont(new Font("΢���ź� Light", Font.PLAIN, 15));
		label_1.setForeground(Color.WHITE);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(235, 397, 72, 18);
		getContentPane().add(label_1);
	}
	public void setImg(){
		background = new ImageIcon("images/login.png");// ����ͼƬ
		  JLabel label = new JLabel(background);// �ѱ���ͼƬ��ʾ��һ����ǩ����
		  // �ѱ�ǩ�Ĵ�Сλ
		  // ������ΪͼƬ�պ�����������
		  label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		  // �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
		  imagePanel = (JPanel) this.getContentPane();
		  imagePanel.setOpaque(false);
		  // ���ݴ���Ĭ�ϵĲ��ֹ�����ΪBorderLayout  new FlowLayout()
		  imagePanel.setLayout(null);
		  this.getLayeredPane().setLayout(null);
		  // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����
		  this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
	}
	
	public void addRdbtn(){
		/******��ѡ��ť��******/
		 JRadioButton rdbtnUser = new JRadioButton("User",true);
		 rdbtnUser.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		 rdbtnUser.setForeground(Color.WHITE);
			rdbtnUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {//����¼��������
					Checkuser=0;
				}
			});
			
			rdbtnUser.setBounds(63, 480, 61, 27);	
			imagePanel.add(rdbtnUser);
			
			JRadioButton rdbtnAdmin = new JRadioButton("Admin");
			rdbtnAdmin.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
			rdbtnAdmin.setForeground(Color.WHITE);
			rdbtnAdmin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Checkuser=1;
					
				}
			});
			rdbtnAdmin.setBounds(190, 480, 73, 27);
			imagePanel.add(rdbtnAdmin);
			ButtonGroup grout=new ButtonGroup();//����һ���� ����������ϲ�Ϊ��
			grout.add(rdbtnAdmin);
			grout.add(rdbtnUser);
			rdbtnAdmin.setContentAreaFilled(false);//�������͸��
			rdbtnUser.setContentAreaFilled(false);			
	}	
}
