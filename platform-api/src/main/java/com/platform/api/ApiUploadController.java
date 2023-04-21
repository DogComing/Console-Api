package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.util.ApiBaseAction;
import com.platform.utils.RRException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Api(tags = "上传")
@RestController
@RequestMapping("/admin-api")
public class ApiUploadController extends ApiBaseAction {

    /**
     * 上传文件
     */
//    @IgnoreAuth
//    @ApiOperation(value = "上传文件")
//    @PostMapping("/goods/upload")
//    public Object upload(@RequestParam("file") MultipartFile file) throws Exception {
//
//        if (file.isEmpty()) throw new RRException("上传文件不能为空");
//
//        //上传文件TODO
//        //获取上传文件名,包含后缀
//        String originalFilename = file.getOriginalFilename();
//        //获取后缀
//        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
//        //保存的文件名
//        String dFileName = UUID.randomUUID() + substring;
//        //保存路径
//        //所有图片存储路径
//        String path = "/Users/huzhiyuan/Desktop/";
//        //文件目录
//        String urlPath = 1 + File.separator;
//        //生成保存文件
//        File uploadFile = new File(path + urlPath + dFileName);
//        System.out.println(uploadFile);
//        //将上传文件保存到路径
//        file.transferTo(uploadFile);
//        return toResponsSuccess(uploadFile);
//    }

    /**
     * 上传文件
     */
    @IgnoreAuth
    @ApiOperation(value = "上传文件")
    @PostMapping("/goods/upload")
    public Object upload(@RequestParam("file") MultipartFile file) throws Exception {

        if (file.isEmpty()) throw new RRException("上传文件不能为空");
        //上传文件TODO
        //获取上传文件名,包含后缀
        String originalFilename = file.getOriginalFilename();
        //获取后缀
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        //保存的文件名
        String dFileName = UUID.randomUUID() + substring;
        //保存路径
        //所有图片存储路径
        String path = "D:\\nginx\\Images\\mztsqs\\goodsImg\\";
        //文件目录
        String urlPath = "Images/mztsqs/goodsImg/";
        //生成保存文件
        File uploadFile = new File(path + dFileName);
        //将上传文件保存到路径
        file.transferTo(uploadFile);
        return toResponsSuccess(urlPath + dFileName);
    }
}
