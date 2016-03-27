package com.gct.gamecode.t6;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gct.gamecode.t6.constant.MyConstant6;
import com.gct.gamecode.t6.util.ImgUtils6;

public class JFrameT6 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private JPanel jpanelGame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameT6 frame = new JFrameT6();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		ImgUtils6.getImgByFileName("");//loading img
	}

	/**
	 * Create the frame.
	 */
	public JFrameT6() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(MyConstant6.DEF_FRAME_X, MyConstant6.DEF_FRAME_Y, MyConstant6.DEF_FRAME_WIDTH, MyConstant6.DEF_FRAME_HEIGHT);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		jpanelGame = new JPanelGame6();
		contentPane.add(jpanelGame, BorderLayout.CENTER);
		
		setContentPane(contentPane);
	}

}
