package org.wangzc.development.platform.model.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wangzc.development.platform.DevelopmentPlatformConfig;
import org.wangzc.development.platform.service.FileService;
import org.wangzc.development.platform.util.Result;

@RestController
@RequestMapping("/view")
public class ViewController {

    @Autowired
    private DevelopmentPlatformConfig config;

    @Autowired
    private FileService fileService;

    @GetMapping("/file/list")
    public Result fileList(String path) {
        Result result = Result.ok();
        result.put("fileList", fileService.fileList(config.getViewPath(), path));
        result.put("filePath", fileService.splitPath(path));
        return result;
    }
}
