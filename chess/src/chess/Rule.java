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


public class Rule                                      //创建走棋规则对象
{
  ChessBoard board=null;
  ChessPiece piece=null;
  ChessPoint point[][]; 
  int starti,startj,endi,endj;
  public Rule(ChessBoard board,ChessPoint point[][])
  {
    this.board=board;
    this.point=point;
  }
  public boolean movePieceRule(ChessPiece piece,int startI,int startJ,int endI,int endJ)            //判断走棋规则
  {
    this.piece=piece;
    this.starti=startI;
    this.startj=startJ;
    this.endi=endI;
    this.endj=endJ;
    int minI=Math.min(startI,endI);
    int maxI=Math.max(startI,endI);
    int minJ=Math.min(startJ,endJ); 
    int maxJ=Math.max(startJ,endJ);
    boolean cango=false;
   if(piece.getName().equals("车"))
    {
              if(startI==endI)   
                 {
                   int j=0;
                   for(j=minJ+1;j<=maxJ-1;j++)
                     { 
                        if(point[startI][j].isPiece())
                          {
                             cango=false;
                             break;
                          } 
                     }
                    if(j==maxJ)
                     {
                        cango=true;
                     }
                 }
               else if(startJ==endJ)  
                 {  
                    int i=0;
                    for(i=minI+1;i<=maxI-1;i++)
                     { 
                        if(point[i][startJ].isPiece())
                          {
                             cango=false;
                             break;
                          } 
                     }
                    if(i==maxI)
                     {
                          cango=true;
                     } 
                 }
                   else                  
                    {
                          cango=false;
                    }

    }
    else if(piece.getName().equals("马"))  
    {
        int xAxle=Math.abs(startI-endI);
        int yAxle=Math.abs(startJ-endJ);
       
       if(xAxle==2&&yAxle==1)
          { 
             if(endI>startI)
                {
                   if(point[startI+1][startJ].isPiece())
                     {
                       cango=false;
                     }
                   else
                     {
                       cango=true;
                     }
                }
              if(endI<startI)
                {
                   if(point[startI-1][startJ].isPiece())
                     {
                       cango=false;
                     }
                   else
                     {
                       cango=true;
                     }
                }
              
          } 
       
       else if(xAxle==1&&yAxle==2)       
          { 
             if(endJ>startJ)
                {
                   if(point[startI][startJ+1].isPiece())
                     {
                       cango=false;
                     }
                   else
                     {
                       cango=true;
                     }
                }
              if(endJ<startJ)
                {
                   if(point[startI][startJ-1].isPiece())
                     {
                       cango=false;
                     }
                   else
                     {
                       cango=true;
                     }
                }
              
          } 
       else 
          {
            cango=false;
          }
    }
   else if(piece.getName().equals("象"))  
    {
        int centerI=(startI+endI)/2;
        int centerJ=(startJ+endJ)/2;
        int xAxle=Math.abs(startI-endI);
        int yAxle=Math.abs(startJ-endJ); 
        if(xAxle==2&&yAxle==2&&endJ<=5)          
          {
             if(point[centerI][centerJ].isPiece())
                {            
                 cango=false;
                }
             else
                {
                  cango=true;
                }
          } 
       else
          {
            cango=false;
          }
    }
   else if(piece.getName().equals("相"))  
    {
        int centerI=(startI+endI)/2;
        int centerJ=(startJ+endJ)/2;
        int xAxle=Math.abs(startI-endI);
        int yAxle=Math.abs(startJ-endJ); 
        if(xAxle==2&&yAxle==2&&endJ>=6)          
          {
             if(point[centerI][centerJ].isPiece())
                {            
                  cango=false;
                }
             else
                {
                  cango=true;
                }
          } 
       else
          {
            cango=false;
          }
    }
   else if(piece.getName().equals("炮"))  
    {
       int number=0;
       if(startI==endI)   
                 {
                   int j=0;
                   for(j=minJ+1;j<=maxJ-1;j++)
                     { 
                        if(point[startI][j].isPiece())
                          {
                            number++;
                          } 
                     }
                   if(number>1)
                     {
                        cango=false;
                     }
                   else if(number==1)
                     {
                        if(point[endI][endJ].isPiece())
                          {
                            cango=true;
                          }
                     }
                   else if(number==0&&!point[endI][endJ].isPiece())
                     {
                        cango=true;
                     }
                 }
       else if(startJ==endJ)  
                 {  
                    int i=0;
                    for(i=minI+1;i<=maxI-1;i++)
                     { 
                        if(point[i][startJ].isPiece())
                          {
                            number++;
                          } 
                     }
                   if(number>1)
                     {
                        cango=false;
                     }
                   else if(number==1)
                     {
                        if(point[endI][endJ].isPiece())
                          {
                            cango=true;
                          }
                     }
                   else if(number==0&&!point[endI][endJ].isPiece())
                     {
                        cango=true;
                     }
                 }
       else 
                 {
                    cango=false;
                 }
    }
   else if(piece.getName().equals("兵"))  
    {
      int xAxle=Math.abs(startI-endI);
      int yAxle=Math.abs(startJ-endJ);
      
       if(endJ>=6)  
          {
            if(startJ-endJ==1&&xAxle==0) 
               {
                  cango=true; 
               }
            
            else
               {
                  cango=false; 
               }
          }
       else if(endJ<=5)  
          {
            if((startJ-endJ==1)&&(xAxle==0)) 
               {
                  cango=true; 
               }
            else if((endJ-startJ==0)&&(xAxle==1))
               {
                  cango=true; 
               }
            else
               {
                  cango=false; 
               }
          }
    }
   else if(piece.getName().equals("卒"))  
    {
      int xAxle=Math.abs(startI-endI);
      int yAxle=Math.abs(startJ-endJ);
      
       if(endJ<=5)  
          {
            if(endJ-startJ==1&&xAxle==0) 
               {
                  cango=true; 
               }
            else
               {
                  cango=false; 
               }
          }
       else if(endJ>=6)  
          {
            if((endJ-startJ==1)&&(xAxle==0))  
               {
                  cango=true; 
               }
            else if((endJ-startJ==0)&&(xAxle==1)) 
               {
                  cango=true; 
               }
            else
               {
                  cango=false; 
               }
          }
    }
  
   else if(piece.getName().equals("士"))  
    { 
      int xAxle=Math.abs(startI-endI);
      int yAxle=Math.abs(startJ-endJ);
      if(endI<=6&&endI>=4&&xAxle==1&&yAxle==1) 
         {
          cango=true; 
         }
      else
         {
           cango=false;
         }
    }
   else if((piece.getName().equals("帅"))||(piece.getName().equals("将")))  
    {
      int xAxle=Math.abs(startI-endI);
      int yAxle=Math.abs(startJ-endJ);
      if(endI<=6&&endI>=4)
          { 
            if((xAxle==1&&yAxle==0)||(xAxle==0&&yAxle==1))
              {
                cango=true; 
              }
            else
              {
                 cango=false;
              }
          } 
       else
          {
            cango=false;
          }
    }

   return cango;

  }
}
