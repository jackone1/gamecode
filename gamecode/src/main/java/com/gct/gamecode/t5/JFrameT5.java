package com.gct.gamecode.t5;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gct.gamecode.t5.constant.MyConstant5;
import com.gct.gamecode.t5.util.ImgUtils;

public class JFrameT5 extends JFrame {

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
					JFrameT5 frame = new JFrameT5();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		ImgUtils.getImgByFileName("");//loading img
	}

	/**
	 * Create the frame.
	 */
	public JFrameT5() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(MyConstant5.DEF_FRAME_X, MyConstant5.DEF_FRAME_Y, MyConstant5.DEF_FRAME_WIDTH, MyConstant5.DEF_FRAME_HEIGHT);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		jpanelGame = new JPanelGame5();
		contentPane.add(jpanelGame, BorderLayout.CENTER);
		
		setContentPane(contentPane);
	}

}
