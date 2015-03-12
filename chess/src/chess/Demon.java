/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 *
 * @author Administrator
 */



public class Demon extends JPanel  implements ActionListener,Runnable   //创建棋谱演示对象
{
   public JButton replay=null,next=null,auto=null,stop=null;
   LinkedList qipu=null;
   Thread zdys=null;
   int index=-1;
   ChessBoard board=null;
   JTextArea text;
   JTextField sjjg=null;
   int time=1000;
   String ysgc="";
   JSplitPane splitH=null,splitV=null;
   public  Demon(ChessBoard board)
   {    
        this.board=board;
        replay=new JButton("重新演示");
        next=new JButton("下一步");
        auto=new JButton("自动演示");
        stop=new JButton("暂停演示");
        zdys=new Thread(this);
        replay.addActionListener(this);
        next.addActionListener(this);
        auto.addActionListener(this);
        stop.addActionListener(this); 
        text=new JTextArea(); 
        sjjg=new JTextField("1");
        setLayout(new BorderLayout());
        JScrollPane pane=new JScrollPane(text);
        JPanel p=new JPanel(new GridLayout(3,2));
        p.add(next);
        p.add(replay);
        p.add(auto);
        p.add(stop);
        p.add(new JLabel("时间间隔(秒)",SwingConstants.CENTER)) ;
        p.add(sjjg);
        splitV=new JSplitPane(JSplitPane.VERTICAL_SPLIT,pane,p);
        splitH=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,board,splitV);
        splitV.setDividerSize(5);
        splitV.setDividerLocation(400);
        splitH.setDividerSize(5);
        splitH.setDividerLocation(460);
        add(splitH,BorderLayout.CENTER); 
        validate();
    }   
 public void setQipu(LinkedList qp)             //设置棋谱
   { 
    this.qipu=qp;
   }
 public char numberToLetter(int n)   
  { 
    char c='\0';
    switch(n)
     {
       case 1 : c='A'; break;
       case 2 : c='B'; break;
       case 3 : c='C'; break;
       case 4 : c='D'; break;
       case 5 : c='E'; break;
       case 6 : c='F'; break;
       case 7 : c='G'; break;
       case 8 : c='H'; break;
       case 9 : c='I'; break;
       case 10 : c='J'; break;
     } 
    return c;
  }
 public void actionPerformed(ActionEvent e)
  {
    if(e.getSource()==next)
      {
         index++;
         if(index<qipu.size())
           { 
              runOnetime(index);
           }
         else
           {
             runDone("演示完毕");
           } 
       }
     if(e.getSource()==replay)
       {
          board=new ChessBoard(45,45,9,10);
          splitH.remove(board);
          splitH.setDividerSize(5);
          splitH.setDividerLocation(460);
          splitH.setLeftComponent(board);
          splitH.validate();
          index=-1;
          text.setText(null);
       }
     if(e.getSource()==auto)
       {
           next.setEnabled(false);
           replay.setEnabled(false); 
           try
              {
                time=1000*Integer.parseInt(sjjg.getText().trim());
              }
           catch(NumberFormatException ee)
              {
                time=1000;
              } 
 
           if(!(zdys.isAlive()))          
             {
                zdys=new Thread(this);
                board=new ChessBoard(45,45,9,10);
                splitH.remove(board);
                splitH.setDividerSize(5);
                splitH.setDividerLocation(460);
                splitH.setLeftComponent(board);
                splitH.validate();
                text.setText(null);
                zdys.start();
             }
   
       }
     if(e.getSource()==stop)
       {
          if(e.getActionCommand().equals("暂停演示"))
               {
                 ysgc="暂停演示";
                 stop.setText("继续演示");
                 stop.repaint();
               }
          if(e.getActionCommand().equals("继续演示"))
               {
                 ysgc="继续演示";
                 zdys.interrupt();   
                 stop.setText("暂停演示");
                 stop.repaint();
               }
       }
  }
  public synchronized void run()
  {
    for(index=0;index<qipu.size();index++)
       {
         try{
              Thread.sleep(time);
            }
         catch(InterruptedException e)
            {
            }  
         while(ysgc.equals("暂停演示"))
            {  
                 try{
                      wait();  
                    }
                  catch(InterruptedException e)
                    {
                      notifyAll();  
                    }  
            }
         runOnetime(index);
       }
     if(index>=qipu.size())
       {
         runDone("演示完毕");
         next.setEnabled(true);
         replay.setEnabled(true);  
       } 
  }
  public void runOnetime(int index)
  {
     MoveStep step=(MoveStep)qipu.get(index);
          Point pStart=step.Start;
          Point pEnd  =step.End;
          int startI  =pStart.x;
          int startJ  =pStart.y;
          int endI    =pEnd.x;
          int endJ    =pEnd.y;
          ChessPiece piece=(board.point)[startI][startJ].getPiece();
      if((board.point)[endI][endJ].isPiece()==true)
          {
            ChessPiece pieceRemoved=(board.point)[endI][endJ].getPiece();
            (board.point)[endI][endJ].reMovePiece(pieceRemoved,board);
            board.repaint();
            (board.point)[endI][endJ].setPiece(piece,board);
            (board.point)[startI][startJ].setHaveChess(false);
            board.repaint();
          }
      else
          {
            (board.point)[endI][endJ].setPiece(piece,board);
            (board.point)[startI][startJ].setHaveChess(false);
                  
          }
      String chesstype=piece.chessType();
      String name=piece.getName();
      String m=chesstype+name+": 从"+
                   startI+numberToLetter(startJ)+" 到 "+endI+numberToLetter(endJ);
      text.append(m);
      if(piece.chessType().equals(board.黑方颜色))
            text.append("\n");
  }
 public void runDone(String message)
  {
    splitH.remove(board);
    splitH.setDividerSize(5);
    splitH.setDividerLocation(460);
    JLabel label=new JLabel(message);
    label.setFont(new Font("宋体",Font.BOLD,40));
    label.setForeground(Color.blue); 
    label.setHorizontalAlignment(SwingConstants.CENTER);
    splitH.setLeftComponent(label);
    splitH.validate();
  }
}

