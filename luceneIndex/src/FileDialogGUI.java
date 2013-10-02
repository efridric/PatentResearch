import java.awt.*;

public class FileDialogGUI {
	
	public String loadFile (Frame f, String title, String defDir, String fileType) {
		FileDialog fd = new FileDialog(f, title, FileDialog.LOAD);
		fd.setFile(fileType);
		fd.setDirectory(defDir);
		fd.setLocation(50, 50);
		fd.show();
		//return fd.getFile(); //Just the file?
		return fd.getDirectory() + /*System.getProperty("file.separator") +*/  fd.getFile(); //full file and path
	}
		
	public String saveFile (Frame f, String title, String defDir /*String fileType*/) {
	    FileDialog fd = new FileDialog(f, title, FileDialog.SAVE);
	    //fd.setFile(fileType);
	    fd.setDirectory(defDir);
	    fd.setLocation(50, 50);
	    fd.show();
	    return fd.getFile();
	    }
}

/*public static void main(String s[]) {
  fileDialog fileDia = new fileDialog();
  System.out.println
    ("Loading : " 
        + fileDia.loadFile(new Frame(), "Open...", ".\\", "*"));
  System.exit(0);
  }
} */
