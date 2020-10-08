package com.lerthal.main;

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
  
  static{
    
    String path = "/menus/iniciar/";
    
    try
    {
      menu_menu = ImageIO.read(Menu.class.getResource(path + "menu"));
      b_new = ImageIO.read(Menu.class.getResource(path + "b_new"));
      b_resume = ImageIO.read(Menu.class.getResource(path + "b_resume"));
      b_exit = ImageIO.read(Menu.class.getResource(path + "b_exit"));
      b_cred = ImageIO.read(Menu.class.getResource(path + "b_cred"));
      s_new = ImageIO.read(Menu.class.getResource(path + "s_new"));
      s_resume = ImageIO.read(Menu.class.getResource(path + "s_resume"));
      s_exit = ImageIO.read(Menu.class.getResource(path + "s_exit"));
      s_cred = ImageIO.read(Menu.class.getResource(path + "s_cred"));
      
      opt_cred = new Option(101 * 4, 137 * 4, 38 * 4, 15 * 4, b_cred, s_cred);
      opt_exit = new Option(121 * 4, 147 * 4, 38 * 4, 15 * 4, b_exit, s_exit);
      opt_new = new Option(88 * 4, 119 * 4, 64 * 4, 15 * 4, b_new, s_new);
      opt_resume = new Option(96 * 4, 100 * 4, 48 * 4, 15 * 4, b_resume, s_resume);
    }
    catch(IOException e)
    {
      System.out.println("Falha ao ler Menu");
    }
  }
  
  public void tick()
  {
    for(Option option : options)
    {
      if(mouseX > option.x && mouseX < option.x + option.height && mouseY > option.y && mouseY < option.y + option.width)
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
