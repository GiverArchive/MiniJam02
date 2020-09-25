package com.lerthal.main;

import java.util.HashMap;

public class SaveFile
{
  private static HashMap<String, String> data = new HashMap<>();
  
  static public void loadFile()
  {
    // TODO
  }
  
  public static void saveFile()
  {
    // TODO
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
}
