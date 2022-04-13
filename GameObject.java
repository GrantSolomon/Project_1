/*
Project 1 Game Object
Grant Solomon
*/

import java.awt.*;
import javax.swing.*;

public class GameObject
{
   // variables
   protected int x, y;
   protected Color c;
   
   // constructor
   public GameObject(int x_in, int y_in, Color c_in)
   {
      x=x_in;
      y=y_in;
      c=c_in;
   }
   
   // collide check
   public boolean checkCollide(GameObject o)
   {
      if(o == this|| o == null)
      {
         return false;
      }
      int topthis = y, topother = o.getY(), bottomthis = y+24, bottomother = o.getY()+24, leftthis = x, leftother = o.getX(), rightthis = x+24, rightother = o.getX()+24;
      if(o instanceof VictoryBlock)
      {
         JOptionPane op = new JOptionPane();
         op.showMessageDialog(null,"YOU WIN!", "CONGRATULATIONS", JOptionPane.INFORMATION_MESSAGE);
         System.exit(0);
      }
      return !((bottomthis<topother)||(topthis>bottomother)||(leftthis>rightother)||(rightthis<leftother));
      
   }
   
   // draw method
   public void draw(Graphics g)
   {
      g.setColor(c);
      g.fillRect(x,y,25,25);
      g.setColor(Color.BLACK);
      g.drawRect(x,y,25,25);
   }
   
   // accessors
   public int getX()
   {
      return x;
   }
   public int getY()
   {
      return y;
   }
   public Color getColor()
   {
      return c;
   }
   
}