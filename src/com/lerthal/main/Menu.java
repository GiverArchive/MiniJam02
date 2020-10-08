package com.lerthal.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class Menu
{
  public String[] options = {"New Game", "Instructions", "Exit"};
  public int currentOption = 0;
  public int maxOption = options.length - 1;
  public boolean up, down, enter;
  public static boolean pause = false;
  public static BufferedImage startPlay, startGuide, startExit, instrucoes;
  
  public static List<Option> mouseOptions = new ArrayList<>();
  public static int mouseX;
  public static int mouseY;
  public static boolean mousePressed = false;
  
  public static Option start = new Option(96 * 4, 100 * 4, 48 * 4, 15 * 4); // poe as coordenas e o tamanho
  public static Option guide = new Option(88 * 4, 119 * 4, 64 * 4, 15 * 4); // poe as coordenas e o tamanho
  public static Option exit = new Option(101 * 4, 137 * 4, 38 * 4, 15 * 4); // Poe as coordenadas e o tamanho
  
  public static BufferedImage menu_menu, b_resume, b_exit, b_new, b_cred;
  public static BufferedImage s_resume, s_exit, s_new, s_cred;
  
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
    }
    catch(IOException e)
    {
      System.out.println("Falha ao ler Menu");
    }
  }
  
  public Menu()
  {
    mouseOptions.add(start);
    mouseOptions.add(guide);
    mouseOptions.add(exit);
    
  }
  
  public void tick()
  {
    
    if(mousePressed)
    {
      mousePressed = false;
      Option option;
      
      for(Option mouseOption : mouseOptions)
      {
        option = mouseOption;
        
        if(mouseX >= option.x && mouseX <= option.x + option.width && mouseY >= option.y && mouseY <= option.y + option.height)
        {
          if(option == exit)
          {
            System.exit(0);
          } else if(option == start)
          {
            // Lógica do start
            Game.gameState = "Normal";
            pause = false;
          } else if(option == guide)
          {
            Game.gameState = "Guide";
          }
        }
      }
    }
    
    if(up)
    {
      up = false;
      currentOption--;
      if(currentOption < 0)
      {
        currentOption = maxOption;
      }
    }
    if(down)
    {
      down = false;
      currentOption++;
      if(currentOption > maxOption)
      {
        currentOption = 0;
      }
    }
    
    if(enter)
    {
      enter = false;
      if(options[currentOption] == "New Game")
      {
        Game.gameState = "Normal";
        pause = false;
      } else if(options[currentOption] == "Exit")
      {
        System.exit(1);
      } else if(options[currentOption] == "Instructions")
      {
        Game.gameState = "Guide";
      }
    }
    
    Option option;
    for(int i = 0; i < mouseOptions.size(); i++)
    {
      option = mouseOptions.get(i);
      if(mouseX >= option.x && mouseX <= option.x + option.width && mouseY >= option.y && mouseY <= option.y + option.height)
      {
        if(option == exit)
        {
          currentOption = 2;
        } else if(option == start)
        {
          currentOption = 0;
        } else if(option == guide)
        {
          currentOption = 1;
          
        }
      }
    }
  }
  
  public void render(Graphics g)
  {
    
    if(options[currentOption] == "New Game")
    {
      g.drawImage(startPlay, 0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE, null);
    } else if(options[currentOption] == "Instructions")
    {
      g.drawImage(startGuide, 0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE, null);
    } else if(options[currentOption] == "Exit")
    {
      g.drawImage(startExit, 0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE, null);
    }
    
    
  }
}
