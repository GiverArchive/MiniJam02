package com.lerthal.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Listeners implements KeyListener, MouseListener, MouseMotionListener
{
  public Listeners(Game game)
  {
    game.addKeyListener(this);
    game.addMouseListener(this);
    game.addMouseMotionListener(this);
  }
  @Override
  public void keyTyped(KeyEvent e) {
  }
  
  @Override
  public void keyPressed(KeyEvent e) {
    
    if (e.getKeyCode() == KeyEvent.VK_M && Game.enemies.size() == 0) {
      System.exit(0);
    }
    
    if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
      
      Game.player.right = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
      
      Game.player.left = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
      
      Game.player.up = true;
      
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
      
      Game.player.down = true;
    }
    
    if (e.getKeyCode() == KeyEvent.VK_X) {
      Game.restartGame = true;
    }
    
    if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
      if(Game.gameState.equals("Pause"))
        Game.gameState = "Normal";
      else if(Game.gameState.equals("Normal"))
        Game.gameState = "Pause";
    }
  }
  
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
      Game.player.right = false;
    }
    
    if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
      Game.player.left = false;
    }
    
    if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
      Game.player.up = false;
    }
    
    if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
      Game.player.down = false;
    }
  }
  
  @Override
  public void mouseClicked(MouseEvent e) {

  }
  
  @Override
  public void mousePressed(MouseEvent e) {
    Game.player.mouseShoot = true;
    Game.player.mx = (e.getX() / 4);
    Game.player.my = (e.getY() / 4);
    
    Menu.mousePressed = true;
    MenuPause.mousePressed = true;
  }
  
  @Override
  public void mouseReleased(MouseEvent e) {
  
  }
  
  @Override
  public void mouseEntered(MouseEvent e) {
  
  }
  
  @Override
  public void mouseExited(MouseEvent e) {
  
  }
  
  @Override
  public void mouseDragged(MouseEvent e) {
  
  }
  
  @Override
  public void mouseMoved(MouseEvent e) {
    Menu.mouseX = e.getX();
    Menu.mouseY = e.getY();
    
    MenuPause.mouseX = e.getX();
    MenuPause.mouseY = e.getY();
  }
}
