package com.lerthal.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.lerthal.entities.AmmoBox;
import com.lerthal.entities.Enemy;
import com.lerthal.entities.Entity;
import com.lerthal.entities.LifeBox;
import com.lerthal.entities.Particle;
import com.lerthal.entities.Weapon;
import com.lerthal.main.Game;

public class World {

	private BufferedImage map;
	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	public static final int TILE_SIZE = 16;

	public World(String path) {
		try {
			map = ImageIO.read(getClass().getResource(path));

			int[] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			for (int xx = 0; xx < map.getWidth(); xx++) {
				for (int yy = 0; yy < map.getHeight(); yy++) {

					tiles[xx + (yy * map.getWidth())] = new floorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);

					int pixelAtual = pixels[xx + (yy * map.getWidth())];

					if (pixelAtual == 0xFF000000) {
						// floor liso
						tiles[xx + (yy * map.getWidth())] = new floorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);
					}else if(pixelAtual == 0xFFffffff){
						tiles[xx + (yy * map.getWidth())] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL);
					}else if (pixelAtual == 0xFFdf7126) {
						// player
						Game.player.setMask(2, 2, 11, 14);
						Game.player.setX(xx * 16);
						Game.player.setY(yy * 16);

					} else if (pixelAtual == 0xFFac3232) {
						// enemy
						Enemy en = new Enemy(xx * 16, yy * 16, 16, 16, Entity.ENEMY_EN);
						Game.entities.add(en);
						Game.enemies.add(en);

					} else if (pixelAtual == 0xFF639bff) {
						// Weapon
						Game.entities.add(new Weapon(xx * 16, yy * 16, 16, 16, Entity.WEAPON_EN));
					} else if (pixelAtual == 0xFF99e550) {
						// LifeBox
						LifeBox lifeBox = new LifeBox(xx * 16, yy * 16, 16, 16, Entity.LIFEBOX_EN);
						lifeBox.setMask(0, 0, 12, 15);
						Game.entities.add(lifeBox);
					} else if (pixelAtual == 0xFFdf7126) {
						// WEAPON
						Game.entities.add(new Weapon(xx * 16, yy * 16, 16, 16, Entity.WEAPON_EN));
					} else if (pixelAtual == 0xFFfbf236) {
						// AmmoBox
						Game.entities.add(new AmmoBox(xx * 16, yy * 16, 16, 16, Entity.AMMO_EN));
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static boolean isFree(int xNext, int yNext) {
		int x1 = xNext / TILE_SIZE; // Pegando a proxima posi��o e convertendo pro formato de Tile;
		int y1 = yNext / TILE_SIZE;

		int x2 = (xNext + TILE_SIZE - 1) / TILE_SIZE;
		int y2 = yNext / TILE_SIZE;

		int x3 = xNext / TILE_SIZE;
		int y3 = (yNext + TILE_SIZE - 1) / TILE_SIZE;

		int x4 = (xNext + TILE_SIZE - 1) / TILE_SIZE;
		int y4 = (yNext + TILE_SIZE - 1) / TILE_SIZE;

		return !((tiles[x1 + (y1 * World.WIDTH)] instanceof WallTile)
				|| (tiles[x2 + (y2 * World.WIDTH)] instanceof WallTile)
				|| (tiles[x3 + (y3 * World.WIDTH)] instanceof WallTile)
				|| (tiles[x4 + (y4 * World.WIDTH)] instanceof WallTile));

	}
	
	public static void generateParticles(int amount , int x , int y){
		for(int i = 0; i < amount; i++){
			Game.entities.add(new Particle(x,y,1,1,null));
		}
	}
	
	
	public static boolean isFreeDynamic(int xNext, int yNext , int width , int height) {
		int x1 = xNext / TILE_SIZE; // Pegando a proxima posi��o e convertendo pro formato de Tile;
		int y1 = yNext / TILE_SIZE;

		int x2 = (xNext + width - 1) / TILE_SIZE;
		int y2 = yNext / TILE_SIZE;

		int x3 = xNext / TILE_SIZE;
		int y3 = (yNext + height - 1) / TILE_SIZE;

		int x4 = (xNext + width - 1) /  TILE_SIZE;
		int y4 = (yNext + height - 1) / TILE_SIZE;

		return !((tiles[x1 + (y1 * World.WIDTH)] instanceof WallTile)
				|| (tiles[x2 + (y2 * World.WIDTH)] instanceof WallTile)
				|| (tiles[x3 + (y3 * World.WIDTH)] instanceof WallTile)
				|| (tiles[x4 + (y4 * World.WIDTH)] instanceof WallTile));

	}

	public static void restartGame(String lvl) {
		Game.entities.clear();
		Game.enemies.clear();
		Game.world = new World("/" + lvl);
		Game.entities.add(Game.player);
	}

	public void render(Graphics g) {
		int xStart = Camera.x >> 4;
		int yStart = Camera.y >> 4;

		int xFinal = xStart + (Game.WIDTH >> 4);
		int yFinal = yStart + (Game.HEIGHT >> 4);

		for (int xx = xStart; xx <= xFinal; xx++) {
			for (int yy = yStart; yy <= yFinal; yy++) {
				if ((xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)) {
					continue;
				}

				Tile tile = tiles[xx + (yy * WIDTH)];
				tile.render(g);
			}
		}
	}
}
