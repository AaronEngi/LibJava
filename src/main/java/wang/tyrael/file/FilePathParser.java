package wang.tyrael.file;

public class FilePathParser {
	public final String filePath;

	public FilePathParser(String filePath) {
		super();
		this.filePath = filePath;
	}


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

	public String getExt(){
		int index = filePath.lastIndexOf(".");
		//noinspection UnnecessaryLocalVariable
		String s = filePath.substring(index + 1);
		return s;
	}
}
