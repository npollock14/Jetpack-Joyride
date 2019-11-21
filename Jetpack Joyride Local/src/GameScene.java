import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class GameScene extends Scene{
	int screenWidth = 1800;
	int screenHeight = 1000;
	
	boolean demoMode;
	int frame = 0;
	double startTime = 0;
	Point mPos = new Point(0,0);
	GameManager gm;
	double highScore = 0;
	boolean highBeat;
	double frameStart = 0;
	ArrayList<Double> fps = new ArrayList<Double>();

	Font font = new Font("Courier New", 1, 25);
	Font titleFont = new Font("Dialog", 3, 50);
	Font smallTitleFont = new Font("Dialog", 3, 30);
	Font smallerTitleFont = new Font("Dialog", 1, 25);
	Font smallestTitleFont = new Font("Dialog", 1, 17);
	Font fpsFont = new Font("Impact", 1, 25);
	
	
	@Override
	public void draw(Graphics g) {
		gm.draw(g);

		
		if(!demoMode) {
			g.drawLine(50, 0, 50, screenHeight);
			g.drawLine(49, 0, 49, screenHeight);
			g.drawLine(48, 0, 48, screenHeight);
			gm.p.draw(g);
		}
		

		g.setFont(fpsFont);
		fps.add(1 / ((System.currentTimeMillis() - frameStart) / 1000));
		if(fps.size() >= 60) {
			fps.remove(0);
		}
		double sum = 0;
		for(Double d : fps) {
			sum += d;
		}
		double avg = sum/fps.size();
		g.drawString((int)avg + "", screenWidth - 50, 25);
		frameStart = System.currentTimeMillis();

		if (gm.p.dead) {
			g.setFont(titleFont);
			g.drawString("DEAD", 800, 500);
			if (gm.p.distanceTraveled > highScore) {
				highScore = gm.p.distanceTraveled;
				Misc.writeToFile("Res/score.txt", (int) highScore + "");
				highBeat = true;
			}
			if (highBeat) {
				g.drawString("NEW HIGHSCORE: " + (int) gm.p.distanceTraveled + "m", 600, 550);
			} else {
				g.drawString((int) gm.p.distanceTraveled + "m", 820, 550);
				g.setFont(smallTitleFont);
				g.drawString("High: " + (int) highScore + "m", 810, 590);
			}

		}
		if (!gm.p.dead && !demoMode) {
			g.setFont(titleFont);
			g.drawString((int) gm.p.distanceTraveled + "m", 60, 900);
			g.setFont(smallTitleFont);
			g.drawString("High: " + (int) highScore + "m", 60, 940);
			g.setFont(smallestTitleFont);
			g.drawString("[R] to Restart [ESC] to Pause", 60, 960);
		}
		
	}

	@Override
	public void update(Point mPos, boolean[] keys, boolean[] keysToggled, boolean[] mouse, boolean[] mouseReleased) {

		this.mPos = mPos;
		this.keys = keys;
		this.keysToggled = keysToggled;
		this.mouse = mouse;
		
		if(keys[27]) {
			Driver.setState(3);
			keys[27] = false;
		}
		
		if(keys[82]) Driver.setState(4);
		gm.p.update(gm, keys);
		if ((int) gm.p.distanceTraveled % 10 == 0 && !demoMode) {
			gm.gameSpeed += .01f;
		}
		gm.updateChunks();
		
	}

	@Override
	public void init(Point mPos, boolean[] keys, boolean[] keysToggled, Font f) {
		gm = new GameManager(3.0f, new Player(new Point(500, 100), 50, 100, new Vec2(0, 0)), 1.0f, screenWidth,
				screenHeight);

		// load chunks from file
		String[] chunkData = Misc.read("Res/Chunks.txt");
		highScore = Integer.parseInt(Misc.read("Res/score.txt")[0]);
		for (String s : chunkData) {
			gm.chunks.add(new Chunk(s));
		}

		gm.updateChunks();
		
	}

	

}
