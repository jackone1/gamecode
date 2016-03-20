package com.gct.gamecode.t4;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gct.gamecode.t4.constant.MyConstant4;

public class JFrameT4 extends JFrame {

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
					JFrameT4 frame = new JFrameT4();
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
	public JFrameT4() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(MyConstant4.DEF_FRAME_X, MyConstant4.DEF_FRAME_Y, MyConstant4.DEF_FRAME_WIDTH, MyConstant4.DEF_FRAME_HEIGHT);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		jpanelGame = new JPanelGame4();
		contentPane.add(jpanelGame, BorderLayout.CENTER);
		
		setContentPane(contentPane);
	}

}
