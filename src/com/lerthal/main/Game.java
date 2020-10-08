package com.lerthal.main;

import com.lerthal.entities.Enemy;
import com.lerthal.entities.Entity;
import com.lerthal.entities.Player;
import com.lerthal.entities.Shoot;
import com.lerthal.graficos.Spritesheet;
import com.lerthal.graficos.UI;
import com.lerthal.world.World;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable
{
  
  public static Game game;
  public static JFrame frame;
  private Thread thread;
  private boolean isRunning = true;
  public static final int WIDTH = 240;
  public static final int HEIGHT = 160;
  public static final int SCALE = 4;
  private int CUR_LEVEL = 1, MAX_LEVEL = 3;
  public static String gameState = "Normal";
  private boolean showMessageGameOver = true;
  private boolean showMessageVictory = true;
  private int framesGameOver = 0;
  public static boolean restartGame = false;
  
  private BufferedImage image;
  
  public void initFrame()
  {
    frame = new JFrame("MiniJam2");
    frame.add(this);
    frame.setResizable(false);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
  
  public static List<Entity> entities;
  public static List<Enemy> enemies;
  public static List<Shoot> bullets;
  public static Player player;
  public static Enemy enemy;
  public static Spritesheet spritesheet;
  public static World world;
  public static Random rand;
  public UI ui;
  
  public Menu menu;
  public MenuPause menuPause;
  
  public Game()
  {
    Sound.musicBackground.loop();
    Sound.musicBackground.setVolume(-39);
    rand = new Random();
    this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
    initFrame();
    /*
     * TimerUtils.runTaskTimer(20, new Runnable() {
     *
     * @Override public void run() { saveGame = true; showSaveIcon = true;
     * System.out.println("Jogo Salvo"); } });
     */
    // Inicializando Objetos :)
    ui = new UI();
    image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    entities = new ArrayList<Entity>();
    enemies = new ArrayList<Enemy>();
    bullets = new ArrayList<Shoot>();
    spritesheet = new Spritesheet("/spritesheet.png");
    player = new Player(16, 16, 16, 16, spritesheet.getSprite(32, 0, 16, 16));
    entities.add(player);
    world = new World("/lvl1.png");
    menu = new Menu();
    menuPause = new MenuPause();
    new Listeners(this);
    
  }
  
  public static void main(String[] args)
  {
    game = new Game();
    game.start();
  }
  
  public void tick()
  {
    if(gameState == "Normal")
    {
      /*
       * if (saveGame) { //saveGame = false; String[] opt1 = { "level", "life" };
       * int[] opt2 = { this.CUR_LEVEL, (int) player.life }; Menu.saveGame(opt1, opt2,
       * 10); }
       */
      restartGame = false;
      for(int i = 0; i < entities.size(); i++)
      {
        Entity e = entities.get(i);
        e.tick();
      }
      
      for(int i = 0; i < bullets.size(); i++)
      {
        bullets.get(i).tick();
      }
      
      if(enemies.size() == 0)
      {
        // Avan�ar de Lvl
        CUR_LEVEL++;
        if(CUR_LEVEL > MAX_LEVEL)
        {
          gameState = "Victory";
          return;
        }
        String newWorld = "lvl" + CUR_LEVEL + ".png";
        World.restartGame(newWorld);
      }
      
    } else if(gameState == "Game_Over")
    {
      this.framesGameOver++;
      if(this.framesGameOver == 37)
      {
        this.framesGameOver = 0;
        if(showMessageGameOver)
        {
          showMessageGameOver = false;
        } else
        {
          showMessageGameOver = true;
        }
      }
      
    } else if(gameState == "Menu")
    {
      menu.tick();
    } else if(gameState == "Pause")
    {
      menuPause.tick();
    }
    
    if(restartGame)
    {
      restartGame = false;
      this.gameState = "Normal";
      CUR_LEVEL = 1;
      String newWorld = "lvl" + CUR_LEVEL + ".png";
      World.restartGame(newWorld);
    }
    
    if(gameState == "Menu" || gameState == "Guide")
    {
      Sound.musicGame.loop();
      Sound.musicGame.setVolume(-25);
    }
    
  }
  
  public void render()
  {
    BufferStrategy bs = this.getBufferStrategy();
    if(bs == null)
    {
      this.createBufferStrategy(3);
      return;
    }
    
    Graphics g = image.getGraphics();
    g.setColor(Color.DARK_GRAY);
    g.fillRect(0, 0, WIDTH, HEIGHT);
    // g.drawImage(player[curAnimation] , 20 , 20 , null);
    
    /* Renderiza��o do jogo */
    world.render(g);
    Collections.sort(entities, Entity.nodeSorter);
    for(int i = 0; i < entities.size(); i++)
    {
      Entity e = entities.get(i);
      e.render(g);
    }
    for(int i = 0; i < bullets.size(); i++)
    {
      bullets.get(i).render(g);
    }
    ui.render(g);
    
    /**/
    
    g.dispose();
    g = bs.getDrawGraphics();
    g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
    if(gameState == "Game_Over")
    {
      Graphics2D g2 = (Graphics2D) g;
      g2.setColor(new Color(0, 0, 0, 50));
      g2.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
      g2.setFont(new Font("arial", Font.BOLD, 40));
      g2.setColor(Color.white);
      g2.drawString("Voc� morreu!", 380, 300);
      g2.setFont(new Font("arial", Font.BOLD, 40));
      if(showMessageGameOver)
      {
        g2.drawString(">Aperte o bot�o X para reiniciar<", 200, 345);
      }
    }
    
    if(gameState == "Victory")
    {
      Graphics2D g2 = (Graphics2D) g;
      g2.setColor(new Color(0, 0, 0, 50));
      g2.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
      g2.setFont(new Font("arial", Font.BOLD, 30));
      g2.setColor(Color.white);
      g2.drawString("Parab�ns, voc� venceu! :) ", 300, 300);
      g2.setFont(new Font("arial", Font.BOLD, 25));
      if(showMessageVictory)
      {
        g2.drawString("> Aperte M para sair do jogo! :) <", 240, 345);
      }
    } else if(gameState == "Menu")
    {
      menu.render(g);
    } else if(gameState == "Pause")
    {
      menuPause.render(g);
    }
    
    bs.show();
    
  }
  
  public synchronized void start()
  {
    Thread thread = new Thread(this);
    thread.start();
    isRunning = true;
  }
  
  public synchronized void stop()
  {
    isRunning = false;
    try
    {
      thread.join();
    }
    catch(InterruptedException e)
    {
      e.printStackTrace();
    }
  }
  
  @Override
  public void run()
  {
    requestFocus();
    long lastTime = System.nanoTime();
    double amountOfTicks = 60;
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    int frames = 0;
    double timer = System.currentTimeMillis();
    while(isRunning)
    {
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      
      if(delta >= 1)
      {
        tick();
        render();
        frames++;
        delta--;
      }
      if(System.currentTimeMillis() - timer >= 1000)
      {
        System.out.println("FPS: " + frames);
        frames = 0;
        timer += 1000;
      }
      
    }
    
    stop();
  }
}
