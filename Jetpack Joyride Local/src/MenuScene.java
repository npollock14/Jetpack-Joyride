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
		
		backgroundScene.draw(g);
		// g.setColor(new Color((int)(s1.value*255), (int)(s1.value*255),
		// (int)(s1.value*255)));
		// g.fillRect(0, 0, 1000, 1000);
		b1.draw(g, 35, 60);
		b2.draw(g, 35, 60);
		g.setFont(Driver.smallTitleFont);
		g.drawString("Rise of the Dawn of the Planet of the Triangles II:", 500, 200);
		g.setFont(Driver.fancyTitleFont);
		g.drawString("Revenge of Super Rectangle Man", 300, 280);

	}

	@Override
	public void init(Point mPos, boolean[] keys, boolean[] keysToggled) {
		this.id = 0;
		this.mPos = mPos;
		this.keys = keys;
		this.keysToggled = keysToggled;
		b1 = new Button(new Rect(750, 400, 200, 80), null, 0, "Start", Driver.titleFont, Color.white, true);
		b2 = new Button(new Rect(700, 500, 290, 80), null, 0, "Settings", Driver.titleFont, Color.white, true);
		backgroundScene.init(mPos, keys, keysToggled);
		backgroundScene.gm.p.invincible = true;
		backgroundScene.demoMode = true;

	}

}