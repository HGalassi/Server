package app;


import java.io.IOException;
import java.net.SocketException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Cliente;

@RestController
public class LojaService{

	@RequestMapping(value = "/getCarrinho", produces = "application/json")
	public ResponseEntity<?> getCarrinho () throws KeyManagementException, NoSuchAlgorithmException, CertificateException, KeyStoreException, SocketException, IOException{
		
		JavaSSLServer ssl = new JavaSSLServer();
		ssl.ssl();
		Cliente cliente = new Cliente();
		
		cliente.setCPF("111.222.333-44");
		cliente.setCreditCard("1111 2222  3333 4444");
		cliente.setCvs("123");
		cliente.setValidade("1223");
		cliente.setNome("Um cliente qualquer");
		Jwt jwt = new Jwt();
		String id = "01234567890";
		String iss = "um usuario qualquer";
		String sub = "uma autorizadora qualquer";
		String key = "9d6f407b-ab09-4ffa-9672-de452905bf4e";
		long exp = System.currentTimeMillis() + 300000; // EQ. 5 MIN
		long ati = System.currentTimeMillis();
		// String id, String issuer, String subject, long ttlMillis
		
		
		String createJWT = jwt.createJWT(id, iss, sub, exp);
		cliente.setToken(createJWT);
		
		
		return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
	}
}


//@CrossOrigin(origins = "*")
//@RestController("/compra")
//
//public class CompraService {
//
//	@GetMapping(value = "/getCarrinho", produces = "application/json",consumes = "application/json")
//	public ResponseEntity<?> getCarrinho (){
//		
//		Cliente cliente = new Cliente();
//		cliente.setCPF("111.222.333-44");
//		cliente.setCreditCard("1111 2222  3333 4444");
//		cliente.setCvs("123");
//		cliente.setValidade("1223");
//		cliente.setNome("Um cliente qualquer");
//		
//		return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
//	}
