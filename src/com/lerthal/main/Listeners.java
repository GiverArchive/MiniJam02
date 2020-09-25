package com.lerthal.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Listeners implements KeyListener, MouseListener, MouseMotionListener
{
  private Game game;
  
  public Listeners(Game game)
  {
    this.game = game;
    game.addKeyListener(this);
    game.addMouseListener(this);
    game.addMouseMotionListener(this);
  }
  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void keyPressed(KeyEvent e) {
    
    if (e.getKeyCode() == KeyEvent.VK_M && Game.enemies.size() == 0) {
      System.exit(0);
    }
    
    if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
      Game.player.right = true;
    } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
      Game.player.left = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
      Game.player.up = true;
      
      if (Game.gameState == "Menu") {
        game.menu.up = true;
      }else if(Game.gameState == "Pause"){
        game.menuPause.up = true;
      }
      
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
      Game.player.down = true;
      
      if (Game.gameState == "Menu") {
        game.menu.down = true;
      }else if(Game.gameState == "Pause"){
        game.menuPause.down = true;
      }
    }
    
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      if (Game.gameState == "Menu") {
        game.menu.enter = true;
      }else if(Game.gameState == "Pause"){
        game.menuPause.enter = true;
      }
    }
    
    if (e.getKeyCode() == KeyEvent.VK_X) {
      Game.restartGame = true;
    }
    if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
      Game.gameState = "Pause";
    }
    
    if (Game.gameState == "Guide" && e.getKeyCode() == KeyEvent.VK_I ){
      Game.gameState = "Normal";
    }
    
    // Atirar com teclado
    /*
     * if (e.getKeyCode() == KeyEvent.VK_F) { player.isShooting = true; }
     */
    
  }
  
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
      Game.player.right = false;
    } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
      Game.player.left = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
      Game.player.up = false;
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
      Game.player.down = false;
    }
    
  }
  
  @Override
  public void mouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub
    
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
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub
    
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
