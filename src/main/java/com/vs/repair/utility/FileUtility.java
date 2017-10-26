package com.vs.repair.utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;

public class FileUtility {

	private final static Logger LOGGER = LoggerFactory.getLogger(FileUtility.class.getName());
	private ResourceLoader mResourceLoader;

	public FileUtility(ResourceLoader resourceLoader) {
		this.mResourceLoader = resourceLoader;
	}

	public String readResourceFile(String resourceFolderName, String resourceFileName) {
		try {
			StringBuilder fileContent = new StringBuilder();
			byte[] buffer = new byte[500];
			FileInputStream fileInputStream = new FileInputStream(mResourceLoader.getResource("classpath:" + resourceFolderName + "/" + resourceFileName).getFile());
			while(fileInputStream.read(buffer) != -1){
				fileContent.append(new String(buffer));
			}
			fileInputStream.close();
			return fileContent.toString();
		} catch (IOException e) {
			LOGGER.error("File read error for file: " + resourceFolderName + "/" + resourceFileName);
			e.printStackTrace();
		}
		return null;
	}
}
