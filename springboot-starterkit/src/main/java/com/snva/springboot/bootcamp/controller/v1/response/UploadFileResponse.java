package com.snva.springboot.bootcamp.controller.v1.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private  String parsedResume;

//    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size, String parsedResume) {
//        this.fileName = fileName;
//        this.fileDownloadUri = fileDownloadUri;
//        this.fileType = fileType;
//        this.size = size;
//        this.parsedResume=parsedResume;
//    }

	// Getters and Setters (Omitted for brevity)
}