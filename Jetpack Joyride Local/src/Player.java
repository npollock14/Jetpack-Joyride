import java.awt.Graphics;
import java.util.ArrayList;

public class Player {
	Vec2 vel;
	Rect bounds, cBounds, rBounds;
	float pwr = 1f;
	ArrayList<Particle> jetpackFlame = new ArrayList<Particle>();
	boolean dead, grounded, ceiling, boosting, crouching, prevCrouch, deathByBullet;
	double distanceTraveled = 0;
	boolean invincible;

	public Player(Point pos, int w, int h, Vec2 vel) {
		super();
		this.vel = vel;
		this.bounds = new Rect(pos.x, pos.y, w, h);
		this.rBounds = bounds;
		this.cBounds = new Rect(pos.x, pos.y, h, w);
	}

	public void update(GameManager gm, boolean[] keys) {

		grounded = false;
		ceiling = false;
		boosting = false;
		int colType = 0;
		if (!dead) { // collision checks
			

			// check ceiling and floor

			if (bounds.intersects(gm.ceil.bounds)) {
				vel.y = 0;
				bounds.pos.y = gm.ceil.bounds.pos.y + gm.ceil.bounds.h;
				ceiling = true;
			}
			if (bounds.intersects(gm.floor.bounds)) {
				vel.y = 0;
				bounds.pos.y = gm.floor.bounds.pos.y - bounds.h;
				grounded = true;
			}
			outer: for (Chunk c : gm.currentChunks) {
				for (Box b : c.boxes) {
					if (bounds.intersects(b.bounds)) {
						colType = bounds.classifyCol(b.bounds);
						
						if (colType == 2) {
							bounds.pos.x = b.bounds.pos.x - bounds.w - 1;
						}
						if (colType == 1) {
							vel.y = 0;
							bounds.pos.y = b.bounds.pos.y + b.bounds.h;
							ceiling = true;
						}
						if (colType == 3) {
							vel.y = 0;
							bounds.pos.y = b.bounds.pos.y - bounds.h;
							grounded = true;
						}

					}
				}
				for (Spike s : c.spikes) {
					if (bounds.intersects(s.bounds) && !invincible) {
						dead = true;
						vel.y = 0;
						vel.x = -gm.gameSpeed;
						break outer;
					}
				}
				for(Turret t : c.turrets) {
					if(bounds.intersects(t.body.bounds)) {
						colType = bounds.classifyCol(t.body.bounds);
						if (colType == 2) {
							bounds.pos.x = t.body.bounds.pos.x - bounds.w - 1;
						}
						if (colType == 1) {
							vel.y = 0;
							bounds.pos.y = t.body.bounds.pos.y + t.body.bounds.h;
							ceiling = true;
						}
						if (colType == 3) {
							vel.y = 0;
							bounds.pos.y = t.body.bounds.pos.y - bounds.h;
							grounded = true;
						}
					}
				}
			}

			if (keys[40] || keys[83]) {
				if(!prevCrouch) {
				setCrouching(true);
				}
				crouching = true;
			}
			if (!crouching) {
				if(prevCrouch) {
				setCrouching(false);
				}
				crouching = false;
			}
			prevCrouch = crouching;
			crouching = false;

			if (keys[32] && !prevCrouch) {
				if (!ceiling) {
					vel.y -= pwr;
				}
				boosting = true;
				for (int i = 0; i < 1; i++) {
					jetpackFlame.add(new Particle(new Point(bounds.pos.x + bounds.w / 2, bounds.pos.y + bounds.h / 2),
							new Vec2(Math.random() * 5 - 2, Math.random() * 20 + vel.y),
							(float) (Math.random() * 20 + 3)));

				}

			}

			if (!grounded && !boosting && !(ceiling && boosting) && !dead) {
				vel.y += gm.gravity * (crouching ? 2 : 1);
			}

		}

		for (Particle p : jetpackFlame) {
			p.update(gm.gravity);
		}
		for (Particle p : jetpackFlame) {
			if (p.age > 100) {
				jetpackFlame.remove(p);
				break;
			}
		}

		if (bounds.pos.x <=  50 && !invincible) {
			dead = true;
		}
		
		if(!dead) vel = applyResistance(vel, .98f);
		bounds.pos.add(vel);
		
		if(!dead && colType != 2) {
		distanceTraveled += gm.gameSpeed/50;
		}
		if(deathByBullet) {
			vel.y = 0;
			vel.x = -gm.gameSpeed;
			for (int i = 0; i < 10; i++) {
				jetpackFlame.add(new Particle(new Point(bounds.pos.x + bounds.w / 2, bounds.pos.y + bounds.h / 2),
						new Vec2(Math.random() * 20 - 10, Math.random() * 20 - 10),
						(float) (Math.random() * 20 + 3)));

			}
		}
		
	}

	private void setCrouching(boolean b) {
		cBounds.pos = bounds.pos;
		rBounds.pos = bounds.pos;
		double deltaX = cBounds.w - rBounds.w;
		if (b) {
			cBounds.pos.x -= deltaX;
			bounds = cBounds.getCopy();
		} else {
			rBounds.pos.y -= deltaX;
			rBounds.pos.x += deltaX;
			bounds = rBounds.getCopy();
		}

	}

	private Vec2 applyResistance(Vec2 vel, float amt) {

		return new Vec2(vel.x == 0 ? 0 : Math.pow(vel.x, amt),
				vel.y == 0 ? 0 : vel.y > 0 ? Math.pow(vel.y, amt) : -Math.pow(-vel.y, amt));

	}

	public void draw(Graphics g) {
		bounds.draw(g);
		g.fillRect((int) bounds.pos.x, (int) bounds.pos.y, (int) bounds.w, (int) bounds.h);
		for (Particle p : jetpackFlame) {
			p.draw(g);
		}
	}

}
