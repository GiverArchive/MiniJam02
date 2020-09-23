package com.lerthal.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.lerthal.main.Game;
import com.lerthal.world.Camera;

public class AmmoBox extends Entity {

	private BufferedImage[] sprites;
	private int frames = 0, maxFrames = 12, index = 0, maxIndex = 5;

	public AmmoBox(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);


	}

	public void tick() {
		frames++;
		if (frames == maxFrames) {
			frames = 0;
			index++;

		}
		if (index > maxIndex) {
			index = 0;
		}
	}

	public void render(Graphics g) {
		//g.drawImage(sprites[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
	}

}
