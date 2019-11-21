import java.awt.Graphics;
import java.util.ArrayList;

public class Chunk {
	ArrayList<Box> boxes = new ArrayList<Box>();
	ArrayList<Spike> spikes = new ArrayList<Spike>();
	ArrayList<Turret> turrets = new ArrayList<Turret>();
	ArrayList<ArrayList<Integer>> init;
	String initS;

	public Chunk(String input) { // id,param1,param2,param3... ; id, param1,param2...
		super();
		input = input.replaceAll("\\s", "");
		initS = input;
		ArrayList<ArrayList<Integer>> parsedInpt = parseChunk(input);
		for (ArrayList<Integer> sect : parsedInpt) {
			if (sect.get(0) == 0) { // boxes
				for (int j = 1; j < sect.size(); j += 4) {
					boxes.add(new Box(new Point((int) (sect.get(j)), (int) (sect.get(j + 1))), (int) (sect.get(j + 2)),
							(int) (sect.get(j + 3))));

				}
			}
			if (sect.get(0) == 1) { // spikes
				for (int j = 1; j < sect.size(); j += 4) {
					spikes.add(new Spike((int) (sect.get(j)), (int) (sect.get(j + 1)), (int) (sect.get(j + 2)),
							(int) (sect.get(j + 3))));
				}
			}
			if (sect.get(0) == 2) { // turret
				for (int j = 1; j < sect.size(); j += 5) {
					turrets.add(new Turret((int) (sect.get(j)), (int) (sect.get(j + 1)), (int) (sect.get(j + 2)),
							(int) (sect.get(j + 3)), sect.get(j + 4)));
				}
			}
		}

	}
	public Chunk(ArrayList<ArrayList<Integer>> parsedInpt) { // id,param1,param2,param3... ; id, param1,param2...
		super();
		init = parsedInpt;
		for (ArrayList<Integer> sect : parsedInpt) {
			if (sect.get(0) == 0) { // boxes
				for (int j = 1; j < sect.size(); j += 4) {
					boxes.add(new Box(new Point((int) (sect.get(j)), (int) (sect.get(j + 1))), (int) (sect.get(j + 2)),
							(int) (sect.get(j + 3))));
					
				}
			}
			if (sect.get(0) == 1) { // spikes
				for (int j = 1; j < sect.size(); j += 4) {
					spikes.add(new Spike((int) (sect.get(j)), (int) (sect.get(j + 1)), (int) (sect.get(j + 2)),
							(int) (sect.get(j + 3))));
				}
			}
			if (sect.get(0) == 2) { // turrets
				for (int j = 1; j < sect.size(); j += 5) {
					turrets.add(new Turret((int) (sect.get(j)), (int) (sect.get(j + 1)), (int) (sect.get(j + 2)),
							(int) (sect.get(j + 3)), sect.get(j + 4)));
				}
			}
		}
		
	}
	//

	private ArrayList<ArrayList<Integer>> parseChunk(String input) {

		String[] sections = input.split(";");
		ArrayList<String[]> sData = new ArrayList<String[]>();
		ArrayList<ArrayList<Integer>> iData = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < sections.length; i++) {
			sData.add(sections[i].split(","));
		}

		for (int i = 0; i < sData.size(); i++) {
			ArrayList<Integer> insideData = new ArrayList<Integer>();
			for (String s : sData.get(i)) {
				insideData.add(Integer.parseInt(s));
			}
			iData.add(insideData);
		}
		return iData;
	}

	public void update(GameManager gm) {
		for (Box b : boxes) {
			b.update(gm.gameSpeed);
		}
		for (Spike s : spikes) {
			s.update(gm.gameSpeed);
		}
		for (Turret t : turrets) {
			t.update(gm);
		}
	}

	public void draw(Graphics g) {
		for (Box b : boxes) {
			b.draw(g);
		}
		for (Spike s : spikes) {
			s.draw(g);
		}
		for (Turret t : turrets) {
			t.draw(g);
		}
	}

	public double getLeft() {
		double left = 0;
		if (boxes.size() > 0) {
			left = boxes.get(0).bounds.pos.x;
		}
		if (turrets.size() > 0) {
			left = turrets.get(0).body.bounds.pos.x;
		}
		if (spikes.size() > 0) {
			left = spikes.get(0).bounds.pos.x;
		}
		for (Box b : boxes) {
			if (b.bounds.pos.x < left) {
				left = b.bounds.pos.x;
			}
		}
		for (Spike s : spikes) {
			if (s.bounds.pos.x < left) {
				left = s.bounds.pos.x;
			}
		}
		for (Turret t : turrets) {
			if (t.body.bounds.pos.x < left) {
				left = t.body.bounds.pos.x;
			}
		}
		return left;
	}

	public double getRight() {
		double right = 0;
		if (boxes.size() > 0) {
			right = boxes.get(0).bounds.pos.x + boxes.get(0).bounds.w;
		}
		if (turrets.size() > 0) {
			right = turrets.get(0).body.bounds.pos.x + turrets.get(0).body.bounds.w;
		}
		if (spikes.size() > 0) {
			right = spikes.get(0).bounds.pos.x + spikes.get(0).bounds.w;
		}
		for (Box b : boxes) {
			if (b.bounds.pos.x + b.bounds.w > right) {
				right = b.bounds.pos.x + b.bounds.w;
			}
		}
		for (Spike s : spikes) {
			if (s.bounds.pos.x + s.bounds.w > right) {
				right = s.bounds.pos.x + s.bounds.w;
			}
		}
		for (Turret t : turrets) {
			if (t.body.bounds.pos.x + t.body.bounds.w > right) {
				right = t.body.bounds.pos.x + t.body.bounds.w;
			}
		}
		return right;
	}

	public Chunk getCopy() {
		if(init != null) {
		return new Chunk(init);
		}else {
			return new Chunk(initS);
		}
	}

}
