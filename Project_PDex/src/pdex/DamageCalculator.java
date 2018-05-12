package pdex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DamageCalculator {
	private final int[][]  effectTable = new int[19][19];

	public DamageCalculator() {
		try {
			File f = new File("./lib/effect.csv");
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line;
			int j=0;
			while ((line = br.readLine()) != null) {
				String[] tempData = line.split(",", 0);
				for(int i=0;i<tempData.length;i++){
					effectTable[i][j] = Integer.valueOf(tempData[i]);
				}
				j++;
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	private int getEffect(Move mv, DCPoke poke) {
		return effectTable[mv.getType()][poke.getType1()]*effectTable[mv.getType()][poke.getType2()];
	}

	private int typeMatch(Move mv,DCPoke poke) {
		return 2;
	}

	public int getDamage(Move mv,DCPoke atkPoke, DCPoke defPoke) {
		/*ダメージ = 攻撃側のレベル × 2 ÷ 5 ＋ 2 → 切り捨て
　		× 物理技(特殊技)の威力 × 攻撃側のこうげき(とくこう) ÷ 防御側のぼうぎょ(とくぼう) → 切り捨て
　		÷ 50 ＋ 2 → 切り捨て
　		× 乱数(0.85, 0.86, …… ,0.99, 1.00 の何れか) → 切り捨て
		 */
		return ((atkPoke.getLv()*2/5+2)*mv.getPower()*atkPoke.getStat()/defPoke.getStat()/50+2)*100/100*typeMatch(mv,atkPoke)/2*getEffect(mv,defPoke)/4;
	}
}
