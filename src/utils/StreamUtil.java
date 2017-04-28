package utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import exception.IORuntimeException;


public class StreamUtil {
	public static void copy(InputStream is, OutputStream os){
		byte[] buffer = new byte[4096];
		try{
			for(int n = 0; -1 != (n = is.read(buffer));){
				os.write(buffer, 0 , n);
			}
		} catch(IOException e){
			throw new IORuntimeException(e);
		}
	}

}
