package com.gct.gamecode.t3;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gct.gamecode.t3.constant.MyConstant3;

public class JFrameT3 extends JFrame {

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
					JFrameT3 frame = new JFrameT3();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFrameT3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(MyConstant3.DEF_FRAME_X, MyConstant3.DEF_FRAME_Y, MyConstant3.DEF_FRAME_WIDTH, MyConstant3.DEF_FRAME_HEIGHT);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		jpanelGame = new JPanelGame3();
		contentPane.add(jpanelGame, BorderLayout.CENTER);
		
		setContentPane(contentPane);
	}

}
