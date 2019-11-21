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
		menu.draw(g, 20, 75);
		
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
	public void init(Point mPos, boolean[] keys, boolean[] keysToggled, Font f) {
		s = new Slider(new Point(1000,500), 200, Color.gray, 30,30,.5,f);
		menu = new Button(new Rect(20, 20, 260, 100), null, 0, "Back", f, Color.white, true);
		
	}

}
