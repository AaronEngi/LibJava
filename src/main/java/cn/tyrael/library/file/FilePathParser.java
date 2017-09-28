package cn.tyrael.library.file;

public class FilePathParser {
	public final String filePath;

	public FilePathParser(String filePath) {
		super();
		this.filePath = filePath;
	}

	/**
	 * 第一个斜杠前的内容
	 * @return
	 */
	public String getFirstDir(){
		int index = filePath.indexOf("/");
		//noinspection UnnecessaryLocalVariable
		String s = filePath.substring(0, index);
		return s;

	}

	public String excludeFirstDir(){
		int index = filePath.indexOf("/");
		//noinspection UnnecessaryLocalVariable
		String s = filePath.substring(index+1);
		return s;
	}

	public String getFileName(){
		int index = filePath.lastIndexOf("/");
		//noinspection UnnecessaryLocalVariable
		String s = filePath.substring(index + 1);
		return s;
	}

	/**
	 * 扩展名
	 * @return
	 */
	public String getExt(){
		int index = filePath.lastIndexOf(".");
		//noinspection UnnecessaryLocalVariable
		String s = filePath.substring(index + 1);
		return s;
	}

}
