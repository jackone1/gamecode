package com.gct.gamecode.t2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gct.gamecode.t1.constant.MyConstant1;

public class JFrameT2 extends JFrame {

	private JPanel contentPane;
	
	private JPanel jpanelGame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameT2 frame = new JFrameT2();
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
	public JFrameT2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(MyConstant1.DEF_FRAME_X, MyConstant1.DEF_FRAME_Y, MyConstant1.DEF_FRAME_WIDTH, MyConstant1.DEF_FRAME_HEIGHT);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		jpanelGame = new JPanelGame();
		jpanelGame.setLayout(new BorderLayout(0, 0));
		
		contentPane.add(jpanelGame, BorderLayout.CENTER);
		
		setContentPane(contentPane);
		
//		this.addKeyListener((KeyListener) jpanelCircle11);
	}

}
