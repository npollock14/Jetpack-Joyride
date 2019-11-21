import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Turret {
	Box body;
	int angle;
	int fireRate = 200;
	int bulletRadius = 5;
	int burstSize = 3;
	int burstLen = 5;
	int burstStart;
	float fireSpeed = 15.0f;
	int barrelLen = 100;
	int age = 0;
	Vec2 vel;
	int id = 2;
	int shotsFired = 0;
	int lastFired;
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();

	public Turret(int x, int y, int w, int h, int angle) {

		this.body = new Box(new Point(x, y), w, h);
		this.angle = angle;

	}

	public void draw(Graphics g) {
		body.bounds.draw(g);
		g.drawLine((int) (body.bounds.pos.x + body.bounds.w / 2), (int) (body.bounds.pos.y + body.bounds.h / 2),
				(int) (body.bounds.pos.x + body.bounds.w / 2 + barrelLen * Math.cos(Math.toRadians(angle))),
				(int) (body.bounds.pos.y + body.bounds.h / 2 - barrelLen * Math.sin(Math.toRadians(angle))));
		
		for(Bullet b : bullets) {
			b.draw(g);
		}
	}

	public void update(GameManager gm) {
		age++;
		if (age % fireRate == 0) {
			burstStart = age;
			shotsFired = 0;
		}
		if(shotsFired < burstSize && age - lastFired > burstLen) {
			bullets.add(new Bullet((int) (body.bounds.pos.x + body.bounds.w/2 - bulletRadius), (int) (body.bounds.pos.y + body.bounds.h/2), bulletRadius, new Vec2(
					fireSpeed * Math.cos(Math.toRadians(angle)), fireSpeed * -Math.sin(Math.toRadians(angle)))));
			lastFired = age;
			shotsFired++;
		}
		body.update(gm.gameSpeed);

		for (Bullet b : bullets) {
			b.update(gm.gameSpeed);
		}
		for (Chunk c : gm.currentChunks) {
			outer: for (Bullet b : bullets) {
				for (Box box : c.boxes) {
					if (b.bounds.intersects(box.bounds)) {
						bullets.remove(b);
						break outer;
					}
				}
				for (Turret t : c.turrets) {
					if (b.bounds.intersects(t.body.bounds) && t != this) {
						bullets.remove(b);
						break outer;
					}
				}
				
				
				
			}
		}
		for (Bullet b : bullets) {
			if(b.bounds.intersects(gm.p.bounds)) {
				gm.p.dead = true;
				gm.p.deathByBullet = true;
			}
		}

	}

}
