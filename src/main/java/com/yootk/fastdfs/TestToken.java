package com.yootk.fastdfs;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.ProtoCommon;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;

public class TestToken {
    public static void main(String[] args) throws Exception {
        // 1、设置要进行查看的图片路径（此时没有组名称）
        String fdfsFilePath = "M00/00/00/wKgcil0mEdeAQ6F4AABAnVyAAEQ327.png" ;
        // 2、根据fastdfs.properties文件进行配置初始化
        ClassPathResource resource = new ClassPathResource("fastdfs.properties") ;
        ClientGlobal.init(resource.getClassLoader()
                .getResource("fastdfs.properties").getPath());
        // 3、创建Tracker客户端的操作对象
        TrackerClient trackerClient = new TrackerClient() ;
        TrackerServer trackerServer = trackerClient.getConnection();
        // 4、所有的Token都是有其失效时间配置的
        int ts = (int) (System.currentTimeMillis() / 1000) ;
        // 5、拼凑出要进行访问的页面路径
        StringBuffer accessBuffer = new StringBuffer() ;
        accessBuffer.append("http://") ;
        accessBuffer.append(trackerServer.getInetSocketAddress().getHostString()) ;
        accessBuffer.append("/group2/").append(fdfsFilePath) ;
        accessBuffer.append("?token=").append(ProtoCommon.getToken(fdfsFilePath,ts, ClientGlobal.g_secret_key)) ;
        accessBuffer.append("&ts=").append(ts) ;
        System.out.println(accessBuffer);
    }
}
