package cn.tyrael.os.baidu;

import java.io.File;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.PutObjectResponse;

public class BosApi {
	public static BosClient getClient(BosConfig bosConfig){
	    String ACCESS_KEY_ID = bosConfig.ACCESS_KEY_ID;                   // 用户的Access Key ID
	    String SECRET_ACCESS_KEY = bosConfig.SECRET_ACCESS_KEY;           // 用户的Secret Access Key

	    // 初始化一个BosClient
	    BosClientConfiguration config = new BosClientConfiguration();
	    config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
		//noinspection UnnecessaryLocalVariable
		BosClient client = new BosClient(config);
	    return client;
	}
	
	public static void putObject(BosClient client, String bucketName, String objectKey, String filePath){
	    // 获取指定文件
	    File file = new File(filePath);

	    // 以文件形式上传Object
	    PutObjectResponse putObjectFromFileResponse = client.putObject(bucketName, objectKey, file);


	    // 打印ETag
	    System.out.println(putObjectFromFileResponse.getETag());
	}



}
