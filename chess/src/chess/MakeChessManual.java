/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;

/**
 *
 * @author Administrator
 */

public class MakeChessManual extends JPanel implements ActionListener          //创建“记录棋谱”对象
{
  JTextArea text=null;
  JScrollPane scroll=null;
  ChessBoard board=null;
  ChessPoint[][] point;
  LinkedList qipu=null;
  LinkedList eatchess=null;    
  JButton buttonUndo;
  int i=0;
  public MakeChessManual(ChessBoard board,ChessPoint[][] point)   
  {
   this.board=board;
   this.point=point;
   text=new JTextArea();
   scroll=new JScrollPane(text);
   qipu=new LinkedList();
   eatchess=new LinkedList();
   buttonUndo=new JButton("悔棋");
   buttonUndo.setFont(new Font("宋体",Font.PLAIN,18));
   setLayout(new BorderLayout());
   add(scroll,BorderLayout.CENTER);
   add(buttonUndo,BorderLayout.SOUTH); 
   buttonUndo.addActionListener(this);
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
  public void recordQipu(ChessPiece piece,int startI,int startJ,int endI,int endJ)     //记录棋谱
  { 
     Point pStart=new Point(startI,startJ);
     Point pEnd=new Point(endI,endJ);
     MoveStep step=new MoveStep(pStart, pEnd);
     qipu.add(step);
     
     String 棋子类别=piece.chessType();
     String name=piece.getName();
     String m="#"+棋子类别+name+": "+
              startI+numberToLetter(startJ)+" 到 "+endI+numberToLetter(endJ);
     text.append(m);
     if(piece.chessType().equals(board.黑方颜色))
        text.append("\n");
  }
 public void recordEatChess(Object object)
  {
     eatchess.add(object);
  }
 
  public LinkedList getQipu()
  {
     return qipu;
  }
 public void actionPerformed(ActionEvent e)
  { 
   int position=text.getText().lastIndexOf("#");
   if(position!=-1)
     text.replaceRange("",position,text.getText().length());
   if(qipu.size()>0)
     {
      MoveStep lastStep=(MoveStep)qipu.getLast();
      qipu.removeLast();
      Object qizi=eatchess.getLast();
      eatchess.removeLast();
      String temp=qizi.toString();
     if(temp.equals("没吃棋子"))
      {
        int startI=lastStep.Start.x;
        int startJ=lastStep.Start.y; 
        int endI=lastStep.End.x;
        int endJ=lastStep.End.y; 
        ChessPiece piece=point[endI][endJ].getPiece();
        
        point[startI][startJ].setPiece(piece,board);
        (point[endI][endJ]).setHaveChess(false);
 
        if(piece.chessType().equals(board.红方颜色))
         {
           board.redgo=true;
           board.blackgo=false;
         }
        if(piece.chessType().equals(board.黑方颜色))
         {
           board.blackgo=true;
           board.redgo=false;
         }
       }
     else
       {
         ChessPiece removedPiece=(ChessPiece)qizi;
         int startI=lastStep.Start.x;
         int startJ=lastStep.Start.y; 
         int endI=lastStep.End.x;
         int endJ=lastStep.End.y; 
         ChessPiece piece=point[endI][endJ].getPiece();
         point[startI][startJ].setPiece(piece,board);     
         point[endI][endJ].setPiece(removedPiece,board);  
         (point[endI][endJ]).setHaveChess(true);
 
        if(piece.chessType().equals(board.红方颜色))
         {
           board.redgo=true;
           board.blackgo=false;
         }
        if(piece.chessType().equals(board.黑方颜色))
         {
           board.blackgo=true;
           board.redgo=false;
         }
      }
    }
  }
}

