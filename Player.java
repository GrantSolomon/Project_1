/*
Project 1 Player Class
Grant Solomon
*/

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Player extends GameObject
{
   // constructor
   public Player(int x, int y)
   {
      super(x,y,new Color(200,0,255));
   }
   
   // check if player is on ground
   public boolean isOnGround(ArrayList<ArrayList<GameObject>> map)
   {
      boolean crash = false;
      int cy=(y-10)/25, cx=(x-10)/25;
      y++;
      
      if(checkCollide(map.get(cy+1).get(cx)) || checkCollide(map.get(cy+1).get(cx+1)))
      {
         y--;
         return true;
      }
      
      return crash;
   }
   
   public boolean move(int x_in, int y_in, ArrayList<ArrayList<GameObject>> map)
   {
      boolean crash=false;
      x+=x_in;
      y+=y_in;
      int cy=(y-10)/25, cx=((x-10)/25);
      
      if(x_in<0)
      {
         if(checkCollide(map.get(cy).get(cx)) || checkCollide(map.get(cy+1).get(cx)))
         {
            x-=x_in;
            y-=y_in;
            crash=true;
         }
      }
      if(x_in>0)
      {
         if(checkCollide(map.get(cy).get(cx+1)) || checkCollide(map.get(cy+1).get(cx+1)))
         {
            x-=x_in;
            y-=y_in;
            crash=true;
         }
      }
      if(y_in<0)
      {
         if(checkCollide(map.get(cy).get(cx)) || checkCollide(map.get(cy).get(cx+1)))
         {
            x-=x_in;
            y-=y_in;
            crash=true;
         }
      }
      if(y_in>0)
      {
         if(checkCollide(map.get(cy+1).get(cx)) || checkCollide(map.get(cy+1).get(cx+1)))
         {
            x-=x_in;
            y-=y_in;
            crash=true;
         }
      }
      return !crash;
   }
   
   public void collides(ArrayList<ArrayList<GameObject>> map)
   {
      boolean win = false;
      int cy=(y-10)/25, cx=((x-10)/25);
      
      if(!move(-1,-1,map))
      {
         if(map.get(cy).get(cx-1) instanceof VictoryBlock || map.get(cy-1).get(cx-1) instanceof VictoryBlock || map.get(cy-1).get(cx) instanceof VictoryBlock)
         {
            win = true;
         }
      }
      else
      {
         move(1,1,map);
      }
      if(!move(1,1,map))
      {
         if(map.get(cy).get(cx+1) instanceof VictoryBlock || map.get(cy+1).get(cx+1) instanceof VictoryBlock || map.get(cy+1).get(cx) instanceof VictoryBlock)
         {
            win = true;
         }
      }
      else
      {
         move(-1,-1,map);
      }
   }
}