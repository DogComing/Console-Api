package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.service.SysConfigService;
import com.platform.util.ApiBaseAction;
import com.platform.utils.RRException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SysUploadController extends ApiBaseAction {

    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 上传文件
     */
//    @IgnoreAuth
//    @ApiOperation(value = "上传文件")
//    @PostMapping("/common/upload")
//    public Object upload(@RequestParam("file") MultipartFile file, @RequestParam("key") String key) throws Exception {
//
//        if (file.isEmpty()) throw new RRException("上传文件不能为空");
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
//        //将上传文件保存到路径
//        file.transferTo(uploadFile);
//        sysConfigService.updateValueByKey(key, urlPath + dFileName);
//        return toResponsSuccess(true);
//    }

    /**
     * 上传文件
     */
    @IgnoreAuth
    @ApiOperation(value = "上传文件")
    @PostMapping("/common/upload")
    public Object upload(@RequestParam("file") MultipartFile file, @RequestParam("key") String key) throws Exception {

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
        String path = "D:\\nginx\\Images\\mztsqs\\admin\\";
        //文件目录
        String urlPath = "Images/mztsqs/admin/";
        //生成保存文件
        File uploadFile = new File(path + dFileName);
        //将上传文件保存到路径
        file.transferTo(uploadFile);
        sysConfigService.updateValueByKey(key, urlPath + dFileName);
        return toResponsSuccess(urlPath + dFileName);
    }

    /**
     * 上传文件
     */
    @IgnoreAuth
    @ApiOperation(value = "上传文件")
    @PostMapping("/store/upload")
    public Object storeLogoUpload(@RequestParam("file") MultipartFile file) throws Exception {

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
        String path = "D:\\nginx\\Images\\mztsqs\\store\\";
        //文件目录
        String urlPath = "Images/mztsqs/store/";
        //生成保存文件
        File uploadFile = new File(path + dFileName);
        //将上传文件保存到路径
        file.transferTo(uploadFile);
        return toResponsSuccess(urlPath + dFileName);
    }
}
