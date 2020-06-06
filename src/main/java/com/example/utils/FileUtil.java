package com.example.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	public static File toFile(MultipartFile multipartFile) {
		try {
			File result = new File(multipartFile.getOriginalFilename());
			result.createNewFile();
			FileOutputStream fos = new FileOutputStream(result);
			fos.write(multipartFile.getBytes());
			fos.close();
			return result;
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
			return null;
		}
	}
}
