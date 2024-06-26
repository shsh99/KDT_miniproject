package org.kdt.ui.user;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.kdt.dao.MemberDAO;
import org.kdt.dto.MemberDTO;
import org.kdt.service.MemberService;
import org.kdt.service.MemberServiceImpl;

public class ChargeMoneyForm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;

    private MemberDTO memberDTO;

    private final MemberService memberService;

    public ChargeMoneyForm(MemberDTO memberDTO) {
        this.memberDTO = memberDTO;
        memberService = new MemberServiceImpl(new MemberDAO());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 350, 200);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("충전할 금액");
        lblNewLabel.setBounds(12, 31, 77, 50);
        contentPane.add(lblNewLabel);
        setTitle("금액충전");
        
        textField = new JTextField();
        textField.setBounds(101, 32, 212, 50);
        contentPane.add(textField);
        textField.setColumns(10);
        
        JButton btnChargeMoney = new JButton("충전하기");
        btnChargeMoney.setBounds(68, 105, 200, 40);
        btnChargeMoney.setBackground(Color.decode("#DAA520"));
        btnChargeMoney.setForeground(Color.white);
        contentPane.add(btnChargeMoney);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        btnChargeMoney.addActionListener(e -> chargeMoneyBtnAction());
    }

    private void chargeMoneyBtnAction(){
        int member_balance = memberDTO.getMember_balance();
        int chargeAmount = Integer.parseInt(textField.getText());
        int i = memberService.chargeMoney(memberDTO, chargeAmount);
        if(i > 0){
            JOptionPane.showMessageDialog(null, "충전에 성공하였습니다.\n현재 잔액: " + addCommasToNumber(member_balance + chargeAmount));
            memberDTO.setMember_balance(member_balance + chargeAmount); // 충전된 금액을 현재 잔액에 추가
            setVisible(false);
        }else{
            JOptionPane.showMessageDialog(null, "충전에 실패하였습니다.");
            setVisible(false);
        }
    }

    // 숫자를 문자열로 변환하고 3자리마다 쉼표를 추가하는 메서드
    private String addCommasToNumber(int number) {
        return String.format("%,d", number);
    }
}
