/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 选择窗口
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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author hp
 */
class xuanzechuangkou extends JFrame implements ActionListener {
	JFrame frame = new JFrame("选择窗口");
	JPanel imagePanel;
	ImageIcon bg;
	Button button1, button2, button3, button4, button5, button6;
	Box box;
	Label label;
	Panel p1;

	xuanzechuangkou() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("tubiao.jpg");
		frame.setIconImage(img);
		
		p1 = new Panel();
		label = new Label("Welcome to School Roll Management System", Label.CENTER);
		label.setFont(new Font("隶书", Font.BOLD, 20));
		label.setBackground(Color.green);
		p1.add(label);
		
		button1 = new Button("Add");
		button2 = new Button("Delete");
		button3 = new Button("Modify");
		button4 = new Button("Inquire");
		button5 = new Button("Back");
		button6 = new Button("Exit");

		box = Box.createHorizontalBox();
		box.add(button1);
		box.add(Box.createHorizontalStrut(8));
		box.add(button2);
		box.add(Box.createHorizontalStrut(8));
		box.add(button3);
		box.add(Box.createHorizontalStrut(8));
		box.add(button4);
		box.add(Box.createHorizontalStrut(8));
		box.add(button5);
		box.add(Box.createHorizontalStrut(8));
		box.add(button6);

		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		button6.addActionListener(this);
		addWindowListener(new WindowAdapter() {
                        @Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		bg = new ImageIcon("hd.jpg");
		JLabel label = new JLabel(bg);
		label.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());
		imagePanel = (JPanel) frame.getContentPane();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(new FlowLayout());
		imagePanel.add(p1, BorderLayout.NORTH);
		imagePanel.add(box, BorderLayout.CENTER);

		frame.getLayeredPane().setLayout(null);
		frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭后状态
		frame.setSize(bg.getIconWidth(), bg.getIconHeight());
		frame.setBounds(200, 200, 500, 300);
		frame.setResizable(false);//设置窗口大小不可改变
		frame.setVisible(true);
		validate();

	}

        @Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button6) {
			System.exit(0);
		}
		if (e.getSource() == button1) {
			new tianjiashuju();
		}
		if (e.getSource() == button2) {
			new shanchushuju();
		}
		if (e.getSource() == button3) {
			new xiugaichengji();
		}
		if (e.getSource() == button4) {
			new chaxunshuju();
		}
		if (e.getSource() == button5) {
			new denglujiemian();
			frame.dispose();
		} else {
		}

	}

}