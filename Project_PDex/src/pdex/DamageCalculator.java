package pdex;

public class DamageCalculator {
	public int getDamage(int atkLV,int power,int atkStat,int defStat) {
		/*ダメージ = 攻撃側のレベル × 2 ÷ 5 ＋ 2 → 切り捨て
　		× 物理技(特殊技)の威力 × 攻撃側のこうげき(とくこう) ÷ 防御側のぼうぎょ(とくぼう) → 切り捨て
　		÷ 50 ＋ 2 → 切り捨て
　		× 乱数(0.85, 0.86, …… ,0.99, 1.00 の何れか) → 切り捨て
		 */
		return ((atkLV*2/5+2)*power*atkStat/defStat/50+2)*100/100;
	}
}
