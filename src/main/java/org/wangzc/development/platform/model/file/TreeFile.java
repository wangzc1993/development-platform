package org.wangzc.development.platform.model.file;

import java.util.List;

public class TreeFile {
    private String label;
    private String fileName;
    private String filePath;
    private int isDir;
    private List<TreeFile> children;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<TreeFile> getChildren() {
        return children;
    }

    public void setChildren(List<TreeFile> children) {
        this.children = children;
    }

    public int getIsDir() {
        return isDir;
    }

    public void setIsDir(int isDir) {
        this.isDir = isDir;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
