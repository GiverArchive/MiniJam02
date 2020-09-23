package com.lerthal.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.List;

import com.lerthal.main.Game;
import com.lerthal.world.Camera;
import com.lerthal.world.Node;
import com.lerthal.world.Vector2i;
import com.lerthal.world.World;

public class Entity {

	public static BufferedImage LIFEBOX_EN = Game.spritesheet.getSprite(64, 112, 16, 16);
	public static BufferedImage WEAPON_EN = Game.spritesheet.getSprite(16, 64, 16, 16);
	public static BufferedImage AMMO_EN = Game.spritesheet.getSprite(0, 96, 16, 16);
	public static BufferedImage ENEMY_EN = Game.spritesheet.getSprite(96, 80, 16, 16);

	protected double x;
	protected double y;
	protected int width;
	protected int height;
	private BufferedImage sprite;

	public int maskX;
	public int maskY;
	public int mwidth;
	public int mheight;

	public int depth;

	protected List<Node> path;

	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;

		this.maskX = 0;
		this.maskY = 0;
		this.mwidth = width;
		this.mheight = height;
	}

	public static Comparator<Entity> nodeSorter = new Comparator<Entity>() {

		@Override
		public int compare(Entity n0, Entity n1) {
			if (n1.depth < n0.depth)
				return +1;
			if (n1.depth > n0.depth)
				return -1;
			return 0;
		}

	};

	public void setMask(int maskX, int maskY, int mwidth, int mheight) {
		this.maskX = maskX;
		this.maskY = maskY;
		this.mwidth = mwidth;
		this.mheight = mheight;
	}

	public int getX() {
		return (int) this.x;
	}

	public int getY() {
		return (int) this.y;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setX(int newX) {
		this.x = newX;
	}

	public void setY(int newY) {
		this.y = newY;
	}

	public void tick() {

	}

	public static boolean isColliding(Entity e1, Entity e2) {
		Rectangle e1Mask = new Rectangle(e1.getX() + e1.maskX, e1.getY() + e1.maskY, e1.mwidth, e1.mheight);
		Rectangle e2Mask = new Rectangle(e2.getX() + e2.maskX, e2.getY() + e2.maskY, e2.mwidth, e2.mheight);

		return e1Mask.intersects(e2Mask);
	}

	public boolean isColliding(int xNext, int yNext) {
		Rectangle currentEnemy = new Rectangle(xNext, yNext, World.TILE_SIZE, World.TILE_SIZE);
		for (int i = 0; i < Game.enemies.size(); i++) {
			Enemy e = Game.enemies.get(i);
			if (e == this) {
				continue;

			}
			Rectangle targetEnemy = new Rectangle(e.getX(), e.getY(), World.TILE_SIZE, World.TILE_SIZE);
			if (currentEnemy.intersects(targetEnemy)) {
				return true;
			}

		}
		return false;
	}

	public void followPath(List<Node> path) {
		if (path != null) {
			if (path.size() > 0) {
				Vector2i target = path.get(path.size() - 1).tile;
				// xprev= x;
				// yprev = y;
				if (x < target.x * 16 && !isColliding(this.getX() + 1 , this.getY())) {
					x++;
				} else if (x > target.x * 16 && !isColliding(this.getX() - 1 , this.getY())) {
					x--;
				}

				if (y < target.y * 16 && !isColliding(this.getX() , this.getY() + 1)) {
					y++;
				} else if (y > target.y * 16 && !isColliding(this.getX() + 1 , this.getY() - 1)) {
					y--;
				}

				if (x == target.x * 16 && y == target.y * 16) {
					path.remove(path.size() - 1);
				}

			}
		}

	}

	public void render(Graphics g) {
		g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, null);
		/* MASK */
		// g.setColor(Color.red);
		// g.fillRect(this.getX()+ maskX- Camera.x, this.getY()+ maskY - Camera.y
		// ,mwidth , mheight);
	}

}
