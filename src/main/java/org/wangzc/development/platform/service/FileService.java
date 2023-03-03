package org.wangzc.development.platform.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    public String joinPath(String... paths);

    public List<String> splitPath(String... paths);

    public List<LocalFile> fileList(String... paths);

    public void addFile(String path, String fileName, String content);

    public void addFiles(String path, List<MultipartFile> files) throws IOException;

    public File getFile(String path) throws FileNotFoundException;

    public String readFile(String path) throws FileNotFoundException;

    public void writeFile(String path, String context) throws FileNotFoundException;

    public class LocalFile {
        private String name;
        private String type;
        private String path;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public boolean isDir() {
            return "dir".equals(type);
        }

        public boolean isFile() {
            return "file".equals(type);
        }
    }
}
