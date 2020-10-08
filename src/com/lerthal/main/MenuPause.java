package com.lerthal.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MenuPause {

    public Option[] options = { opt_exit, opt_resume };
    
    public static int mouseX;
    public static int mouseY;
    public static boolean mousePressed = false;
    
    public static Option opt_resume;
    public static Option opt_exit;
    public static Option selected;
    
    public static BufferedImage menu_menu, b_resume, b_exit;
    public static BufferedImage s_resume, s_exit;
    
    static{
        
        String path = "/menus/pause/";
        
        try
        {
            menu_menu = ImageIO.read(Menu.class.getResource(path + "menu"));
            b_resume = ImageIO.read(Menu.class.getResource(path + "b_resume"));
            b_exit = ImageIO.read(Menu.class.getResource(path + "b_exit"));
            s_resume = ImageIO.read(Menu.class.getResource(path + "s_resume"));
            s_exit = ImageIO.read(Menu.class.getResource(path + "s_exit"));
        }
        catch(IOException e)
        {
            System.out.println("Falha ao ler Menu");
            System.exit(1);
        }
        
        opt_exit = new Option(0, 0, 10, 10, b_exit, s_exit);
        opt_resume = new Option(0, 100, 10, 10, s_resume, s_resume);
    }
    
    public void tick(){
        
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
