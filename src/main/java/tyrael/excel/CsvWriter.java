package tyrael.excel;

import java.util.List;

import tyrael.file.FileUtil;

public class CsvWriter {
    private final String file;

    public CsvWriter(String file) {
        this.file = file;
    }

    public void write(List<String> list){
        FileUtil.write(file, list);
    }
}
