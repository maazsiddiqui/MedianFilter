import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		
		medianFilter mFilter = new medianFilter(args[0], args[1]);
		mFilter.loadImage();
		mFilter.mirrorFrame();
		mFilter.median3X3();
		mFilter.printMedianArray();
	}

}
