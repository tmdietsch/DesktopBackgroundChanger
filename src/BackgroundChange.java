import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.UINT_PTR;
import com.sun.jna.win32.*;

public class BackgroundChange {

	public String source;
	public String oldImage;
	
	public BackgroundChange() {
		source = "C:\\Users\\DietschTM18\\Pictures\\Desktop Background";
		oldImage = "";
	}
	
	public void changeDefault() {
		String image = "";
		try {
			image = getImageLoc();
			changeBackground(image);
		} catch (IOException ioe) {
		}
		
	}
	
	public void changeBackground(String image) {
		
		String path = source  + "\\" + image;
		
		SPI.INSTANCE.SystemParametersInfo(
		          new UINT_PTR(SPI.SPI_SETDESKWALLPAPER), 
		          new UINT_PTR(0), 
		          path, 
		          new UINT_PTR(SPI.SPIF_UPDATEINIFILE | SPI.SPIF_SENDWININICHANGE)
				);
	}

	private String getImageLoc() throws IOException {
		
		final File imageFile = new File(source);
		Random rand = new Random();
		
		ArrayList<String> images = new ArrayList<>(Arrays.asList(imageFile.list()));
		images.remove(oldImage);
		String str = images.get(rand.nextInt(images.size()));
		oldImage = str;
		return str;
		
	}
	
	private interface SPI extends StdCallLibrary {

	      //from MSDN article
	      long SPI_SETDESKWALLPAPER = 20;
	      long SPIF_UPDATEINIFILE = 0x01;
	      long SPIF_SENDWININICHANGE = 0x02;
	      
	      @SuppressWarnings("serial")
	      SPI INSTANCE = (SPI) Native.load("user32", SPI.class, new HashMap<String, Object>() {
	         {
	            put(OPTION_TYPE_MAPPER, W32APITypeMapper.UNICODE);
	            put(OPTION_FUNCTION_MAPPER, W32APIFunctionMapper.UNICODE);
	         }
	      });

	      boolean SystemParametersInfo(
	          UINT_PTR uiAction,
	          UINT_PTR uiParam,
	          String pvParam,
	          UINT_PTR fWinIni
	        );
	  }
	
}