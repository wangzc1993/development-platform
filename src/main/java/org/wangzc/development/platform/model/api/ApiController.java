package org.wangzc.development.platform.model.api;

import java.io.FileNotFoundException;
import java.util.List;

import org.nutz.lang.random.R;
import org.nutz.lang.util.NutMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wangzc.development.platform.DevelopmentPlatformConfig;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.ApiUtils;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;
import org.wangzc.development.platform.model.api.engine.method.ApiMethodMapping;
import org.wangzc.development.platform.service.FileService;
import org.wangzc.development.platform.util.AlertException;
import org.wangzc.development.platform.util.Result;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private FileService fileService;

    @Autowired
    private DevelopmentPlatformConfig config;

    @GetMapping("/gen-id")
    public Result genId() {
        return Result.okResult(R.UU32());
    }

    @PostMapping("/execute")
    public Result execute(@RequestBody NutMap map) throws AlertException {
        String path = map.getString("path");
        ApiMethodMapping.init(config.getFilePath() + config.getMethodMappingPath());
        ApiEngine apiEngine = new ApiEngine();
        Object execute;
        try {
            apiEngine.init(fileService.readFile(path));
            apiEngine.check();
            execute = apiEngine.execute(map.getAs("parameters", NutMap.class));
        } catch (TagException | FileNotFoundException e) {
            throw new AlertException(e.getMessage(), e);
        }
        Result result = Result.okResult(execute);
        result.put("logs", apiEngine.getApiContext().getLogs());
        return result;
    }

    @PostMapping("/check")
    public Result check(@RequestBody NutMap map) throws AlertException {
        String json = map.getString("json");
        ApiMethodMapping.init(config.getFilePath() + config.getMethodMappingPath());
        ApiEngine apiEngine = new ApiEngine();
        try {
            apiEngine.init(ApiUtils.jsonToXml(json));
            List<TagCheckException> checkAll = apiEngine.checkAll();
            return Result.okResult(checkAll);
        } catch (TagException e) {
            throw new AlertException(e.getMessage(), e);
        }
    }
}
