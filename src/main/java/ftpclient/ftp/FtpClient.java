package ftpclient.ftp;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpClient {

	private String server;
	private int port;
	private String user;
	private String password;
	private FTPClient ftp;
	
	public FtpClient(String server, int port, String user, String password) {
		super();
		this.server = server;
		this.port = port;
		this.user = user;
		this.password = password;
	}
	
	public void open() throws IOException {
		ftp = new FTPClient();
		ftp.addProtocolCommandListener(new PrintCommandListener(
                new PrintWriter(System.out)));
		ftp.connect(server, port);
		int reply = ftp.getReply();
		
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			throw new IOException("Exception in connecting to server");
		}
		
		ftp.login(user, password);
	}
	
	public void close() throws IOException {
		ftp.disconnect();
	}
	
	
}
