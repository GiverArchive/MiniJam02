package com.lerthal.main;

import com.lerthal.graficos.Dialogs;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Menu
{
  public static BufferedImage menu_menu, b_resume, b_exit, b_new, b_cred;
  public static BufferedImage s_resume, s_exit, s_new, s_cred;
  public static boolean mousePressed = false;
  
  public Option[] options = {opt_new, opt_resume, opt_exit, opt_cred};
  
  public static int mouseX;
  public static int mouseY;
  
  public static Option opt_new;
  public static Option opt_resume;
  public static Option opt_exit;
  public static Option opt_cred;
  public static Option selected;
  
  // Ignora, eh so pra corrigir o lag
  public static void init(){ MenuPause.init(); Dialogs.init(); }
  
  static{
    
    String path = "/menus/iniciar/";
    
    try
    {
      menu_menu = ImageIO.read(Menu.class.getResource(path + "menu.png"));
      b_new = ImageIO.read(Menu.class.getResource(path + "b_new.png"));
      b_resume = ImageIO.read(Menu.class.getResource(path + "b_resume.png"));
      b_exit = ImageIO.read(Menu.class.getResource(path + "b_exit.png"));
      b_cred = ImageIO.read(Menu.class.getResource(path + "b_cred.png"));
      s_new = ImageIO.read(Menu.class.getResource(path + "s_new.png"));
      s_resume = ImageIO.read(Menu.class.getResource(path + "s_resume.png"));
      s_exit = ImageIO.read(Menu.class.getResource(path + "s_exit.png"));
      s_cred = ImageIO.read(Menu.class.getResource(path + "s_cred.png"));
    }
    catch(IOException e)
    {
      System.out.println("Falha ao ler Menu");
      System.exit(1);
    }
  
    opt_cred = new Option(101 * 4, 137 * 4, 38 * 4, 15 * 4, b_cred, s_cred);
    opt_exit = new Option(121 * 4, 147 * 4, 38 * 4, 15 * 4, b_exit, s_exit);
    opt_new = new Option(88 * 4, 119 * 4, 64 * 4, 15 * 4, b_new, s_new);
    opt_resume = new Option(96 * 4, 100 * 4, 48 * 4, 15 * 4, b_resume, s_resume);
  }
  
  public void tick()
  {
    for(Option option : options)
    {
      if(mouseX > option.x && mouseX < option.x + option.width && mouseY > option.y && mouseY < option.y + option.height)
      {
        selected = option;
        break;
      }
      
      selected = null;
    }
    
    if(mousePressed)
    {
      if(selected != null)
      {
        selected.click();
      }
    }
  }
  
  public void render(Graphics g)
  {
    g.drawImage(menu_menu, 0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE, null);
    
    for(Option option : options)
    {
      option.render(g);
    }
  }
}
