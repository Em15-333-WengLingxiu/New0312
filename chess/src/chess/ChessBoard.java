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



public class ChessBoard extends JPanel implements MouseListener,MouseMotionListener  //创建对弈棋盘
{
   public ChessPoint point[][];                                
   public int unitWidth,unitHeight;                            
   int xz,yz;                                            
   int x,y;                                                    
   boolean move=false;    
   public String     红方颜色="红色",黑方颜色="蓝色";
   ChessPiece 红车1,红车2,红马1,红马2,红相1,红相2,红帅,红士1,红士2,
              红兵1,红兵2,红兵3,红兵4,红兵5,红炮1,红炮2;
   ChessPiece 黑车1,黑车2,黑马1,黑马2,黑将,黑士1,黑士2,
              黑卒1,黑卒2,黑卒3,黑卒4,黑卒5,黑象1,黑象2,黑炮1,黑炮2;
   
   int startx,starty;                                         
   int starti,startj;                                         
   public boolean redgo=true,blackgo=false;               
   Rule rule=null;                                            
   public  MakeChessManual record=null;                        
   public  ChessBoard(int w,int h,int r,int c)
   {
        setLayout(null);
        addMouseListener(this);
        addMouseMotionListener(this);
        Color bc=getBackground();
        unitWidth=w;
        unitHeight=h;
        xz=r;
        yz=c;
      
        point=new ChessPoint[r+1][c+1]; 
                                        
                                        
        for(int i=1;i<=r;i++)
          {
            for(int j=1;j<=c;j++)
              {
                point[i][j]=new ChessPoint(i*unitWidth,j*unitHeight,false); 
              }
          }

        rule=new Rule(this,point);
        record=new MakeChessManual(this,point) ;  
        
        红车1=new ChessPiece("车",Color.red,bc,w-4,h-4,this);
        红车1.setChessType("红色");
        红车2=new ChessPiece("车",Color.red,bc,w-4,h-4,this);
        红车2.setChessType("红色");
        红马1=new ChessPiece("马",Color.red,bc,w-4,h-4,this);
        红马1.setChessType("红色");
        红马2=new ChessPiece("马",Color.red,bc,w-4,h-4,this);
        红马2.setChessType("红色");
        红炮1=new ChessPiece("炮",Color.red,bc,w-4,h-4,this);
        红炮1.setChessType("红色");
        红炮2=new ChessPiece("炮",Color.red,bc,w-4,h-4,this);
        红炮2.setChessType("红色");
        红相1=new ChessPiece("相",Color.red,bc,w-4,h-4,this);
        红相1.setChessType("红色");
        红相2=new ChessPiece("相",Color.red,bc,w-4,h-4,this);
        红相2.setChessType("红色");
        红士1=new ChessPiece("士",Color.red,bc,w-4,h-4,this);
        红士1.setChessType("红色");
        红士2=new ChessPiece("士",Color.red,bc,w-4,h-4,this);
        红士2.setChessType("红色");
        红帅=new ChessPiece("帅",Color.red,bc,w-4,h-4,this);
        红帅.setChessType("红色");
        红兵1=new ChessPiece("兵",Color.red,bc,w-4,h-4,this);
        红兵1.setChessType("红色");
        红兵2=new ChessPiece("兵",Color.red,bc,w-4,h-4,this);
        红兵2.setChessType("红色");
        红兵3=new ChessPiece("兵",Color.red,bc,w-4,h-4,this);
        红兵3.setChessType("红色");
        红兵4=new ChessPiece("兵",Color.red,bc,w-4,h-4,this);
        红兵4.setChessType("红色");
        红兵5=new ChessPiece("兵",Color.red,bc,w-4,h-4,this);
        红兵5.setChessType("红色");
         
        黑将=new ChessPiece("将",Color.blue,bc,w-4,h-4,this);
        黑将.setChessType("蓝色");
        黑士1=new ChessPiece("士",Color.blue,bc,w-4,h-4,this);
        黑士1.setChessType("蓝色");
        黑士2=new ChessPiece("士",Color.blue,bc,w-4,h-4,this);
        黑士2.setChessType("蓝色");
        黑车1=new ChessPiece("车",Color.blue,bc,w-4,h-4,this);
        黑车1.setChessType("蓝色");
        黑车2=new ChessPiece("车",Color.blue,bc,w-4,h-4,this);
        黑车2.setChessType("蓝色");
        黑炮1=new ChessPiece("炮",Color.blue,bc,w-4,h-4,this);
        黑炮1.setChessType("蓝色");
        黑炮2=new ChessPiece("炮",Color.blue,bc,w-4,h-4,this);
        黑炮2.setChessType("蓝色");
        黑象1=new ChessPiece("象",Color.blue,bc,w-4,h-4,this);
        黑象1.setChessType("蓝色");
        黑象2=new ChessPiece("象",Color.blue,bc,w-4,h-4,this);
        黑象2.setChessType("蓝色");
        黑马1=new ChessPiece("马",Color.blue,bc,w-4,h-4,this);
        黑马1.setChessType("蓝色"); 
        黑马2=new ChessPiece("马",Color.blue,bc,w-4,h-4,this);
        黑马2.setChessType("蓝色"); 
        黑卒1=new ChessPiece("卒",Color.blue,bc,w-4,h-4,this);
        黑卒1.setChessType("蓝色");
        黑卒2=new ChessPiece("卒",Color.blue,bc,w-4,h-4,this);
        黑卒2.setChessType("蓝色");
        黑卒3=new ChessPiece("卒",Color.blue,bc,w-4,h-4,this);
        黑卒3.setChessType("蓝色");
        黑卒4=new ChessPiece("卒",Color.blue,bc,w-4,h-4,this);
        黑卒4.setChessType("蓝色");
        黑卒5=new ChessPiece("卒",Color.blue,bc,w-4,h-4,this);
        黑卒5.setChessType("蓝色");
        point[1][10].setPiece(红车1,this);
        point[2][10].setPiece(红马1,this);
        point[3][10].setPiece(红相1,this);
        point[4][10].setPiece(红士1,this);
        point[5][10].setPiece(红帅,this);
        point[6][10].setPiece(红士2,this);
        point[7][10].setPiece(红相2,this);
        point[8][10].setPiece(红马2,this);
        point[9][10].setPiece(红车2,this);
        point[2][8].setPiece(红炮1,this);
        point[8][8].setPiece(红炮2,this);  
        point[1][7].setPiece(红兵1,this);
        point[3][7].setPiece(红兵2,this);
        point[5][7].setPiece(红兵3,this);
        point[7][7].setPiece(红兵4,this);
        point[9][7].setPiece(红兵5,this);

        point[1][1].setPiece(黑车1,this);
        point[2][1].setPiece(黑马1,this);
        point[3][1].setPiece(黑象1,this);
        point[4][1].setPiece(黑士1,this);
        point[5][1].setPiece(黑将,this);
        point[6][1].setPiece(黑士2,this);
        point[7][1].setPiece(黑象2,this);
        point[8][1].setPiece(黑马2,this);
        point[9][1].setPiece(黑车2,this);
        point[2][3].setPiece(黑炮1,this);
        point[8][3].setPiece(黑炮2,this);
        point[1][4].setPiece(黑卒1,this);
        point[3][4].setPiece(黑卒2,this);
        point[5][4].setPiece(黑卒3,this);
        point[7][4].setPiece(黑卒4,this);
        point[9][4].setPiece(黑卒5,this);
    
    }
  public void paintComponent(Graphics g)    //绘制棋盘
   {
     super.paintComponent(g);
      for(int j=1;j<=yz;j++)   
       {
          g.drawLine(point[1][j].x,point[1][j].y,point[xz][j].x,point[xz][j].y); 
       }
     for(int i=1;i<=xz;i++)     
       {
         if(i!=1&&i!=xz)
          {
            g.drawLine(point[i][1].x,point[i][1].y,point[i][yz-5].x,point[i][yz-5].y);
            g.drawLine(point[i][yz-4].x,point[i][yz-4].y,point[i][yz].x,point[i][yz].y); 
          }
         else
          {
            g.drawLine(point[i][1].x,point[i][1].y,point[i][yz].x,point[i][yz].y);
          }
       }
      
       g.drawLine(point[4][1].x,point[4][1].y,point[6][3].x,point[6][3].y);
       g.drawLine(point[6][1].x,point[6][1].y,point[4][3].x,point[4][3].y);
       g.drawLine(point[4][8].x,point[4][8].y,point[6][yz].x,point[6][yz].y);
       g.drawLine(point[4][yz].x,point[4][yz].y,point[6][8].x,point[6][8].y);
     
       for(int i=1;i<=xz;i++)
       { 
          g.drawString(""+i,i*unitWidth,unitHeight/2);
       }
       int j=1;
      for(char c='A';c<='J';c++)
       { 
          g.drawString(""+c,unitWidth/4,j*unitHeight);
          j++;
       }
         
   } 
  public void mousePressed(MouseEvent e)     //返回棋子
  { 
    ChessPiece piece=null;
    Rectangle rect=null;
    if(e.getSource()==this)
        move=false;
    if(move==false)
      if(e.getSource() instanceof ChessPiece)
       {
         piece=(ChessPiece)e.getSource();   
         startx=piece.getBounds().x;        
         starty=piece.getBounds().y;     
         
          rect=piece.getBounds();
          for(int i=1;i<=xz;i++)
            {
              for(int j=1;j<=yz;j++)
                {
                  int x=point[i][j].getX();
                  int y=point[i][j].getY();
                  if(rect.contains(x,y))
                   {                   
                     starti=i;
                     startj=j;
                     break;
                   }
                  
                }
            }
       }
  } 
 public void mouseMoved(MouseEvent e)
  { 
  }
 public void mouseDragged(MouseEvent e)     //拖动棋子移动
  {
 
    ChessPiece piece=null;
       if(e.getSource() instanceof ChessPiece)
         {
           piece=(ChessPiece)e.getSource();   
           
           move=true; 
           
           e=SwingUtilities.convertMouseEvent(piece,e,this); 
         }
    
       if(e.getSource()==this)
        {
          if(move&&piece!=null)
           {
            x=e.getX(); 
            y=e.getY();
            if(redgo&&((piece.chessType()).equals("红色")))
               {
                 piece.setLocation(x-piece.getWidth()/2,y-piece.getHeight()/2);
               }
            if(blackgo&&(piece.chessType().equals("黑色")))
               {
                 piece.setLocation(x-piece.getWidth()/2,y-piece.getHeight()/2);
               }
           }
        }
 }
 public void mouseReleased(MouseEvent e)   //将棋子放到棋点
  { 
    ChessPiece piece=null;
    move=false;
    Rectangle rect=null;
    if(e.getSource() instanceof ChessPiece)
      {
        piece=(ChessPiece)e.getSource();   
        rect=piece.getBounds();
         
        e=SwingUtilities.convertMouseEvent(piece,e,this); 
      }
    if(e.getSource()==this)
     {
        boolean containChessPoint=false;
        int x=0,y=0;
        int m=0,n=0;
        if(piece!=null)
         { 
          for(int i=1;i<=xz;i++)
            {
              for(int j=1;j<=yz;j++)
                {
                  x=point[i][j].getX();
                  y=point[i][j].getY();
                  if(rect.contains(x,y))
                   { 
                    
                     containChessPoint=true;
                     m=i;
                     n=j;
                     break;
                   }
                  
                }
            }
         }
        if(piece!=null&&containChessPoint)
         {
           Color pieceColor=piece.getColor();
          if(point[m][n].isPiece()) 
            { 
               Color c=(point[m][n].getPiece()).getColor();
               if(pieceColor.getRGB()==c.getRGB())
                   { 
                     piece.setLocation(startx,starty);
                     
                     (point[starti][startj]).setHaveChess(true);
                   }                  
               else
                   { 
                      boolean ok=rule.movePieceRule(piece,starti,startj, m,n);
                      if(ok)
                       {                 
                            ChessPiece pieceRemoved=point[m][n].getPiece();
                            point[m][n].reMovePiece(pieceRemoved,this);
                            point[m][n].setPiece(piece,this);
                            (point[starti][startj]).setHaveChess(false);
                            record.recordQipu(piece,starti,startj,m,n) ;
                            record.recordEatChess(pieceRemoved)   ;      
                             if(piece.chessType().equals("红色"))
                                {
                                   redgo=false;
                                   blackgo=true;
                                }
                             if(piece.chessType().equals("蓝色"))
                                {
                                   blackgo=false;
                                   redgo=true;
                               }
                           validate();
                           repaint();
                       }
                      else    
                       {
                           piece.setLocation(startx,starty);
                           (point[starti][startj]).setHaveChess(true);
                       }
                   }
                 
            }
          else
            {
              
               boolean ok=rule.movePieceRule(piece,starti,startj, m,n);
               if(ok)
                 {
                    point[m][n].setPiece(piece,this);
                   (point[starti][startj]).setHaveChess(false);
                   record.recordQipu(piece,starti,startj,m,n) ;
                   record.recordEatChess("没吃棋子");        
                     if(piece.chessType().equals("红色"))
                       {
                          redgo=false;
                         blackgo=true;
                       }
                      if(piece.chessType().equals("蓝色"))
                       {
                         blackgo=false;
                         redgo=true;
                       }
                 }
               else     
                 {
                   piece.setLocation(startx,starty);
                   (point[starti][startj]).setHaveChess(true);
                 }
            }
        }
     if(piece!=null&&!containChessPoint)        
       {        
          piece.setLocation(startx,starty);
          (point[starti][startj]).setHaveChess(true);
       }
    }  
  }
 public void mouseEntered(MouseEvent e)
  {
  }
 public void mouseExited(MouseEvent e)
  { 
  }
 public void mouseClicked(MouseEvent e)
  { 
  }
}
