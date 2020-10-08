package com.lerthal.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Option {
    public int x,y,width,height;
    public BufferedImage icon, icon2;
    private Runnable actor;
    
    public Option(int x , int y , int width, int height, BufferedImage icon, BufferedImage icon2)
    {
        this.icon = icon;
        this.icon2 = icon2;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void setActor(Runnable r)
    {
        actor = r;
    }
    
    public void click()
    {
        if(actor != null)
            actor.run();
    }
    
    public void render(Graphics g)
    {
        g.drawImage(Menu.selected == this ? icon2 : icon, x, y, width, height, null);
    }
}
