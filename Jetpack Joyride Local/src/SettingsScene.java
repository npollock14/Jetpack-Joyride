import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class SettingsScene extends Scene{
	
	Slider s;
	Button menu;

	@Override
	public void draw(Graphics g) {
		g.setColor(new Color((int)(s.value * 255), (int)(s.value * 255), (int)(s.value * 255)));
		g.fillRect(0, 0, 1800, 1000);
		g.setColor(Color.BLACK);
		s.draw(g);
		menu.draw(g, 35, 60);
		
	}

	@Override
	public void update(Point mPos, boolean[] keys, boolean[] keysToggled, boolean[] mouse, boolean[] mouseReleased) {
		s.update(mouse, mPos, mouseReleased);
		menu.update(mouse, mPos, mouseReleased);
		if(menu.clicked) {
			Driver.state = 0;
			mouse[1] = false;
		}
		
	}

	@Override
	public void init(Point mPos, boolean[] keys, boolean[] keysToggled) {
		s = new Slider(new Point(200,500), 1400, Color.WHITE, 100,200,.5,Driver.f);
		menu = new Button(new Rect(20, 20, 200, 80), null, 0, "Back", Driver.titleFont, Color.white, true);
		
	}

}
