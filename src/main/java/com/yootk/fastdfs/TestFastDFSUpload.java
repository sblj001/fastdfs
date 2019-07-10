package com.yootk.fastdfs;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.Arrays;

public class TestFastDFSUpload {
    public static void main(String[] args) throws Exception {
        // 1、如果要想进行文件上传，则需要通过File类设置有一个文件的上传路径
        File file = new File("E:" + File.separator + "xbwawa.png") ;
        // 2、对于文件的上传一定要提供有相应的文件后缀
        String fileExt = "png" ;
        // 3、在使用FastDFS处理的时候，使用了一个专属的配置文件'
        ClassPathResource resource = new ClassPathResource("fastdfs.properties") ;
        // 4、如果要想进行上传，则需要进行上传的初始化操作，需要通过资源路径进行加载
        ClientGlobal.init(resource.getClassLoader()
                .getResource("fastdfs.properties").getPath());
        // 5、创建Tracker客户端的处理类
        TrackerClient client = new TrackerClient() ;
        TrackerServer trackerServer = client.getConnection();// 获取Tracker服务对象
        StorageServer storageServer = null ;
        // 6、创建了一个存储的客户端类对象
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        // 7、将需要上传的内容进行包装
        NameValuePair[] metaList = new NameValuePair[3] ; // 定义一个数据的集合
        metaList[0] = new NameValuePair("fileName",file.getName()) ;
        metaList[1] = new NameValuePair("fileExtName",fileExt) ;
        metaList[2] = new NameValuePair("fileLength", String.valueOf(file.length())) ;
        // 8、进行文件上传
        String[] uploadFile = storageClient.upload_file(file.getPath(), fileExt, metaList);
        System.out.println("【上传信息】" + Arrays.toString(uploadFile));
        trackerServer.close();
    }
}
