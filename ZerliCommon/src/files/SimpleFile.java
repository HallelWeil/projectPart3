package files;

import java.io.Serializable;

public class SimpleFile implements Serializable {

	/**
	 * The files saved as bytes
	 */
	private byte[] data;
	private String fileName;
	private String fileType;

	
	public void initArray(int size)
	{
		data = new byte [size];	
	}
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public String getFullFileName() {
		return fileName + "." + fileType;
	}
}
