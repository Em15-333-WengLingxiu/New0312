/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 修改成绩
 */
package ms;

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
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *
 * @author hp
 */
class xiugaichengji extends JFrame implements ActionListener {
	JFrame frame = new JFrame("修改成绩");
	JPanel imagePanel;
	ImageIcon bg;
	Button xiug;
	TextField input1, input2, input3;
	TextArea show;
	Box box1, box2, box3;
	Panel p1, p2;
	int k, s1, j;

	Connection con;
	Statement sql;
	int sum = 0;
	ResultSet rs;

	xiugaichengji() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("tubiao.jpg");
		frame.setIconImage(img);
		p1 = new Panel();
		p2 = new Panel();
		xiug = new Button("Modify");

		input1 = new TextField(10);
		input2 = new TextField(10);
		input3 = new TextField(10);
		show = new TextArea(6, 43);
        
		Font f=new Font("宋体",Font.BOLD,10);
		Label l1=new Label("Please input your school number");
		l1.setFont(f);
		l1.setForeground(Color.blue);
		p1.add(l1);
		
		p1.add(new Label("school number："));
		p1.add(input1);
		box1 = Box.createVerticalBox();
		box1.add(Box.createVerticalStrut(8));
		box1.add(new Label("Math："));
		box1.add(Box.createVerticalStrut(8));
		box1.add(new Label("English："));
		box2 = Box.createVerticalBox();
		box2.add(input2);
		box2.add(Box.createVerticalStrut(8));
		box2.add(input3);
		box3 = Box.createHorizontalBox();
		box3.add(box1);
		box3.add(Box.createHorizontalStrut(1));
		box3.add(box2);
		
		Label l2=new Label("Input the grade");
		l2.setFont(f);
		l2.setForeground(Color.blue);
		p2.add(l2);
		p2.add(box3);

		xiug.addActionListener(this);
		show.setEditable(false);
		bg = new ImageIcon("hd.jpg");
		JLabel label = new JLabel(bg);
		label.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());
		imagePanel = (JPanel) frame.getContentPane();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(new FlowLayout());
		imagePanel.add(p1);
		imagePanel.add(p2);
		imagePanel.add(xiug);
		imagePanel.add(show);

		frame.getLayeredPane().setLayout(null);
		frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
	    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(bg.getIconWidth(), bg.getIconHeight());
		frame.setBounds(720, 220, 450, 390);
		frame.setResizable(false);
		frame.setVisible(true);
		validate();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}

	public void actionPerformed(ActionEvent ee) {
		String s1, s2, s3, insert1, number;
		;
		int m2, m3, math, english;
		s1 = input1.getText();
		number = s1;
		s2 = input2.getText();
		s3 = input3.getText();

		m2 = Integer.parseInt(s2);
		math = m2;
		m3 = Integer.parseInt(s3);
		english = m3;

		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException eee) {
			System.out.println("" + eee);
		}
		try {
			con = DriverManager.getConnection("jdbc:odbc:sun", "gxy", "123");
			sql = con.createStatement();
			insert1 = "UPDATE chengjibiao SET math =" + math + ",english="
					+ english + " WHERE number = " + "'" + number + "'";
			sql.executeUpdate(insert1);
			show.setText("你将了:");
			show.append("学号为 " + s1 + " 的学生 数学成绩更改为：" + math + "  英语成绩更改为："
					+ english);
			show.append("\n");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}