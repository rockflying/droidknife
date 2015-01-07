package droid.knife.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ApkTool {

	public static String apk;
	public static String filename;

	public static final String cmd = "java -jar libs/apktool.jar d -f -o temp/";

	public static boolean apkParse(String file) {
		apk = file;

		if (!apk.endsWith(".apk")) {
			System.out.println("Invalid apk file");
			return false;
		}

		File f = new File(apk);
		if (!f.exists()) {
			System.out.println(apk + " does not exist");
			return false;
		}

		filename = f.getName().replace(".apk", "");

		try {
			Process proc = Runtime.getRuntime()
					.exec(cmd + filename + " " + apk);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					proc.getErrorStream()));
			
			String buf = null;

			while ((buf = reader.readLine()) != null) {
				System.out.println(buf);
			}

			reader = new BufferedReader(new InputStreamReader(
					proc.getInputStream()));
			while ((buf = reader.readLine()) != null) {
				System.out.println(buf);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void clean() {
		// TODO delete temporary results
		File file = new File("temp/"+filename);
		if(file.exists()) {
			file.delete();
		}
	}

	public static void main(String[] args) {
		ApkTool.apkParse("F:/temp/suishouji.apk");
	}
}
