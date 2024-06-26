package org.kdt.ui.admin;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.kdt.dao.MemberDAO;
import org.kdt.dao.MembersProductDAO;
import org.kdt.dao.ProductDAO;
import org.kdt.service.MembersProductService;
import org.kdt.service.MembersProductServiceImpl;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Failure extends JFrame {

	private static final long serialVersionUID = 1L;

	private final MembersProductService membersProductService;
	private JPanel contentPane;
	private JTextField orderNo;

	public Failure() {
		membersProductService = new MembersProductServiceImpl(new MembersProductDAO(), new ProductDAO(),new MemberDAO());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("반려하기");

		JLabel lblNewLabel = new JLabel("주문번호");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 43, 100, 40);
		contentPane.add(lblNewLabel);

		orderNo = new JTextField();
		orderNo.setBounds(124, 39, 200, 50);
		contentPane.add(orderNo);
		orderNo.setColumns(10);
		
		JButton btnFailure = new JButton("반려하기");
		btnFailure.setBounds(81, 113, 200, 40);
		btnFailure.setBackground(Color.decode("#DC143C"));
		btnFailure.setForeground(Color.white);
		contentPane.add(btnFailure);

		btnFailure.addActionListener(e -> btnFailureBtnAction());

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
	}

	private void btnFailureBtnAction(){
		int i = membersProductService.requestOrderFailure(orderNo.getText());
		if(i <= 0){
			JOptionPane.showMessageDialog(null, "해당 주문이 존재하지 않습니다.");
			setVisible(false);
		}else {
			JOptionPane.showMessageDialog(null, "반려하였습니다.");
			setVisible(false);
		}
	}
}
