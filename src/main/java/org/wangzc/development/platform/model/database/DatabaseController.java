package org.wangzc.development.platform.model.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wangzc.development.platform.DevelopmentPlatformConfig;
import org.wangzc.development.platform.service.FileService;
import org.wangzc.development.platform.util.Result;

@RestController
@RequestMapping("/database")
public class DatabaseController {
    
    @Autowired
    private DevelopmentPlatformConfig config;

    @Autowired
    private FileService fileService;

    @GetMapping("/file/list")
    public Result fileList(String path) {
        Result result = Result.ok();
        result.put("fileList", fileService.fileList(config.getDatabasePath(), path));
        result.put("filePath", fileService.splitPath(path));
        return result;
    }
}
