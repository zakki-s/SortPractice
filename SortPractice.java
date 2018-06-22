// package tr.dev.comtetition;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Set;

public class SortPractice {

	static String headerStr = "";

	public static HashMap<Integer, String> input(BufferedReader br) {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		try {
			String line;
			int i = 0;

			// String型line変数にtest01.csvファイルの内容を1行代入して、行がある限り処理を続ける
			while ((line = br.readLine()) != null) {
				// 文字コードUTF-8に変換させる為に1度byte型に変換させる
				byte[] b = line.getBytes();
				// 文字コードUTF-8に変換させる
				line = new String(b, "UTF-8");

				// もしファイルの1行目なら処理を続けて、そうでなければelseの処理をする
				if (i == 0) {
					// csvファイルのヘッダーをheaderStrに格納する
					headerStr = line;
				} else {
					// ","で区切って分けた配列を代入
					String[] data = line.split(",");
					// 会員No.をint型に変換してから代入
					Integer valueNum = Integer.parseInt(data[0]);
					// Integer型の会員No.と、String型の1行をmapに値を追加する
					map.put(valueNum, line);
				}

				// 次の行を参照させる
				i++;
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		return map;
	}

	public static HashMap<Integer, String> sortMap(Object[] mapKey, HashMap<Integer, String> map) {
	 	HashMap<Integer, String> memberMap = new HashMap<Integer, String>();

		for (int n = 0; n < mapKey.length; n++) {
			for (int m = mapKey.length-1; m > n; m--) {
				if (Integer.parseInt(mapKey[m].toString()) < Integer.parseInt(mapKey[m-1].toString())) {
					Object t = mapKey[m];
					mapKey[m] = mapKey[m-1];
					mapKey[m-1] = t;
				}
			}
			memberMap.put(Integer.parseInt(mapKey[n].toString()), map.get(mapKey[n]));
			// System.out.println(map.get(mapKey[n]));
		}
		return memberMap;
	}

	public static void writer(File fileIn, File fileOut) {
		try {
			// ファイルを読み込みに行く
			BufferedReader br = new BufferedReader(new FileReader(fileIn));
			HashMap<Integer, String> map = input(br);

			// キーだけを呼び出してObject型の配列に変換する
			// Set<Integer> itemIdSet = map.keySet();
			// Object[] mapKey =itemIdSet.toArray();
			Object[] mapKey = map.keySet().toArray();

			System.out.println(headerStr);
			
			HashMap<Integer, String> menberMap = sortMap(mapKey, map);

			br.close();
			
			FileWriter fileWrite = new FileWriter(fileOut, false);
			PrintWriter printWriter = new PrintWriter(new BufferedWriter(fileWrite));

			for (int a = 0; a < menberMap.size(); a++ ) {
				printWriter.println(map.get(mapKey[a]));
				System.out.println(map.get(mapKey[a]));
			}

			printWriter.close();
			System.out.println("---CSV Output Done---");
		} catch (IOException e) {
	        System.out.println(e);
	    }
	}
	// メソッドを分ける
	public static void main(String[] args) throws FileNotFoundException {

    	//=E6=99=82=E9=96=93=E8=A8=88=E6=B8=AC=E9=96=8B=E5=A7=8B
    	long start = System.currentTimeMillis();
    	System.out.println("---START---");

		// 相対パスに変更
		//String fileInput = "test01.csv";
		//String fileOutput = "TestOutput.csv";

		// インデントと改行のタイミングを修正
		File fileIn = new File("test01.csv");
		File fileOut = new File("TestOutput.csv");

		if(fileOut.exists()) {
			fileOut.delete();
		}

		writer(fileIn, fileOut);

	    System.out.println("---END---");
        //=E6=99=82=E9=96=93=E8=A8=88=E6=B8=AC=E7=B5=82=E4=BA=86
    	long end = System.currentTimeMillis();
    	System.out.println("TIME:" + (end - start)  + "ms");
	}
	//=E5=BE=8C=E3=80=85=E9=96=A2=E6=95=B0=E3=81=AF=E6=8A=BD=E5=87=BA
}