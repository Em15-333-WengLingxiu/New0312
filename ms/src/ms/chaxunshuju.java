/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 查询数据
 */
package ms;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
class chaxunshuju extends JFrame implements ActionListener {
	JFrame frame = new JFrame("查询记录");
	JPanel imagePanel;
	ImageIcon bg;
	Button xunzhao;
	TextField input;
	TextArea show;
	Box box;
	String s;

	Connection con;
	Statement sql;
	ResultSet rs;

	chaxunshuju() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("tubiao.jpg");
		frame.setIconImage(img);

		xunzhao = new Button("Inquire");
		input = new TextField(10);
		show = new TextArea(6, 43);
		xunzhao.addActionListener(this);

		show.setEditable(false);
		box = Box.createHorizontalBox();
		
		Label l1=new Label("Please input in your school number ");
		l1.setFont(new Font("宋体",Font.BOLD,15));
		l1.setForeground(Color.blue);
		box.add(l1);
		
		box.add(Box.createHorizontalStrut(8));
		box.add(input);
		box.add(Box.createHorizontalStrut(8));
		box.add(xunzhao);

		bg = new ImageIcon("ha.jpg");
		JLabel label = new JLabel(bg);
		label.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());
		imagePanel = (JPanel) frame.getContentPane();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(new FlowLayout());
		imagePanel.add(box, BorderLayout.NORTH);
		imagePanel.add(show, BorderLayout.CENTER);

		frame.getLayeredPane().setLayout(null);
		frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
	    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(bg.getIconWidth(), bg.getIconHeight());
		frame.setBounds(730, 230, 520, 350);
		frame.setResizable(false);//设置窗口大小不可改变
		frame.setVisible(true);
		validate();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}

	public void actionPerformed(ActionEvent ee) {
		boolean boo = true;
		s = input.getText();
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException eee) {
			System.out.println("" + eee);
		}
		try {
			con = DriverManager.getConnection("jdbc:odbc:sun", "gxy", "123");
			sql = con.createStatement();
			rs = sql.executeQuery("SELECT * FROM chengjibiao ");
			while (rs.next()) {
				String number = rs.getString("number");
				String name = rs.getString("name");
				String date = rs.getString("date");
				int math = rs.getInt("math");
				int english = rs.getInt("english");
				if (number.equals(s)) {
					boo = false;
					show.setText(null);
					show.append("number：" + number + "  name：" + name + "  birthday："
							+ date + "  math " + math + "  english " + english);
					show.append("\n");
				}
			}
			con.close();
			if (boo) {
				JOptionPane.showMessageDialog(this, "你输入的学号不存在");
			}
		} catch (SQLException eee) {
			System.out.println(eee);
		}
	}
}