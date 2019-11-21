import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Driver extends JPanel
		implements ActionListener, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	private static final long serialVersionUID = 1L;
	int screenWidth = 1800;
	int screenHeight = 1000;
	boolean[] keys = new boolean[300];
	boolean[] keysToggled = new boolean[300];
	boolean[] keysPressed = new boolean[300];
	boolean[] keysReleased = new boolean[300]; 
	boolean[] keysHeld = new boolean[300];
	boolean[] mouse = new boolean[200];
	static boolean[] mouseReleased = new boolean[10];
	static int state = 0;
	static boolean darkMode;
	Point mPos = new Point(0, 0);

	MenuScene m = new MenuScene();
	GameScene game = new GameScene();
	PauseScene pause = new PauseScene();
	SettingsScene settings = new SettingsScene();
	static Font f = new Font("Press Start", 0, 48);
	static Font fBig = new Font("Press Start", 0, 82);
	static Font font = new Font("Courier New", 1, 25);
	static Font titleFont = new Font("Dialog", 3, 50);
	static Font smallTitleFont = new Font("Dialog", 3, 30);
	static Font smallestTitleFont = new Font("Dialog", 1, 17);
	static Font fpsFont = new Font("Impact", 1, 25);
	static Font fancyTitleFont = new Font("TimesRoman", 3, 82);

	// ============== end of settings ==================

	public void paint(Graphics g) {
		super.paintComponent(g);

		if (state == 0) {
			m.draw(g);
			return;
		}
		if (state == 1) {
			game.draw(g);
		}
		if(state == 2) {
			settings.draw(g);
		}
		if(state == 3) {
			game.draw(g);
			pause.draw(g);
		}

	}

	public void update() throws InterruptedException {
		mPos = getMousePos();
		if (state == 0) {
			m.update(mPos, keys, keysToggled, mouse, mouseReleased);
			return;
		}
		if (state == 1) {
			game.update(mPos, keys, keysToggled, mouse, mouseReleased);
		}
		if(state == 2) {
			settings.update(mPos, keys, keysToggled, mouse, mouseReleased);
		}
		if(state == 3) {
			pause.update(mPos, keys, keysToggled, mouse, mouseReleased);
		}
		if(state == 4) {
			game.init(mPos, keys, keysToggled);
			state = 1;
		}

		updateKeysHeld();
		updateKeysPressed();
		for(boolean b : keysReleased) {
			b = false;
		}

	}

	private void updateKeysPressed() {

		for(int i = 0; i < keysPressed.length; i++) {
			if(keys[i] && !keysPressed[i]) {
				keysPressed[i] = true;
			}else if(keysHeld[i]) {
				keysPressed[i] = false;
			}
		}
		
	}

	private void updateKeysHeld() {
		for (int i = 0; i < keysHeld.length; i++) {
			if (keys[i]) {
				keysHeld[i] = true;
			} else {
				keysHeld[i] = false;
			}

		}
	}

	private void init() {

		game.init(mPos, keys, keysToggled);
		m.init(mPos, keys, keysToggled);
		pause.init(mPos, keys, keysToggled);
		settings.init(mPos, keys, keysToggled);

	}

	public Point getMousePos() {
		try {
			return new Point(this.getMousePosition().x, this.getMousePosition().y);
		} catch (Exception e) {
			return mPos;
		}
	}

	// ==================code above ===========================

	@Override
	public void actionPerformed(ActionEvent arg0) {

		try {
			update();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repaint();

	}

	public static void main(String[] arg) {
		@SuppressWarnings("unused")
		Driver d = new Driver();
	}

	public Driver() {

		init();

		JFrame f = new JFrame();
		f.setTitle("Rise of the Dawn of the Planet of the Triangles II: Revenge of Super Rectangle Man");
		f.setSize(screenWidth, screenHeight);
		f.setBackground(Color.BLACK);
		f.setResizable(false);
		f.addKeyListener(this);
		f.addMouseMotionListener(this);
		f.addMouseWheelListener(this);
		f.addMouseListener(this);

		f.add(this);

		t = new Timer(14, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

	}

	Timer t;

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public static int getState() {
		return state;
	}

	public static void setState(int state) {
		Driver.state = state;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		keysReleased[e.getKeyCode()] = true;

		keys[e.getKeyCode()] = false;

		if (keysToggled[e.getKeyCode()]) {
			keysToggled[e.getKeyCode()] = false;
		} else {
			keysToggled[e.getKeyCode()] = true;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouse[e.getButton()] = true;

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouse[e.getButton()] = false;
		mouseReleased[e.getButton()] = true;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouse[e.getButton()] = true;

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

}
