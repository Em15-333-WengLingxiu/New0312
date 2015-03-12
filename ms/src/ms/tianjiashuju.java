/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 添加数据
 */
package ms;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
/**
 *
 * @author hp
 */
class tianjiashuju extends JFrame implements ActionListener {
	JFrame frame = new JFrame("添加数据");
	JPanel imagePanel;
	ImageIcon bg;
	Button sure;
	TextField input1, input2, input3, input4, input5;
	TextArea show;
	Box box1, box2, box3, box4;
	int k, s1, j;
	Connection con;
	Statement sql;
	ResultSet rs;

	tianjiashuju() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("tubiao.jpg");
		frame.setIconImage(img);
		input1 = new TextField(10);
		input2 = new TextField(10);
		input3 = new TextField(10);
		input4 = new TextField(10);
		input5 = new TextField(10);

		box2 = Box.createVerticalBox();
		box2.add(input1);
		box2.add(Box.createVerticalStrut(8));
		box2.add(input2);
		box2.add(Box.createVerticalStrut(8));
		box2.add(input3);
		box2.add(Box.createVerticalStrut(8));
		box2.add(input4);
		box2.add(Box.createVerticalStrut(8));
		box2.add(input5);

		box1 = Box.createVerticalBox();
		box1.add(new Label("Student Number："));
		box1.add(Box.createVerticalStrut(8));
		box1.add(new Label("Name："));
		box1.add(Box.createVerticalStrut(8));
		box1.add(new Label("Birthday："));
		box1.add(Box.createVerticalStrut(8));
		box1.add(new Label("Math："));
		box1.add(Box.createVerticalStrut(8));
		box1.add(new Label("English："));

		box3 = Box.createHorizontalBox();
		box3.add(box1);
		box3.add(Box.createHorizontalStrut(1));
		box3.add(box2);
		show = new TextArea(6, 43);
		sure = new Button("Yes");

		box4 = Box.createHorizontalBox();
		Label label1 = new Label("Please input the data");
		label1.setFont(new Font("宋体", Font.BOLD, 20));
		label1.setForeground(Color.blue);
		box4.add(label1);
		box4.add(Box.createHorizontalStrut(1));
		box4.add(sure);

		sure.addActionListener(this);
		bg = new ImageIcon("bc.jpg");
		JLabel label = new JLabel(bg);
		label.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());
		imagePanel = (JPanel) frame.getContentPane();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(new FlowLayout());
		show.setEditable(false);
		imagePanel.add(box4, BorderLayout.NORTH);
		imagePanel.add(box3, BorderLayout.CENTER);
		imagePanel.add(show, BorderLayout.SOUTH);
		frame.getLayeredPane().setLayout(null);
		frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(bg.getIconWidth(), bg.getIconHeight());
		frame.setBounds(600, 200, 540, 440);
		frame.setResizable(false);
		frame.setVisible(true);
		validate();
		addWindowListener(new WindowAdapter() {
                        @Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
        /*public void actionPerformed(ActionEvent ee)
        {
            File file = new File("out2.txt");
      if(!file.exists())
        file.createNewFile();
      int  number;
       DataOutputStream dataoutputstrea = new DataOutputStream(new FileOutputStream(file));
        dataoutputstrea.writeUTF(input1);
        dataoutputstrea.flush();
        dataoutputstrea.close();
        DataInputStream datainputstream = new DataInputStream(new FileInputStream(file));

       number=datainputstream.readUTF();
        datainputstream.close();
        System.out.print(input1);
      
            
        }
}*/
        
    /*public void actionPerformed(ActionEvent ee) throws IOException
       {  
        FileOutputStream out1=new  FileOutputStream("text.txt");
        BufferedOutputStream out2=new BufferedOutputStream(out1);
        DataOutputStream out=new DataOutputStream(out2);
        out.writeUTF("Kimi");
        out.writeInt(20);
        out.writeFloat((float) 85.5);
        out.close();
        
        InputStream in1=new FileInputStream("text.txt");
        BufferedInputStream in2=new BufferedInputStream(in1);
        DataInputStream in=new DataInputStream(in2);
        System.out.print("姓名"+" - "+in.readUTF()+", ");
        System.out.print("年龄"+" - "+in.readInt()+", ");
        System.out.print("成绩"+" - "+in.readFloat()+". "); 
        in.close();
       }*/
           
      
       
	public void actionPerformed(ActionEvent ee) {
		String s1, s2, s3, s4, s5, insert1, recode, name, date, number;
		int m1, m2, math, english;
		s1 = input1.getText();
		s2 = input2.getText();
		s3 = input3.getText();
		s4 = input4.getText();
		s5 = input5.getText();
		number = s1;
		name = s2;
		date = s3;
		m1 = Integer.parseInt(s4);
		math = m1;
		m2 = Integer.parseInt(s5);
		english = m2;

		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException eee) {
			System.out.println("" + eee);
		}
		try {
			con = DriverManager.getConnection("jdbc:odbc:sun", "gxy", "123");
			sql = con.createStatement();
			recode = "(" + "'" + number + "'" + "," + "'" + name + "'" + ","
					+ "'" + date + "'" + "," + math + "," + english + ")";
			insert1 = "INSERT INTO chengjibiao VALUES " + recode;
			sql.executeUpdate(insert1);
			show.setText("You have insert:");
			show.append("Student Number：" + number + "  Name：" + name + "  Birthday：" + date
					+ " Math " + math + " English " + english);
			show.append("\n");
		} catch (SQLException e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(this, "Sorry!The number have exit ！");
		}
	}
         }