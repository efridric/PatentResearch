package com.efridric.util;
import java.awt.*;

public class FileDialogGUI {
	
	public static String loadFile (Frame f, String title, String defDir, String fileType) {
		FileDialog fd = new FileDialog(f, title, FileDialog.LOAD);
		fd.setFile(fileType);
		fd.setDirectory(defDir);
		fd.setLocation(50, 50);
		fd.show();
		//return fd.getFile(); //Just the file?
		return fd.getDirectory() + /*System.getProperty("file.separator") +*/  fd.getFile(); //full file and path
	}
}