package org.fundaciobit.pinbaladmin.logic.utils;

/**
 * 
 * @author anadal
 *
 */
public class FileInfo {
    
    public enum FileInfoType {
        DIRECTPDF, HTMLPDF, ERROR
    }
    

    protected String fileName;
    protected byte[] content;
    
    protected FileInfoType type;

    public FileInfo() {
        super();
    }
    
    
    public FileInfo(String error) {
        super();
        this.fileName = error;
        this.content = null;
        this.type = FileInfoType.ERROR;
    }

    public FileInfo(String fileName, byte[] content, FileInfoType type) {
        super();
        this.fileName = fileName;
        this.content = content;
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public FileInfoType getType() {
        return type;
    }

    public void setType(FileInfoType type) {
        this.type = type;
    }
    
    public String getError() {
        if (type == FileInfoType.ERROR) {
            return this.fileName;
        } else {
            return null;
        }
    }
    
    public int getSize() {
        if (content == null) {
            return -1;
        } else {
            return content.length;
        }
    }

}
