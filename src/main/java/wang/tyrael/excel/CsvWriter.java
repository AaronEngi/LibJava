package wang.tyrael.excel;

import wang.tyrael.file.FileUtil;

import java.util.List;

public class CsvWriter {
    private final String file;

    public CsvWriter(String file) {
        this.file = file;
    }

    public void write(List<String> list){
        FileUtil.write(file, list);
    }
}
