/*
CSC 1054 Project 1
Grant Solomon
*/

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.Timer;

public class Project1 extends JPanel 
{
   // variables
   int x, y, rows, columns, xs=0, ys=0, jump, N=1, count=0, count2=0;
   ArrayList<ArrayList<GameObject>> map = new ArrayList<ArrayList<GameObject>>();
   Scanner read;
   boolean right, left, win;
   Player player;
   
   // constructor
   public Project1()
   {
      super();
      
      // timer and keylistener
      addKeyListener(new KeyEventDemo());
      setFocusable(true);
      Timer t = new Timer(10, new TimeListener());
      
      
      // try-catch for file reading
      try
      {
         // read from file
         read = new Scanner(new File("Project1Map.txt"));
         x = 10+(25*read.nextInt());
         y = 10+(25*read.nextInt());
         rows = read.nextInt();
         columns = read.nextInt();
         player = new Player(x,y);
         
         // fill map with data
         for(int i=0;i<rows;i++)
         {
            ArrayList<GameObject> list = new ArrayList<GameObject>();
            int ty = (10+(25*i));
            
            for(int j=0;j<columns;j++)
            {
               int temp = read.nextInt();
               int tx = (10+(25*j));
               if(temp==1)
               {
                  list.add(new Block(tx,ty));
               }
               else if(temp==2)
               {
                  list.add(new VictoryBlock(tx,ty));
               }
               else
               {
                  list.add(null);
               }
            }
            map.add(list);
         }
         t.start();
         
      }
      catch(FileNotFoundException fnfe)
      {
      
      }
      
      setPreferredSize(new Dimension(820,620));
      setBackground(Color.GRAY);
   }
   
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      
      // make black border
      g.setColor(Color.BLACK);
      g.fillRect(0,0,820,10);
      g.fillRect(0,0,10,620);
      g.fillRect(0,610,820,10);
      g.fillRect(810,0,10,620);
      
      // draw blocks and VictoryBlocks
      for(int i=0;i<map.size();i++)
      {
         for(int j=0;j<map.get(i).size();j++)
         {
            if(map.get(i).get(j)!=null)
            {
               map.get(i).get(j).draw(g);
            }
         }
      }
      
      // draw player
      player.draw(g);
   }
   
   public class KeyEventDemo implements KeyListener
   {
      public void keyTyped(KeyEvent e){}
      public void keyReleased(KeyEvent e) 
      {
         if(e.getKeyCode() == KeyEvent.VK_A)
         {
            left = false;
         }
         if(e.getKeyCode() == KeyEvent.VK_D)
         {
            right = false;
         }
      }
      public void keyPressed(KeyEvent e) 
      {
         if(e.getKeyCode() == KeyEvent.VK_A)
         {
            left = true;
         }
         if(e.getKeyCode() == KeyEvent.VK_D)
         {
            right = true;   
         }
         if(e.getKeyCode() == KeyEvent.VK_W)
         {
            if(player.isOnGround(map))
            {
               jump = 7;
            }
         }
         
      repaint();
      }
   }
   
   public class TimeListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         // set speed/direction
         if(left)
         {
            player.move(-1,0,map);
         }
         if(right)
         {
            player.move(1,0,map);
         }
         
         // jump
         for(int i=0;i<jump;i++)
         {
            if(jump > 0)
            {
               N=0;
               if(!player.move(0,-1,map))
               {
                  jump=0;
               }
               repaint();
            }
         }
         if(jump>0)
         {
            if(count2>10)
            {
               jump--;
               count2=0;
            }
         }
         count2++;
         if(player.checkCollide(map.get((y-10)/25).get((x-10)/25)) || player.checkCollide(map.get((y-10)/25).get(((x-10)/25)+1)))
         {
            jump=0;
         }
         
         // gravity
         for(int i=0;i<N;i++)
         {
            player.move(0,1,map);
            repaint();
         }
         if(N<7)
         {
            if(count>20)
            {
               N++;
               count=0;
            }
         }
         count++;
         if(player.isOnGround(map))
         {
            N=1;
         }
         repaint();
         
      }
   }
   
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Project 1");
      frame.setSize(836,659);
      frame.add(new Project1());
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }
}