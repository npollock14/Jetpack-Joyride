import java.awt.Graphics;

public class Particle {
	Point pos;
	Vec2 vel;
	float r;
	int age = 0;

	public Particle(Point pos, Vec2 vel, float r) {
		super();
		this.pos = pos;
		this.vel = vel;
		this.r = r;
	}

	public void update(float gravity) {
		vel.y += gravity;
		pos.add(vel);
		if(r >= .5) {
			r-=.5;
		}
		age++;
	}

	public void draw(Graphics g) {
		g.drawOval((int) pos.x, (int) pos.y, (int) r, (int) r);
	}

}
