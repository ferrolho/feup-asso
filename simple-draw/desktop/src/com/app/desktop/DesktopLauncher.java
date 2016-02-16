package com.app.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.app.SimpleDraw;

public class DesktopLauncher {

	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		new LwjglApplication(new SimpleDraw(), config);
	}

}
