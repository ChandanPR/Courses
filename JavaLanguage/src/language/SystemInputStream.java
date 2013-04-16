package language;

import java.io.IOException;
import java.io.InputStream;

public class SystemInputStream extends InputStream{
	
	InputStream in;
	
	public SystemInputStream(InputStream in){
		this.in = in;
	}
	
	public void setIn(InputStream in) throws IOException {
		this.in.close();
		this.in = in;
	}

	@Override
	public int read() throws IOException {
		return in.read();
	}
	
	@Override
	public void close() throws IOException {
		//DO NOTHING
	}

}
