package pdex;

public class Move {

	//技名
	private final String name;
	//ID(通し番号)
	private final int id;

	//タイプ
	private final int type;

	//威力
	private final int power;
	//命中率
	private final int accuracy;
	//PP
	private final int pp;
	//物理(特殊)
	private final boolean isPhys;
	//Z技威力
	private final int zpower;
	public Move(String n,int[] info){
		name = n;
		id = info[0];
		type = info[1];
		power = info[2];
		accuracy = info[3];
		pp = info[4];
		isPhys = info[5]==1;
		zpower = info[6];
	}

	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	public int getType() {
		return type;
	}
	public int getPower() {
		return power;
	}
	public int getAccuracy() {
		return accuracy;
	}
	public int getPp() {
		return pp;
	}
	public boolean isPhys() {
		return isPhys;
	}
	public int getZpower() {
		return zpower;
	}
}
