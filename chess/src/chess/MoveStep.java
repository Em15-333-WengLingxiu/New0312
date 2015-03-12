/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;
import java.awt.*;
/**
 *
 * @author Administrator
 */

public class MoveStep implements java.io.Serializable
{
  public Point Start,End;
  public MoveStep(Point p1,Point p2)
  {
     Start=p1;
     End=p2;
  }
}
