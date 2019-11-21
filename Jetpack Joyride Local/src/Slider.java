import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Slider {
	Button b;
	double value;
	Point pos;
	int w;
	int bw,bh;
	boolean moving;

	public Slider(Point pos, int w, Color c, int bw, int bh, double initValue, Font f) {
		super();
		this.pos = pos;
		this.w = w;
		if(initValue < 0 || initValue > 1) throw new IllegalArgumentException("Initial Value Out of Range Exception");
		b = new Button(new Rect((int)(((double)w*initValue) + pos.x - bw/2), pos.y - bh/2, bw, bh), null, 1,null, f, c, false);
		this.bw = bw;
		this.bh = bh;
		this.value = initValue;
	}

	public void update(boolean[] mouse, Point mPos, boolean[] mouseReleased) {
		b.update(mouse, mPos, mouseReleased);
if(b.clicked || moving) {
	b.bounds = new Rect(mPos.x - bw/2, pos.y - bh/2, bw, bh);
	if(b.bounds.pos.x > pos.x + w - bw/2) b.bounds.pos.x = pos.x + w - bw/2;
	if(b.bounds.pos.x < pos.x - bw/2) b.bounds.pos.x = pos.x - bw/2;
	value = (((double)b.bounds.pos.x + bw/2 - (double)pos.x)/(double)w);
	moving = mouse[1];
}

	}

	public void draw(Graphics g) {
g.drawLine((int)pos.x, (int)pos.y, (int)(pos.x + w), (int)pos.y);
b.draw(g, 0, 0);
	}

}