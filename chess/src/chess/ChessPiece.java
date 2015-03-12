/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chess;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Administrator
 */


public class ChessPiece extends JLabel        //创建棋子对象
{
   String name;
   Color backColor=null,foreColor;
   String colortype=null;
   ChessBoard board=null;
   int width,height;
  public ChessPiece(String name,Color fc,Color bc,int width,int height,ChessBoard board)
   {
     this.name=name;
     this.board=board;
     this.width=width;
     this.height=height;
     foreColor=fc; 
     backColor=bc;
     setSize(width,height);
     setBackground(bc); 
     addMouseMotionListener(board);
     addMouseListener(board);
   }
   public void paint(Graphics g)       //绘制棋子的外观
   { 
     g.setColor(foreColor);
     g.fillOval(2,2,width-2,height-2);
     g.setColor(Color.white);
     g.setFont(new Font("宋体",Font.BOLD,28));     
     g.drawString(name,7,height-8);
     g.setColor(Color.green);  
     g.drawOval(2,2,width-2,height-2);
   }
   public int getWidth()                        //获取棋子的宽度
   {
     return width;
   }
   public int getHeight()                      //获取棋子的高度
   {
     return height;
   }
   public String getName()                    //获取棋子的名字
   {
     return name;
   }
   public Color getColor()                   //获取棋子的颜色
   {
     return foreColor;
   }
   public void setChessType(String chesstype)             //设置棋子的类别
   {
      colortype=chesstype;
   }
  public String chessType()                    //获取棋子的类别
   {
     return  colortype;
   }
} 

