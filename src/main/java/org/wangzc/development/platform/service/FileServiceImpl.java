package org.wangzc.development.platform.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.nutz.lang.Files;
import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.wangzc.development.platform.DevelopmentPlatformConfig;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private DevelopmentPlatformConfig config;

    @Override
    public String joinPath(String... paths) {
        return "/" + Strings.join("/", splitPath(paths));
    }

    @Override
    public List<String> splitPath(String... paths) {
        List<String> pathList = new ArrayList<>();
        for (String path : paths) {
            if (Strings.isBlank(path)) {
                return new ArrayList<>();
            }
            for (String p : path.split("/")) {
                if (Strings.isNotBlank(p)) {
                    pathList.add(p);
                }
            }
        }
        return pathList;
    }

    @Override
    public List<LocalFile> fileList(String... paths) {
        StringBuilder path = new StringBuilder();
        for (String p : paths) {
            if (Strings.isBlank(p)) {
                p = "";
                continue;
            }
            if (!p.startsWith("/")) {
                p = "/" + p;
            }
            if (p.endsWith("/")) {
                p = p.substring(0, p.length() - 1);
            }
            path.append(p);
        }
        File file = Files.createDirIfNoExists(config.getFilePath() + path);
        List<LocalFile> files = new ArrayList<>();
        for (File f : file.listFiles()) {
            LocalFile localFile = new LocalFile();
            localFile.setName(f.getName());
            localFile.setPath(f.getPath());
            localFile.setType(f.isFile() ? "file" : "dir");
            files.add(localFile);
        }
        files.sort(new Comparator<LocalFile>() {

            @Override
            public int compare(LocalFile o1, LocalFile o2) {
                if (o1.isDir() && o2.isFile()) {
                    return -1;
                }
                if (o1.isFile() && o2.isDir()) {
                    return 1;
                }
                return o1.getName().compareTo(o2.getName());
            }

        });
        return files;
    }

    @Override
    public void addFile(String path, String fileName, String content) {
        File file = new File(config.getFilePath() + joinPath(path) + "/" + fileName);
        Files.write(file, content);
    }

    @Override
    public void addFiles(String path, List<MultipartFile> files) throws IOException {
        for (MultipartFile file : files) {
            File f = new File(config.getFilePath() + joinPath(path) + "/" + file.getOriginalFilename());
            Files.write(f, file.getInputStream());
        }
    }

    public File getFile(String path) throws FileNotFoundException {
        File file = new File(config.getFilePath() + joinPath(path));
        if (!file.exists()) {
            throw new FileNotFoundException("文件不存在：" + config.getFilePath() + joinPath(path));
        }
        return file;
    }

    public String readFile(String path) throws FileNotFoundException {
        return Files.read(getFile(path));
    }

    public void writeFile(String path, String context) throws FileNotFoundException {
        File file = getFile(path);
        Files.write(file, context);
    }
}
