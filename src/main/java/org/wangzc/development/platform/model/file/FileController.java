package org.wangzc.development.platform.model.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nutz.lang.Files;
import org.nutz.lang.util.NutMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.wangzc.development.platform.DevelopmentPlatformConfig;
import org.wangzc.development.platform.model.api.engine.ApiUtils;
import org.wangzc.development.platform.service.FileService;
import org.wangzc.development.platform.util.AlertException;
import org.wangzc.development.platform.util.Result;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private DevelopmentPlatformConfig config;

    @Autowired
    private FileService fileService;

    @GetMapping("/tree")
    public Result tree(String type) throws FileNotFoundException, AlertException {
        File file = fileService.getFile(type);
        return Result.okResult(eachFiles(file));
    }

    @GetMapping("/read")
    public Result read(String path) throws FileNotFoundException {
        String readFile = fileService.readFile(path);
        return Result.okResult(ApiUtils.xmlToJson(readFile));
    }

    @PostMapping("/save")
    public Result save(@RequestBody NutMap map) throws FileNotFoundException {
        String path = map.getString("path");
        String json = map.getString("json");
        System.out.println(path + json);
        fileService.writeFile(path, ApiUtils.jsonToXml(json));
        return Result.ok();
    }

    @GetMapping("/mk-dir")
    public Result mkDir(String path, String dirName) throws AlertException, FileNotFoundException {
        File dir = fileService.getFile(path);
        if (!dir.isDirectory()) {
            throw new AlertException("要添加的不是目录");
        }
        Files.makeDir(new File(dir.getPath(), dirName));
        return Result.ok();
    }

    @GetMapping("/mk-file")
    public Result mkFile(String path, String fileName) throws AlertException, FileNotFoundException {
        File dir = fileService.getFile(path);
        if (!dir.isDirectory()) {
            throw new AlertException("要添加的不是目录");
        }
        Files.createFileIfNoExists(new File(dir.getPath(), fileName + ".xml"));
        return Result.ok();
    }

    @GetMapping("/delete")
    public Result delete(String path) throws FileNotFoundException {
        File file = fileService.getFile(path);
        if (file.isDirectory()) {
            Files.deleteDir(file);
        }
        if (file.isFile()) {
            Files.deleteFile(file);
        }
        return Result.ok();
    }

    private List<TreeFile> eachFiles(File file) {
        List<TreeFile> files = new ArrayList<>();
        if (file.isFile()) {
            return files;
        }
        for (File f : file.listFiles()) {
            TreeFile treeFile = new TreeFile();
            treeFile.setFileName(f.getName());
            treeFile.setLabel(f.getName());
            treeFile.setIsDir(f.isDirectory() ? 1 : 0);
            treeFile.setChildren(eachFiles(f));
            treeFile.setFilePath(f.getPath().replace("." + File.separator + "files" + File.separator, ""));
            files.add(treeFile);
        }
        return files;
    }

    @GetMapping("/file/list")
    public Result fileList(String path) {
        Result result = Result.ok();
        result.put("fileList", fileService.fileList(config.getResourcePath(), path));
        result.put("filePath", fileService.splitPath(path));
        return result;
    }

    @PostMapping("/file/upload")
    public Result fileUpload(String path, HttpServletRequest request) throws IOException, AlertException {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("files");
        if (null == files || 0 == files.size()) {
            throw new AlertException("文件不存在");
        }
        fileService.addFiles(fileService.joinPath(config.getResourcePath(), path), files);
        return Result.ok();
    }
}
