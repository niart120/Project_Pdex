package pdex;

public class Pokemon {
	//名前
	private final String name;
	//図鑑No.
	private final int dexNo;
	//id(通し番号)
	private final int id;

	//H種族値
	private final int hBS;
	//A種族値
	private final int aBS;
	//B種族値
	private final int bBS;
	//C種族値
	private final int cBS;
	//D種族値
	private final int dBS;
	//S種族値
	private final int sBS;
	//合計種族値
	private final int sumBS;

	//タイプ1
	private final int type1;
	//タイプ2
	private final int type2;

	public Pokemon(String n,int[] stat){
		name = n;
		dexNo = stat[0];
		id = stat[1];

		hBS = stat[2];
		aBS = stat[3];
		bBS = stat[4];
		cBS = stat[5];
		dBS = stat[6];
		sBS = stat[7];
		sumBS = stat[8];

		type1 = stat[9];
		type2 = stat[10];

	}

	public String getName() {
		return name;
	}

	public int getDexNo() {
		return dexNo;
	}

	public int getId() {
		return id;
	}

	public int gethBS() {
		return hBS;
	}

	public int getaBS() {
		return aBS;
	}

	public int getbBS() {
		return bBS;
	}

	public int getcBS() {
		return cBS;
	}

	public int getdBS() {
		return dBS;
	}

	public int getsBS() {
		return sBS;
	}

	public int getSumBS() {
		return sumBS;
	}

	public int getType1() {
		return type1;
	}

	public int getType2() {
		return type2;
	}

	public int getAStat(int lv, int ev,int iv,int nc) {
		return ((aBS*2+ev+iv/4)*lv/100+5)*nc;
	}

	public int getBStat(int lv, int ev,int iv,int nc) {
		return ((bBS*2+ev+iv/4)*lv/100+5)*nc;
	}

	public int getCStat(int lv, int ev,int iv,int nc) {
		return ((cBS*2+ev+iv/4)*lv/100+5)*nc;
	}

	public int getDStat(int lv, int ev,int iv,int nc) {
		return ((dBS*2+ev+iv/4)*lv/100+5)*nc;
	}

	public int getSStat(int lv, int ev,int iv,int nc) {
		return ((sBS*2+ev+iv/4)*lv/100+5)*nc;
	}


}
