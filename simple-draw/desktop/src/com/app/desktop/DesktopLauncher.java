package com.app.desktop;

import com.app.SimpleDraw;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {

	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Simple Draw";

		// Dimension screenDimension =
		// Toolkit.getDefaultToolkit().getScreenSize();
		// config.width = screenDimension.width;
		// config.height = screenDimension.height;

		// fullscreen
		// config.fullscreen = true;

		// vSync
		config.vSyncEnabled = true;

		// Multisample anti-aliasing
		config.samples = 4;

		new LwjglApplication(new SimpleDraw(), config);
	}

}
