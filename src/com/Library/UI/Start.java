package com.Library.UI;

import java.awt.EventQueue;
/**
 * ͼ�����ϵͳ Mysql �� v2.0��
 * 2017-6-24
 * @author PuaChen
 *
 */
public class Start {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new LoginWindow();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
