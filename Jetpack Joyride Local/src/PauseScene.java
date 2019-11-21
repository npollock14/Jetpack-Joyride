import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class PauseScene extends Scene{
	
	Button menu,restart;

	@Override
	public void draw(Graphics g) {
		
		g.setFont(f);
		g.drawString("Paused", 1000, 450);
		menu.draw(g, 20, 75);
		restart.draw(g, 20, 75);

		
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
	public void init(Point mPos, boolean[] keys, boolean[] keysToggled, Font f) {
		this.id = 0;
		this.mPos = mPos;
		this.keys = keys;
		this.keysToggled = keysToggled;
		this.f = f;
		menu = new Button(new Rect(470, 500, 260, 100), null, 0, "Menu", f, Color.white, true);
		restart = new Button(new Rect(400, 640, 410, 100), null, 0, "Restart", f, Color.white, true);
		
	}

}
