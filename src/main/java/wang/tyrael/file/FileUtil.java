package wang.tyrael.file;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.annotation.Nonnull;

public class FileUtil {
    private static final String TAG = "FileUtil";

    public static boolean copy(@Nonnull File source, @Nonnull File dest) throws IOException {
        //noinspection ResultOfMethodCallIgnored
        dest.getParentFile().mkdirs();
        if (!dest.exists()) {
            //noinspection ResultOfMethodCallIgnored
            dest.createNewFile();
        }
        try (FileChannel inputChannel = new FileInputStream(source).getChannel();
             FileChannel outputChannel = new FileOutputStream(dest).getChannel()) {
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        }
        return true;
    }

    public static boolean copy(@Nonnull String src, @Nonnull String dest) throws IOException {
        return copy(new File(src), new File(dest));
    }

    public static String readString(String path) {
        byte[] data = null;
        try {
            InputStream in = new FileInputStream(path);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (data == null) {
            return null;
        }
        return new String(data);
    }

    public static Object readJson(String path, Class c) {
        byte[] b = getBytes(path);
        //LogAdapter.d(TAG, "" + b);
        if (b == null) {
            return null;
        }
        String s = null;
        s = new String(b, StandardCharsets.UTF_8);
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

    public static List<String> readList(String file) {
        Scanner s = new Scanner(file);
        List<String> list = new ArrayList<>();
        while (s.hasNext()) {
            list.add(s.nextLine());
        }
        return list;
    }

    public static String[] readForArray(String file) {
        List<String> list = readList(file);
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static void write(String file, List<String> list) {
        PrintStream ps = null;
        try {
            ps = new PrintStream(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (String s : list) {
            ps.println(s);
        }
        ps.close();
    }

    public static void write(String file, String[] list) {
        PrintStream ps = null;
        try {
            ps = new PrintStream(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (String s : list) {
            ps.println(s);
        }
        ps.close();
    }

    /**
     * 二次调用会覆盖以前的数据，从头开始写
     *
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

    public static void write(String sFile, byte[] bytes) {
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

    public static void write(String sFile, InputStream inputStream) {
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
            while ((len = inputStream.read(bytes)) != -1) {
                sum += len;
                fileOutputStream.write(bytes, 0, len);
            }
            System.out.println("写入文件字节数：" + sum);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
