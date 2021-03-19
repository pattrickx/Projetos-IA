package algoritmo;

public class Ladrao extends ProgramaLadrao {

	@Override
	public int acao() {

		int[] a = sensor.getAmbienteOlfatoPoupador();
		int[] b = sensor.getVisaoIdentificacao();

		if (    (a[0] == 2 ||
				a[3] == 2 ||
				a[5] == 2) ||
				((b[0] >=100 && b[0] < 200) ||
						(b[5] >=100 && b[5] < 200) ||
						(b[10] >=100 && b[10] < 200) ||
						(b[14] >=100 && b[14] < 200) ||
						(b[11] >=100 && b[11] < 200) ||
						(b[19] >=100 && b[19] < 200))
		)  {

			return 4;
		} else

		if ((a[2] == 2 || a[4] == 2 || a[7] == 2)
				||
				((b[4] >=100 && b[4] < 200) ||
						(b[9] >=100 && b[9] < 200) ||
						(b[12] >=100 && b[12] < 200) ||
						(b[13] >=100 && b[13] < 200) ||
						(b[18] >=100 && b[18] < 200) ||
						(b[23] >=100 && b[23] < 200))) {
			return 3;
		} else

		if ((a[1] == 2)
				||
				((b[1] >=100 && b[1] < 200) ||
						(b[2] >=100 && b[2] < 200) ||
						(b[3] >=100 && b[3] < 200) ||
						(b[6] >=100 && b[6] < 200) ||
						(b[7] >=100 && b[7] < 200) ||
						(b[8] >=100 && b[8] < 200))) {
			return 1;
		} else

		if ((a[6] == 2) ||

				((b[20] >=100 && b[20] < 200) ||
						(b[21] >=100 && b[21] < 200) ||
						(b[22] >=100 && b[22] < 200) ||
						(b[15] >=100 && b[15] < 200) ||
						(b[16] >=100 && b[16] < 200) ||
						(b[17] >=100 && b[17] < 200))) {
			return 2;
		} else if (a[0] == 0 && a[1] == 0 && a[2] == 0 && a[3] == 0 && a[4] == 0 && a[5] == 0 && a[6] == 0
				&& a[7] == 0) {
			return (int) (Math.random() * 5);
		}
		return (int) (Math.random() * 5);

	}

}
//package algoritmo;
//
//import java.awt.Point;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Random;
//
//public class Ladrao extends ProgramaLadrao {
//	private int[][] map = new int[30][30];
//	private boolean findPoupador = false;
//
//	public Ladrao() {
//	}
//
//	public int acao() {
//		Point actPos = this.sensor.getPosicao();
//		Random r = new Random();
//		int[] visao = this.sensor.getVisaoIdentificacao();
//		int[] olfatoP = this.sensor.getAmbienteOlfatoPoupador();
//		int[] olfatoL = this.sensor.getAmbienteOlfatoLadrao();
//		int counterX = -2;
//		int counterY = -2;
//		System.out.print("Começando for\n");
//		int[] var11 = visao;
//		int var10 = visao.length;
//
//		int i;
//		int var9;
//		int[] var10000;
//		int var10001;
//		for(var9 = 0; var9 < var10; ++var9) {
//			i = var11[var9];
//			System.out.print(String.valueOf(i));
//			switch(i) {
//				case -1:
//					System.out.print("fora do mundo");
//					break;
//				case 1:
//				case 2:
//				case 3:
//				case 4:
//				case 5:
//					this.map[(int)actPos.getX() + counterX][(int)actPos.getY() + counterY] = 2147483647;
//					System.out.print(" Obstaculo |");
//					break;
//				case 100:
//					var10000 = this.map[(int)actPos.getX() + counterX];
//					var10001 = (int)actPos.getY() + counterY;
//					var10000[var10001] += -5;
//					i = 1;
//
//					for(; i < 3; ++i) {
//						if ((int)actPos.getX() + counterX + i < 29) {
//							var10000 = this.map[(int)actPos.getX() + counterX + i];
//							var10001 = (int)actPos.getY() + counterY;
//							var10000[var10001] += -5 + i;
//						}
//
//						if ((int)actPos.getX() + counterX - i > 0) {
//							var10000 = this.map[(int)actPos.getX() + counterX - i];
//							var10001 = (int)actPos.getY() + counterY;
//							var10000[var10001] += -5 + i;
//						}
//
//						if ((int)actPos.getY() + counterY + i < 29) {
//							var10000 = this.map[(int)actPos.getX() + counterX];
//							var10001 = (int)actPos.getY() + counterY + i;
//							var10000[var10001] += -5 + i;
//						}
//
//						if ((int)actPos.getY() + counterY - i > 0) {
//							var10000 = this.map[(int)actPos.getX() + counterX];
//							var10001 = (int)actPos.getY() + counterY - i;
//							var10000[var10001] += -5 + i;
//						}
//
//						if ((int)actPos.getX() + counterX + i < 29 && (int)actPos.getY() + counterY + i < 29) {
//							var10000 = this.map[(int)actPos.getX() + counterX + i];
//							var10001 = (int)actPos.getY() + counterY + i;
//							var10000[var10001] += -5 + i;
//						}
//
//						if ((int)actPos.getX() + counterX - i > 0 && (int)actPos.getY() + counterY + i < 29) {
//							var10000 = this.map[(int)actPos.getX() + counterX - i];
//							var10001 = (int)actPos.getY() + counterY + i;
//							var10000[var10001] += -5 + i;
//						}
//
//						if ((int)actPos.getY() + counterY + i < 29 && (int)actPos.getX() + counterX - i > 0) {
//							var10000 = this.map[(int)actPos.getX() + counterX - i];
//							var10001 = (int)actPos.getY() + counterY + i;
//							var10000[var10001] += -5 + i;
//						}
//
//						if ((int)actPos.getY() + counterY - i > 0 && (int)actPos.getX() + counterX - i > 0) {
//							var10000 = this.map[(int)actPos.getX() + counterX - i];
//							var10001 = (int)actPos.getY() + counterY - i;
//							var10000[var10001] += -5 + i;
//						}
//					}
//
//					System.out.print(" Poupador |");
//					break;
//				case 200:
//					var10000 = this.map[(int)actPos.getX() + counterX];
//					var10001 = (int)actPos.getY() + counterY;
//					var10000[var10001] += 5;
//
//					for(i = 1; i < 3; ++i) {
//						if ((int)actPos.getX() + counterX + i < 29) {
//							var10000 = this.map[(int)actPos.getX() + counterX + i];
//							var10001 = (int)actPos.getY() + counterY;
//							var10000[var10001] += i;
//						}
//
//						if ((int)actPos.getX() + counterX - i > 0) {
//							var10000 = this.map[(int)actPos.getX() + counterX - i];
//							var10001 = (int)actPos.getY() + counterY;
//							var10000[var10001] += i;
//						}
//
//						if ((int)actPos.getY() + counterY + i < 29) {
//							var10000 = this.map[(int)actPos.getX() + counterX];
//							var10001 = (int)actPos.getY() + counterY + i;
//							var10000[var10001] += i;
//						}
//
//						if ((int)actPos.getY() + counterY - i > 0) {
//							var10000 = this.map[(int)actPos.getX() + counterX];
//							var10001 = (int)actPos.getY() + counterY - i;
//							var10000[var10001] += i;
//						}
//
//						if ((int)actPos.getX() + counterX + i < 29 && (int)actPos.getY() + counterY + i < 29) {
//							var10000 = this.map[(int)actPos.getX() + counterX + i];
//							var10001 = (int)actPos.getY() + counterY + i;
//							var10000[var10001] += i;
//						}
//
//						if ((int)actPos.getX() + counterX - i > 0 && (int)actPos.getY() + counterY + i < 29) {
//							var10000 = this.map[(int)actPos.getX() + counterX - i];
//							var10001 = (int)actPos.getY() + counterY + i;
//							var10000[var10001] += i;
//						}
//
//						if ((int)actPos.getY() + counterY + i < 29 && (int)actPos.getX() + counterX - i > 0) {
//							var10000 = this.map[(int)actPos.getX() + counterX - i];
//							var10001 = (int)actPos.getY() + counterY + i;
//							var10000[var10001] += i;
//						}
//
//						if ((int)actPos.getY() + counterY - i > 0 && (int)actPos.getX() + counterX - i > 0) {
//							var10000 = this.map[(int)actPos.getX() + counterX - i];
//							var10001 = (int)actPos.getY() + counterY - i;
//							var10000[var10001] += i;
//						}
//					}
//
//					this.findPoupador = true;
//					System.out.print(" Ladrão |");
//					break;
//				default:
//					if (this.map[(int)actPos.getX() + counterX][(int)actPos.getY() + counterY] < 0) {
//						this.map[(int)actPos.getX() + counterX][(int)actPos.getY() + counterY] = 0;
//					}
//
//					System.out.print(" Vazio/Fora do Alcance |");
//			}
//
//			if (counterX == 2) {
//				++counterY;
//				counterX = -2;
//				System.out.print("\n");
//			} else if (counterY == 0 && counterX == -1) {
//				System.out.print(" X |");
//				counterX += 2;
//			} else {
//				++counterX;
//			}
//		}
//
//		System.out.print("\nTerminando for\n");
//		int var10002;
//		if (this.findPoupador) {
//			var10000 = this.map[(int)actPos.getX()];
//			var10001 = (int)actPos.getY();
//			var10000[var10001] -= 5;
//			this.findPoupador = false;
//		} else {
//			var10002 = this.map[(int)actPos.getX()][(int)actPos.getY()]++;
//		}
//
//		counterX = -1;
//		counterY = -1;
//		var11 = olfatoP;
//		var10 = olfatoP.length;
//
//		for(var9 = 0; var9 < var10; ++var9) {
//			i = var11[var9];
//			if (i > 0) {
//				this.map[(int)actPos.getX() + counterX][(int)actPos.getY() + counterY] = i - 6;
//			}
//
//			if (counterX == 1) {
//				++counterY;
//				counterX = -1;
//				System.out.print("\n");
//			} else if (counterY == 0 && counterX == -1) {
//				System.out.print(" X |");
//				counterX += 2;
//			} else {
//				++counterX;
//			}
//		}
//
//		counterX = -1;
//		counterY = -1;
//		var11 = olfatoL;
//		var10 = olfatoL.length;
//
//		for(var9 = 0; var9 < var10; ++var9) {
//			i = var11[var9];
//			if (i > 0) {
//				var10002 = this.map[(int)actPos.getX() + counterX][(int)actPos.getY() + counterY]++;
//			}
//
//			if (counterX == 1) {
//				++counterY;
//				counterX = -1;
//				System.out.print("\n");
//			} else if (counterY == 0 && counterX == -1) {
//				System.out.print(" X |");
//				counterX += 2;
//			} else {
//				++counterX;
//			}
//		}
//
//		List<Integer> mapping = new ArrayList();
//		mapping.add((int)actPos.getY() - 1 > 0 ? this.map[(int)actPos.getX()][(int)actPos.getY() - 1] : 2147483647);
//		mapping.add((int)actPos.getY() + 1 < 29 ? this.map[(int)actPos.getX()][(int)actPos.getY() + 1] : 2147483647);
//		mapping.add((int)actPos.getX() + 1 < 29 ? this.map[(int)actPos.getX() + 1][(int)actPos.getY()] : 2147483647);
//		mapping.add((int)actPos.getX() - 1 > 0 ? this.map[(int)actPos.getX() - 1][(int)actPos.getY()] : 2147483647);
////		System.out.printf("Mapping to String: 0=%d, 1=%d, 2=%d, 3=%d", this.map[(int)actPos.getX()][(int)actPos.getY() - 1], this.map[(int)actPos.getX()][(int)actPos.getY() + 1], this.map[(int)actPos.getX()][(int)actPos.getY() + 1], this.map[(int)actPos.getX() - 1][(int)actPos.getY()]);
//		boolean allEqual = true;
//		boolean manyEqual = false;
//		int count = 0;
//		int aux = 2147483647;
//		Iterator var14 = mapping.iterator();
//
//		int item;
//		while(var14.hasNext()) {
//			item = (Integer)var14.next();
//			int rand;
//			if (aux == item) {
//				rand = r.nextInt(99);
//				if (rand >= 95) {
//					manyEqual = true;
//					++count;
//				} else {
//					manyEqual = false;
//					++count;
//				}
//			} else if (item < aux) {
//				allEqual = false;
//				if (aux != 2147483647) {
//					rand = r.nextInt(99);
//					if (rand < 95) {
//						aux = item;
//					}
//				} else {
//					aux = item;
//				}
//
//				++count;
//			}
//		}
//
//		System.out.print("aux = " + aux + "\n");
//		System.out.print("index" + (mapping.indexOf(aux) + 1) + "\n");
//		item = r.nextInt(999);
//		boolean randomFactor = false;
//		if (item >= 985) {
//			randomFactor = true;
//		}
//
//		return !allEqual && !randomFactor ? (manyEqual ? count + 1 : mapping.indexOf(aux) + 1) : (int)Math.random() * 5;
//	}
//}