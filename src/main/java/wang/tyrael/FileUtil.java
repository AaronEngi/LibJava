package wang.tyrael;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

public class FileUtil {
	private static final String TAG = "FileUtil";

	public static String readString(String path){
        byte[] data = null;
        try {
            InputStream in = new FileInputStream(path);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(data);
    }

	public static Object readJson(String path, Class c){
		byte[] b = getBytes(path);
		//LogAdapter.d(TAG, "" + b);
		if(b == null){
			return null;
		}
		String s = null;
		try {
			s = new String(b, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//LogAdapter.d(TAG, s);
		return new Gson().fromJson(s, c);
	}
	
	public static byte[] getBytes(String path) {
		    byte[] data = null;
		    try {
		      InputStream in = new FileInputStream(path);
		      data = new byte[in.available()];
		      in.read(data);
		      in.close();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		    return data;
		  }
	
	public static List<String> readList(String file){
		Scanner s = new Scanner(file);
		List<String> list = new ArrayList<>();
		while(s.hasNext()){
			list.add(s.nextLine());
		}
		return list;
	}
	
	public static String[] readForArray(String file){
		List<String> list = readList(file);
		String[] array = new String[list.size()];
		for(int i=0; i <list.size(); i++){
			array[i] = list.get(i);
		}
		return array;
	}
	
	public static void write(String file, List<String> list){
		PrintStream ps = null;
		try {
			ps = new PrintStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String s : list){
			ps.println(s);
		}
		ps.close();
	}
	
	public static void write(String file, String[] list){
		PrintStream ps = null;
		try {
			ps = new PrintStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String s : list){
			ps.println(s);
		}
		ps.close();
	}
	
	/**
	 * 二次调用会覆盖以前的数据，从头开始写
	 * @param sFile
	 * @param content
	 */
	public static void write(String sFile, String content) {
		try {

			File file = new File(sFile);
			file.getParentFile().mkdirs();

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static void write(String sFile, byte[] bytes){
        try {
            File file = new File(sFile);
            file.getParentFile().mkdirs();

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

	public static void write(String sFile, InputStream inputStream){
		try {
			File file = new File(sFile);
			file.getParentFile().mkdirs();

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileOutputStream fileOutputStream = new FileOutputStream(file);

			byte[] bytes = new byte[1024];
			int sum = 0;
			int len = 0;
			while((len = inputStream.read(bytes)) != -1){
			    sum += len;
				fileOutputStream.write(bytes, 0, len);
			}
			System.out.println("写入文件字节数：" +sum);
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
