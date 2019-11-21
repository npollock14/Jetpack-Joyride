import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MenuScene extends Scene {

	Button b1;
	Button b2;
	GameScene backgroundScene = new GameScene();

	@Override
	public void update(Point mPos, boolean[] keys, boolean[] keysToggled, boolean[] mouse, boolean[] mouseReleased) {
		this.mPos = mPos;
		this.keys = keys;
		this.keysToggled = keysToggled;
		this.mouse = mouse;
		backgroundScene.update(mPos, keys, keysToggled, mouse, mouseReleased);
		b1.update(this.mouse, this.mPos, this.mouseReleased);
		b2.update(this.mouse, this.mPos, this.mouseReleased);

		if (b1.clicked)
			Driver.state = 4;
		if (b2.clicked)
			Driver.state = 2;

	}

	@Override
	public void draw(Graphics g) {
		g.setFont(f);
		backgroundScene.draw(g);
		// g.setColor(new Color((int)(s1.value*255), (int)(s1.value*255),
		// (int)(s1.value*255)));
		// g.fillRect(0, 0, 1000, 1000);
		b1.draw(g, 20, 75);
		b2.draw(g, 20, 75);

		g.drawString("Rise of the Dawn of the Planet of the Triangles II:", 200, 200);
		g.drawString("Revenge of Super Rectangle Man", 200, 250);

	}

	@Override
	public void init(Point mPos, boolean[] keys, boolean[] keysToggled, Font f) {
		this.id = 0;
		this.mPos = mPos;
		this.keys = keys;
		this.keysToggled = keysToggled;
		this.f = f;
		b1 = new Button(new Rect(470, 500, 260, 100), null, 0, "Start", f, Color.white, true);
		b2 = new Button(new Rect(400, 640, 410, 100), null, 0, "Settings", f, Color.white, true);
		backgroundScene.init(mPos, keys, keysToggled, f);
		backgroundScene.gm.p.invincible = true;
		backgroundScene.demoMode = true;

	}

}