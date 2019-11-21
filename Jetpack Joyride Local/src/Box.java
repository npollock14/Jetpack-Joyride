import java.awt.Graphics;

public class Box {
	Rect bounds;
	Vec2 vel = new Vec2(0,0);
	int id = 0;

	public Box(Point pos, int w, int h) {
		super();
		this.bounds = new Rect(pos.x, pos.y, w, h);
	}

	public void draw(Graphics g) {
		bounds.draw(g);
		g.drawRect((int)bounds.pos.x, (int)bounds.pos.y, (int)bounds.w, (int)bounds.h);
		g.drawLine((int)bounds.pos.x, (int)bounds.pos.y, (int)(bounds.pos.x + bounds.w),
				(int)(bounds.pos.y + bounds.h));
		g.drawLine((int) (bounds.pos.x + bounds.w), (int)bounds.pos.y, (int)(bounds.pos.x),
				(int)(bounds.pos.y + bounds.h));
	}
	public void update(float gameSpeed) {
		vel.x = -gameSpeed;
		bounds.pos.add(vel);
	}

}
