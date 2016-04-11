package com.feup.asso.desktop;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.feup.asso.SimpleDraw;

public class DesktopLauncher {

	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Simple Draw";

		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		config.width = screenDimension.width * 2 / 3;
		config.height = screenDimension.height * 2 / 3;

		// fullscreen
		// config.fullscreen = true;

		// vSync
		config.vSyncEnabled = true;

		// Multisample anti-aliasing
		config.samples = 4;

		new LwjglApplication(new SimpleDraw(), config);
	}

}
