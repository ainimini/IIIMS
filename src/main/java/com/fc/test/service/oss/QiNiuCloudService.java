package com.fc.test.service.oss;

import com.baomidou.mybatisplus.extension.api.R;
import com.fc.test.mapper.auto.TsysUserMapper;
import com.fc.test.model.auto.TsysUser;
import com.fc.test.util.Result;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Random;
import java.util.UUID;

/**
 * @ClassName dell
 * @Description TOOD
 * @Author X
 * @Data 2019/11/13
 * @Version 1.0
 **/
@Service
public class QiNiuCloudService {

    @Value("${oss.qiniu.accessKey}")
    private String accessKey;

    @Value("${oss.qiniu.bucket}")
    private String bucket;

    @Value("${oss.qiniu.secretKey}")
    private String secretKey;

    @Value("${oss.qiniu.domain}")
    private String domain;

    public Result uploadToQiNiu(MultipartFile uploadFile) throws IOException {

        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        //文件上传管理器
        UploadManager uploadManager = new UploadManager(cfg);
        //生成上传凭证，然后准备上传
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        //给图片重新设定名字
        String fileName = uploadFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString().toUpperCase().replace("-", "");
        fileName = uuid + suffix;
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        Response response = uploadManager.put(uploadFile.getBytes(), fileName, upToken);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        // System.out.println(putRet.key);
        //System.out.println(putRet.hash);
        //成功 返回url name
        Result r = Result.ok()
                .put("url", domain + "/" + putRet.key)
                .put("name", putRet.key);
        return r;
    }
}
