/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 登陆界面
 */
package ms;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author hp
 */
class denglujiemian extends JFrame implements ActionListener {
	JFrame frame = new JFrame("登陆界面");
	JPanel imagePanel;
	ImageIcon bg;
	Button button1, button2;
	Label l1, l2, l3;
	TextField a1, a2;
	Box b1, b2, b3, b4, b5;
        denglujiemian()
        {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("a.jpg");
		frame.setIconImage(img);
		button1 = new Button("Submit");
		button2 = new Button("Exit");
		Panel p1 = new Panel();
		l1 = new Label("School Roll Management System", Label.CENTER);
		l1.setFont(new Font("隶书", Font.BOLD, 30));
		l1.setBackground(Color.green);
		p1.add(l1);
		l2 = new Label("Login Name");
		l3 = new Label("Password");
		a1 = new TextField(10);
		a2 = new TextField(10);
		a2.setEchoChar('*');

		b1 = Box.createVerticalBox();
		b1.add(l2);
		b1.add(Box.createVerticalStrut(8));
		b1.add(l3);

		b2 = Box.createVerticalBox();
		b2.add(a1);
		b2.add(Box.createVerticalStrut(8));
		b2.add(a2);

		b3 = Box.createHorizontalBox();
		b3.add(b1);
		b3.add(Box.createHorizontalStrut(10));
		b3.add(b2);

		b4 = Box.createHorizontalBox();
		b4.add(button1);
		b4.add(Box.createHorizontalStrut(10));
		b4.add(button2);

		b5 = Box.createVerticalBox();
		b5.add(b3);
		b5.add(Box.createVerticalStrut(8));
		b5.add(b4);

		button1.addActionListener(this);
		button2.addActionListener(this);
		a1.setText("admin");
		a2.setText("admin");
		addWindowListener(new WindowAdapter() {
                        @Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		bg = new ImageIcon("a.jpg");
		JLabel label = new JLabel(bg);
		label.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());
		imagePanel = (JPanel) frame.getContentPane();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(new FlowLayout());
		imagePanel.add(p1, BorderLayout.NORTH);
		imagePanel.add(b5, BorderLayout.CENTER);

		frame.getLayeredPane().setLayout(null);
		frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(bg.getIconWidth(), bg.getIconHeight());
		frame.setBounds(200, 150, 510, 340);
		frame.setResizable(false);//设置窗口大小不可改变
		frame.setVisible(true);
		validate();
	}
        @Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button2) {
			System.exit(0);
		}
		if (e.getSource() == button1) {
			if (a1.getText().equals("admin") && a2.getText().equals("admin")) {
				new xuanzechuangkou();
				frame.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "账户密码错误，请重新输入！");
			}
		}
	}
}
