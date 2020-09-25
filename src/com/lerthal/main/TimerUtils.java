package com.lerthal.main;

import java.util.Timer;
import java.util.TimerTask;

public class TimerUtils {

	public static void runTaskTimer(int segundos, Runnable task) {
		new javax.swing.Timer(segundos * 1000, e -> task.run()).start();
	}

	public static void runLater(int segundos, TimerTask task) {
		new Timer().schedule(task, segundos * 1000);
	}
}
