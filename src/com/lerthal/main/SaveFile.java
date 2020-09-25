package com.lerthal.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Base64;
import java.util.HashMap;

public class SaveFile
{
  private static HashMap<String, String> data = new HashMap<>();
  
  private static void putDefaults()
  {
    data.put("player-life", "5");
    data.put("ammo", "0");
    data.put("level", "1");
    data.put("lifepacks", "0");
    data.put("helmet", "naoequiped");
  }
  
  static public void loadFile() throws Throwable
  {
    putDefaults();
    
    File folder = new File(System.getProperty("user.home"), "Gamezinho");
    
    if(!folder.exists()) return;
    
    File file = new File(folder, "Save.dat");
    
    if(!file.exists()) return;
  
    BufferedReader reader = new BufferedReader(new FileReader(file));
    
    data.put("player-life", reader.readLine());
    data.put("ammo", reader.readLine());
    data.put("level", reader.readLine());
    data.put("lifepacks", reader.readLine());
    data.put("helmet", reader.readLine());
    
    reader.close();
  }
  
  public static void saveFile() throws Throwable
  {
    File folder = new File(System.getProperty("user.home", "Gamezinho"));
    
    if(!folder.exists()) folder.mkdir();
    
    File file = new File(folder, "Save.dat");
    
    if(file.exists()) file.delete();
  
    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
    
    writer.write(new String(Base64.getEncoder().encode(data.get("player-life").getBytes())));
    writer.newLine();
    writer.write(new String(Base64.getEncoder().encode(data.get("ammo").getBytes())));
    writer.newLine();
    writer.write(new String(Base64.getEncoder().encode(data.get("level").getBytes())));
    writer.newLine();
    writer.write(new String(Base64.getEncoder().encode(data.get("lifepacks").getBytes())));
    writer.newLine();
    writer.write(new String(Base64.getEncoder().encode(data.get("helmet").getBytes())));
    writer.flush();
    writer.close();
  }
  
  public static int getPlayerLife()
  {
    return Integer.parseInt(data.get("player-life"));
  }
  
  public static void setPlayerLife(int toSet)
  {
    data.put("player-life", String.valueOf(toSet));
  }
  
  public static void setLevel(int level)
  {
    data.put("level", String.valueOf(level));
  }
  
  public static int getLevel()
  {
    return Integer.parseInt(data.get("level"));
  }
  
  public static int getAmmo()
  {
    return Integer.parseInt(data.get("ammo"));
  }
  
  public static void setAmmo(int ammo)
  {
    data.put("ammo", String.valueOf(ammo));
  }
  
  public static boolean hasHelmet()
  {
    return data.get("helmet").equals("equipped");
  }
  
  public static void setHelmet(boolean helmet)
  {
    data.put("helmet", helmet ? "equipped" : "naoequipped");
  }
  
  public static int lifePacks()
  {
    return Integer.parseInt(data.get("lifepacks"));
  }
  
  public static void setLifePacks(int lifePacks)
  {
    data.put("lifepacks", String.valueOf(lifePacks));
  }
  
  static{
    putDefaults();
  }
}
