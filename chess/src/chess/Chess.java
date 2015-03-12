/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.LinkedList;

/**
 *
 * @author Administrator
 */


public class Chess extends JFrame implements ActionListener
{
  ChessBoard cboard=null;
  Demon demon=null;
  MakeChessManual record=null;
  Container con=null;
  JMenuBar bar;
  JMenu jmenu;
  JMenuItem zzqp,bcqp,ysqp;
  JFileChooser fileChooser=null;
  LinkedList qipu=null;
  public Chess()          //创建程序主窗口
   { 
      bar=new JMenuBar();
      jmenu=new JMenu("中国象棋");
      zzqp=new JMenuItem("制作棋谱");
      bcqp=new JMenuItem("保存棋谱");
      ysqp=new JMenuItem("演示棋谱");
      jmenu.add(zzqp);
      jmenu.add(bcqp);
      jmenu.add(ysqp);
      bar.add(jmenu);
      setJMenuBar(bar);
      setTitle(zzqp.getText());
      zzqp.addActionListener(this);
      bcqp.addActionListener(this);
      ysqp.addActionListener(this);
      cboard=new ChessBoard(45,45,9,10);
      record=cboard.record;
      con=getContentPane();
      JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,cboard,record);
      split.setDividerSize(5);
      split.setDividerLocation(460);
      con.add(split,BorderLayout.CENTER); 
      addWindowListener(new WindowAdapter()
                 { public void windowClosing(WindowEvent e)
                     { System.exit(0);
      	             }
                 });
      setVisible(true);
      setBounds(60,20,670,540);
      fileChooser=new JFileChooser();
      con.validate();
      validate();
   }
  public void actionPerformed(ActionEvent e)
   { 
     if(e.getSource()==zzqp) 
       {
         con.removeAll();
         bcqp.setEnabled(true);
         this.setTitle(zzqp.getText());
         cboard=new ChessBoard(45,45,9,10);
         record=cboard.record;
         JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,cboard,record);
         split.setDividerSize(5);
         split.setDividerLocation(460);
         con.add(split,BorderLayout.CENTER); 
         validate();
       }   
     if(e.getSource()==bcqp) 
       { 
         int state=fileChooser.showSaveDialog(null);
         File saveFile =fileChooser.getSelectedFile();
          if(saveFile!=null&&state==JFileChooser.APPROVE_OPTION)
               {try
                   {
                    FileOutputStream outOne=new FileOutputStream(saveFile);
                    ObjectOutputStream outTwo=new ObjectOutputStream(outOne);
                    outTwo.writeObject(record.getQipu()) ;
                    outOne.close();
                    outTwo.close();
                   }
                catch(IOException event)
                   {
                   } 
               }
       }
     if(e.getSource()==ysqp) 
       {         
           con.removeAll();
           con.repaint();
           con.validate(); 
           validate();
         bcqp.setEnabled(false);
         
         int state=fileChooser.showOpenDialog(null);
         File openFile =fileChooser.getSelectedFile();
          if(openFile!=null&&state==JFileChooser.APPROVE_OPTION)
               {try
                   {
                    FileInputStream inOne=new FileInputStream(openFile);
                    ObjectInputStream inTwo=new ObjectInputStream(inOne);
                    qipu=(LinkedList)inTwo.readObject() ;
                    inOne.close();
                    inTwo.close();
                    ChessBoard board=new ChessBoard(45,45,9,10);
                    demon=new Demon(board);
                    demon.setQipu(qipu);
                    con.add(demon,BorderLayout.CENTER);
                    con.validate(); 
                    validate();
                    this.setTitle(ysqp.getText()+":"+openFile); 
                   }
                catch(Exception event)
                   {
                      JLabel label=new JLabel("不是棋谱文件");
                      label.setFont(new Font("宋体",Font.BOLD,60));
                      label.setForeground(Color.red); 
                      label.setHorizontalAlignment(SwingConstants.CENTER);
                      con.add(label,BorderLayout.CENTER);
                      con.validate();  
                      this.setTitle("无法打开棋谱"); 
                      validate();
                   } 
               }
           else
              {
                JLabel label=new JLabel("无法打开");
                label.setFont(new Font("宋体",Font.BOLD,50)); 
                label.setForeground(Color.pink);  
                label.setHorizontalAlignment(SwingConstants.CENTER);
                con.add(label,BorderLayout.CENTER);
                con.validate(); 
                this.setTitle("无法打开"); 
                validate();  
              }
       }
   }
  public static void main(String args[])
   {
      new Chess();
   }
}
