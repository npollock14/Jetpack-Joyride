import java.awt.Graphics;

public class Bullet {
Rect bounds;
Vec2 vel;
int r;

public Bullet(int x, int y, int r, Vec2 vel) {
	bounds = new Rect(x,y,r*2, r*2);
	this.vel = vel;
	this.r = r;
}
public void draw(Graphics g) {
	g.drawOval((int)bounds.pos.x, (int)bounds.pos.y, r*2, r*2);
}
public void update(float gameSpeed) {
	bounds.pos.add(new Vec2(vel.x - gameSpeed, vel.y));
}

}
