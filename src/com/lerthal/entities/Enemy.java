package com.lerthal.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.lerthal.main.Game;
import com.lerthal.main.Sound;
import com.lerthal.world.AStar;
import com.lerthal.world.Camera;
import com.lerthal.world.Vector2i;
import com.lerthal.world.World;

public class Enemy extends Entity {

	public static int speed = 1;
	public int life = 10;
	private boolean isDamaged = false;
	private int damageFrames = 10, damageCurrent = 0;

	private int frames = 0, maxFrames = 8, index = 0, maxIndex = 10;

	private BufferedImage[] sprites;

	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, null);

		sprites = new BufferedImage[11];
		sprites[0] = Game.spritesheet.getSprite(96, 80, 16, 16);

	}

	public void tick() {
		/*maskX = 6;
		maskY = 6;
		if(!isCollidingWithPlayer()) {
			if(path == null || path.size() == 0){
				Vector2i start = new Vector2i ((int)(x/16),(int)(y/16));
				Vector2i end = new Vector2i ((int)(Game.player.x/16),(int)(Game.player.y/16));
				path = AStar.findPath(Game.world, start, end);
			}
		
		} else {
			// Estamos colidindo
			if (Game.rand.nextInt(100) < 30) {
				Sound.hit.play();
				Game.player.life-=3;
				Game.player.isDamaged = true;
				// System.out.println("Vida: "+Game.player.life);
			}
		}
		if(new Random().nextInt(100) < 75) {
			followPath(path);
		}
		if(new Random().nextInt(100) < 5) {
			Vector2i start = new Vector2i ((int)(x/16),(int)(y/16));
			Vector2i end = new Vector2i ((int)(Game.player.x/16),(int)(Game.player.y/16));
			path = AStar.findPath(Game.world, start, end);
		}

		frames++;

		if (frames == maxFrames) {
			frames = 0;
			index++;

		}
		if (index > maxIndex) {
			index = 0;
		}

		checkCollisionWithBullet();

		if (life <= 0) {
			destroyEnemy();
		}
		
		if(isDamaged){
			this.damageCurrent++;
			if(this.damageCurrent == this.damageFrames) {
				this.damageCurrent = 0;
				isDamaged = false;
			}
		}
		*/

	}

	public void destroyEnemy() {
		Game.enemies.remove(this);
		Game.entities.remove(this);
		return;
	}

	public void checkCollisionWithBullet() {
		for (int i = 0; i < Game.bullets.size(); i++) {
			Entity e = Game.bullets.get(i);
			if (e instanceof Shoot) {
				if (Entity.isColliding(this, e)) {
					isDamaged = true;
					life--;
					Game.bullets.remove(i);
					return;
				}
			}
		}
	}

	public boolean isCollidingWithPlayer() {
		Rectangle currentEnemy = new Rectangle(this.getX(), this.getY(), World.TILE_SIZE, World.TILE_SIZE);
		Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(), 16, 16);

		return currentEnemy.intersects(player);
	}


	public void render(Graphics g) {
		if(!isDamaged) {
			g.drawImage(sprites[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}else {
			//sprite do inimigo tomando dano;
		}
		// super.render(g);
		 //g.fillRect(this.getX() + maskX - Camera.x,this.getY() + maskY - Camera.y, 8, 8);
		// g.setColor(Color.yellow);

	}
}
