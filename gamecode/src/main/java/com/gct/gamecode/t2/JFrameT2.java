package com.gct.gamecode.t2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gct.gamecode.t1.constant.MyConstant1;

public class JFrameT2 extends JFrame {

	private JPanel contentPane;
	
	private JPanel jpanelCircle11;

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
		
		jpanelCircle11 = new JPanelCircle2();
		jpanelCircle11.setLayout(new BorderLayout(0, 0));
		
		contentPane.add(jpanelCircle11, BorderLayout.CENTER);
		
		setContentPane(contentPane);
		
		this.addKeyListener((KeyListener) jpanelCircle11);
	}

}
