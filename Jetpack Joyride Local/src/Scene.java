import java.awt.Font;
import java.awt.Graphics;

public abstract class Scene {
	Point mPos; 
	boolean[] keys = new boolean[300];
	boolean[] keysToggled = new boolean[300];
	boolean[] mouse = new boolean[200];
	boolean[] mouseReleased = new boolean[10];
	Font f;
	int id;
	boolean running;
	
	public abstract void draw(Graphics g);
	public abstract void update(Point mPos, boolean[] keys, boolean[] keysToggled, boolean[] mouse, boolean[] mouseReleased);
	public abstract void init(Point mPos, boolean[] keys, boolean[] keysToggled, Font f);
}
