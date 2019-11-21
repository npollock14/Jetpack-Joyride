import java.awt.Graphics;
import java.util.ArrayList;

public class GameManager {
	float gameSpeed;
	ArrayList<Chunk> chunks = new ArrayList<Chunk>();
	ArrayList<Chunk> currentChunks = new ArrayList<Chunk>();
	Player p;
	float gravity;
	int screenHeight, screenWidth;
	Box ceil, floor;

	public GameManager(float gameSpeed, Player p, float gravity, int screenWidth, int screenHeight) {
		super();
		this.gameSpeed = gameSpeed;
		this.p = p;
		this.gravity = gravity;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		ceil = new Box(new Point(0,-100), screenWidth, 100);
		floor = new Box(new Point(0,screenHeight-30), screenWidth, 100);
	}
	public void updateChunks() {
		//double startTime = System.nanoTime();
		if(shouldSpawn()) {
			Chunk newChunk = getNewChunk(chunks);
			double left = newChunk.getLeft();
			for(Box b : newChunk.boxes) {
				b.bounds.pos.x += screenWidth - left + 300;
			}
			for(Spike b : newChunk.spikes) {
				b.bounds.pos.x += screenWidth - left + 300;
			}
			for(Turret t : newChunk.turrets) {
				t.body.bounds.pos.x += screenWidth - left + 300;
			}
			currentChunks.add(newChunk);
		}
		
		for(Chunk c : currentChunks) {
			c.update(this);
		}
		
		for(Chunk c : currentChunks) {
			if(c.getRight() < 0) {
				currentChunks.remove(c);
				break;
			}
		}
		//System.out.println((System.nanoTime() - startTime) / Math.pow(10, 6));
	}
	public void draw(Graphics g) {
		for (Chunk c : currentChunks) {
			c.draw(g);
		}
	}
	private Chunk getNewChunk(ArrayList<Chunk> chunks) {
		
		return chunks.get((int) (Math.random()*chunks.size())).getCopy();
		
	}
	public boolean shouldSpawn() {
		if(currentChunks.size() == 0) {
			return true;
		}
		if(getRight(currentChunks) <= screenWidth) {
			return true;
		}
		return false;
	}
	private double getRight(ArrayList<Chunk> currentChunks) {
		double right = currentChunks.get(0).getRight();
		for(Chunk c : currentChunks) {
			if(c.getRight() > right) {
				right = c.getRight();
			}
		}
		return right;
	}

}
