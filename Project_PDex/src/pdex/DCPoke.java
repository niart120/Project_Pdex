package pdex;

public class DCPoke {

	private final int stat;
	private final int lv;
	private final int type1;
	private final int type2;

	public DCPoke(Pokemon poke,int lv,int stat) {
		this.stat = stat;
		this.lv = lv;
		this.type1 = poke.getType1();
		this.type2 = poke.getType2();
	}

	public DCPoke(Pokemon poke,int stat) {
		this.stat = stat;
		this.lv = -1;
		this.type1 = poke.getType1();
		this.type2 = poke.getType2();
	}

	public int getStat() {
		return stat;
	}
	public int getLv() {
		return lv;
	}
	public int getType1() {
		return type1;
	}
	public int getType2() {
		return type2;
	}

}
