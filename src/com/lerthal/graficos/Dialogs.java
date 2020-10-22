package com.lerthal.graficos;

import com.lerthal.main.Game;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Dialogs
{
  public static BufferedImage ana_chorando;
  public static BufferedImage ana_padrao;
  public static BufferedImage ana_feliz;
  public static BufferedImage ana_pensando;
  
  public static BufferedImage eduardo_cry;
  public static BufferedImage eduardo_default;
  public static BufferedImage eduardo_preocupado;
  
  public static BufferedImage enzo_cry;
  public static BufferedImage enzo_default;
  public static BufferedImage enzo_preocupado;
  
  public static BufferedImage felipe_default;
  public static BufferedImage felipe_malicioso;
  public static BufferedImage felipe_surpreso;
  
  public static BufferedImage lucia_cry;
  public static BufferedImage lucia_default;
  public static BufferedImage lucia_sad;
  
  public static BufferedImage marcos_angry;
  public static BufferedImage marcos_assutado;
  public static BufferedImage marcos_frust;
  public static BufferedImage marcos_happy;
  public static BufferedImage marcos_neutral;
  public static BufferedImage marcos_quiet;
  public static BufferedImage marcos_sad;
  
  public static BufferedImage tiao_cry;
  public static BufferedImage tiao_default;
  public static BufferedImage tiao_final;
  public static BufferedImage tiao_happy;
  public static BufferedImage tiao_surprise;
  
  public static void init()
  {
    String pathA = "/dialog/ana_julia/";
    String pathEd = "/dialog/eduardo/";
    String pathEn = "/dialog/enzo/";
    String pathF = "/dialog/felipe/";
    String pathL = "/dialog/lucia/";
    String pathM = "/dialog/marcos/";
    String pathT = "/dialog/tiao/";
    
    try
    {
      ana_chorando = load(pathA, "cry");
      ana_padrao = load(pathA, "default");
      ana_feliz = load(pathA, "happy");
      ana_pensando = load(pathA, "thinking");
      
      eduardo_cry = load(pathEd, "cry");
      eduardo_default = load(pathEd, "default");
      eduardo_preocupado = load(pathEd, "preocupado");
      
      enzo_cry = load(pathEn, "cry");
      enzo_default = load(pathEn, "default");
      enzo_preocupado = load(pathEn, "preocupado");
      
      felipe_default = load(pathF, "default");
      felipe_malicioso = load(pathF, "malicioso");
      felipe_surpreso = load(pathF, "surprise");
      
      lucia_cry = load(pathL, "cry");
      lucia_default = load(pathL, "default");
      lucia_sad = load(pathL, "sad");
      
      marcos_angry = load(pathM, "angry");
      marcos_assutado = load(pathM, "assustado");
      marcos_frust = load(pathM, "frust");
      marcos_happy = load(pathM, "happy");
      marcos_neutral = load(pathM, "neutral");
      marcos_quiet = load(pathM, "quiet");
      marcos_sad = load(pathM, "sad");
      
      tiao_cry = load(pathT, "cry");
      tiao_default = load(pathT, "default");
      tiao_final = load(pathT, "final");
      tiao_happy = load(pathT, "happy");
      tiao_surprise = load(pathT, "surprise");
    }
    catch(IOException e)
    {
      e.printStackTrace();
      System.exit(1);
    }
  }
  
  private static BufferedImage load(String path, String name) throws IOException
  {
    return ImageIO.read(Game.class.getResource(path + name + ".png"));
  }
}
