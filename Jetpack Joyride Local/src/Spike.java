import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Spike {
	Rect bounds;
	Vec2 vel = new Vec2(0,0);
	int numSpikes;
	int spikeWidth = 20;
	int thickness = 1;
	int id = 1;
	public Spike(int x, int y, int w, int h) {
		super();
		this.bounds = new Rect(x, y, w, h);
		numSpikes = (int) (bounds.w / spikeWidth);
	}
	public void draw(Graphics g) {
		//bounds.draw(g);
		for(int i = 0; i< numSpikes; i++) {
			g.drawLine((int)(i*spikeWidth + bounds.pos.x), (int)(bounds.pos.y + bounds.h), (int)(i * spikeWidth + bounds.pos.x + spikeWidth/2), (int)(bounds.pos.y));
			g.drawLine((int)(i*spikeWidth + spikeWidth/2 + bounds.pos.x), (int)(bounds.pos.y), (int)(i * spikeWidth + spikeWidth + bounds.pos.x), (int)(bounds.pos.y + bounds.h));
		}
		g.drawLine((int)(bounds.pos.x), (int)(bounds.pos.y + bounds.h), (int)(bounds.pos.x + bounds.w), (int)(bounds.pos.y + bounds.h));
	}
	public void update(float gameSpeed) {
		vel.x = -gameSpeed;
		bounds.pos.add(vel);
	}

}
