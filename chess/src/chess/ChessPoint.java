/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

/**
 *
 * @author Administrator
 */
public class ChessPoint                   //创建棋点对象
{
   int x,y;
   boolean havechess;
   ChessPiece piece=null;
   ChessBoard board=null;
   public ChessPoint(int x,int y,boolean yn)
   {
      this.x=x;
      this.y=y;
      havechess=yn;
   }
  public boolean isPiece()                      //判断棋点是否有棋子
  {
    return havechess;
  }
  public void setHaveChess(boolean yn)                //设置棋点上是否有棋子
  {
    havechess=yn;
  }
 
  public int getX()
  {
    return x;
  }
  public int getY()
  {
    return y;
  }
  public void setPiece(ChessPiece piece,ChessBoard board)          //在棋点上放置棋子
  {
     this.board=board;
     this.piece=piece;
     board.add(piece);
     int w=(board.unitWidth);
     int h=(board.unitHeight);
     piece.setBounds(x-w/2,y-h/2,w,h);
     havechess=true;
     board.validate(); 
  }
  public ChessPiece getPiece()                        //获取棋点上的棋子
  {
     return piece;
  }
  public void reMovePiece(ChessPiece piece,ChessBoard board)         //移掉棋点上的棋子
  {
     this.board=board;
     this.piece=piece;
     board.remove(piece);
     board.validate();
     havechess=false;
  }
}

