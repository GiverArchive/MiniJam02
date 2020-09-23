package com.lerthal.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.lerthal.entities.Entity;
import com.lerthal.entities.Player;
import com.lerthal.main.Game;

public class UI {

	private int saveIconFrames = 0 , maxSaveFrames = 150;
	
	
	/*
	 	public void showSavingIcon(Graphics g){
		{
            saveIconFrames++;
            
            if(saveIconFrames >= maxSaveFrames)
            {
                saveIconFrames = 0;
               Game.showSaveIcon = false;
            }
            
            g.drawImage(Entity.savingIcon, Game.WIDTH - 20, Game.HEIGHT - 20, null);
        }
	}
	
	*/
	
	public void render(Graphics g){
		g.setFont((new Font("arial", Font.BOLD, 10)));
		g.drawString("" + Game.player.ammo, 220, 14);
		// g.drawString(""+player.speed, 10, 10);
		
		/*if(Game.showSaveIcon)
        {
            showSavingIcon(g);
        }
        */
	}
	
}
