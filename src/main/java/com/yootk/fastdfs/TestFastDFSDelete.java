package com.yootk.fastdfs;

import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

public class TestFastDFSDelete {
    public static void main(String[] args) throws Exception {
        // 1、定义要删除文件名称
        String fdfsFilePath = "group1/M00/00/00/wKgcil0mDHWAa-_tAABAnVyAAEQ029.png" ;
        // 2、在使用FastDFS处理的时候，使用了一个专属的配置文件'
        ClassPathResource resource = new ClassPathResource("fastdfs.properties") ;
        ClientGlobal.init(resource.getClassLoader()
                .getResource("fastdfs.properties").getPath());
        // 3、创建Tracker客户端的处理类
        TrackerClient client = new TrackerClient() ;
        TrackerServer trackerServer = client.getConnection();// 获取Tracker服务对象
        StorageServer storageServer = null ;
        // 4、创建了一个存储的客户端类对象
        StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);
        // 5、文件删除
        int code = storageClient.delete_file1(fdfsFilePath);
        System.out.println("【文件删除】code = " + code);
        trackerServer.close();
    }
}
