package app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.swing.JFileChooser;

import org.apache.tomcat.jni.Sockaddr;

import app.SSLSocketKeystoreFactory.SecureType;
 
/**
 * @web http://java-buddy.blogspot.com/
 */
public class JavaSSLServer {
     
    static final int port = 8000;
 
         public void ssl () throws KeyManagementException, NoSuchAlgorithmException, CertificateException, KeyStoreException, SocketException, IOException {
        
        	 
        	 SSLServerSocketFactory sslServerSocketFactory = 
                (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
        
        System.setProperty("javax.net.ssl.keyStore", "F:\\WorkSpaceEstudos\\SSLServer\\target\\keystore");
        System.setProperty("javax.net.ssl.keyStorePassword",            "password");
        System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
        try {
			SSLContext sc = SSLContext.getInstance("TLS_VERSION");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // test commit
        try {
			KeyStore ks = KeyStore.getInstance("JKS");
			InputStream ksIs = new FileInputStream("F:\\WorkSpaceEstudos\\SSLServer\\target\\keystore");
					try {
						ks.load(ksIs, "password".toCharArray());
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (CertificateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}finally {
					if(ksIs != null) {
						try {
							ksIs.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
						
				}
					  KeyManagerFactory kmf = null;
					try {
						kmf = KeyManagerFactory.getInstance(KeyManagerFactory
						            .getDefaultAlgorithm());
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					    try {
							kmf.init(ks, "password".toCharArray());
						} catch (UnrecoverableKeyException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        try {
            ServerSocket sslServerSocket = 
                    sslServerSocketFactory.createServerSocket(port);
            
        	        	
//            ServerSocket sslServerSocket = 
//                    sslServerSocketFactory.createServerSocket(port, 10, null);

            abstract class SocketAddress {
            	
            }
            
            java.net.SocketAddress add = null;
            
//            sslServerSocket.bind(add);
            
            System.out.println("SSL ServerSocket started");
            System.out.println(sslServerSocket.toString());
             
            Socket socket = sslServerSocket.accept();
            System.out.println("Canal = " + sslServerSocket.getChannel());
            System.out.println("Porta = " +sslServerSocket.getLocalPort());
            System.out.println("Endereço = " +sslServerSocket.getLocalSocketAddress());
            System.out.println("ServerSocket accepted");
            
            
             
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            try (BufferedReader bufferedReader = 
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream()))) {
                String line;
                while((line = bufferedReader.readLine()) != null){
                    System.out.println(line);
                    out.println(line);
                }
            }
            System.out.println("Closed");
             
        } catch (IOException ex) {
            Logger.getLogger(JavaSSLServer.class.getName())
                    .log(Level.SEVERE, null, ex);
        	}
         }
}