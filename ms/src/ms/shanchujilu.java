/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 删除数据
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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author hp
 */
class shanchushuju extends JFrame implements ActionListener {
	JFrame frame = new JFrame("删除记录");
	JPanel imagePanel;
	ImageIcon bg;
	Button xunzhao;
	TextField input;
	TextArea show;
	String s;
	int k, s1, j;
	Connection con;
	Statement sql;
	int sum = 0;
	ResultSet rs;

	shanchushuju() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("tubiao.jpg");
		frame.setIconImage(img);
		Panel p = new Panel();
		xunzhao = new Button("Delete");
		input = new TextField(15);
		show = new TextArea(6, 43);
		Label l1=new Label("Please input the number you want to delete");
		l1.setFont(new Font("宋体",Font.BOLD,10));
		l1.setForeground(Color.BLUE);
		p.add(l1);
		p.add(input);
		p.add(xunzhao);

		xunzhao.addActionListener(this);
		show.setEditable(false);

		bg = new ImageIcon("hb.jpg");
		JLabel label = new JLabel(bg);
		label.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());
		imagePanel = (JPanel) frame.getContentPane();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(new FlowLayout());
		imagePanel.add(p, BorderLayout.NORTH);
		imagePanel.add(show, BorderLayout.CENTER);

		frame.getLayeredPane().setLayout(null);
		frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(bg.getIconWidth(), bg.getIconHeight());
		frame.setBounds(710, 210, 480, 310);
		frame.setVisible(true);
		frame.setResizable(false);//设置窗口大小不可改变
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
		String m;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException eee) {
			System.out.println("" + eee);
		}
		try {
			con = DriverManager.getConnection("jdbc:odbc:sun", "gxy", "123");
			sql = con.createStatement();
			rs = sql.executeQuery("SELECT * FROM chengjibiao");
			while (rs.next()) {
				String number = rs.getString("number");
				String name = rs.getString("name");
				String date = rs.getString("date");
				int math = rs.getInt("math");
				int english = rs.getInt("english");
				if (number.equals(s)) {
					show.setText("你删除了:");
					show.append("学号：" + number + "  姓名：" + name + "  出生："
							+ date + " 数学 " + math + " 英语 " + english);
					show.append("\n");
					m = "DELETE FROM chengjibiao WHERE number = '"
							+ input.getText() + "'";
					sql.executeUpdate(m);
				}
			}
			con.close();
			if (boo) {
				JOptionPane.showMessageDialog(this, "你输入的学号不存在");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}

