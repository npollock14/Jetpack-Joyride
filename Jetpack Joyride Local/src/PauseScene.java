import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class PauseScene extends Scene{
	
	Button menu,restart;

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setPaint(new Color(0,0,0,100));
		g2.fillRect(0, 0, 1800, 1000);
		g.setColor(Driver.darkMode ? Color.white : Color.black);
		g.setFont(Driver.titleFont);
		g.drawString("Paused", 800, 60);
		g.setFont(Driver.smallestTitleFont);
		g.drawString("[ESC] to Resume", 1000, 50);
		menu.draw(g, 10, 35);
		restart.draw(g, 20, 35);

		
	}

	@Override
	public void update(Point mPos, boolean[] keys, boolean[] keysToggled, boolean[] mouse, boolean[] mouseReleased) {
		
		
		
		this.mPos = mPos;
		this.keys = keys;
		this.keysToggled = keysToggled;
		this.mouse = mouse;
		
		if(keys[27]) {
			Driver.setState(1);
			keys[27] = false;
		}
		
		menu.update(this.mouse, this.mPos, this.mouseReleased);
		restart.update(this.mouse, this.mPos, this.mouseReleased);
		
		if(menu.clicked) { 
			Driver.state = 0;
			mouse[1] = false;
		}
		if(restart.clicked) {
			Driver.state = 4;
			mouse[1] = false;
		}
		
		
	}

	@Override
	public void init(Point mPos, boolean[] keys, boolean[] keysToggled) {
		this.id = 0;
		this.mPos = mPos;
		this.keys = keys;
		this.keysToggled = keysToggled;
		menu = new Button(new Rect(760, 90, 100, 50), null, 0, "Menu", Driver.smallTitleFont, Color.white, true);
		restart = new Button(new Rect(900, 90, 150, 50), null, 0, "Restart", Driver.smallTitleFont, Color.white, true);
		
	}

}
