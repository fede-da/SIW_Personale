//package it.uniroma3.siw.ProgettoSIW.firebaseStorage;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.stereotype.Service;
//
//import com.google.api.services.storage.Storage;
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.cloud.storage.StorageOptions;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//
//@Service
//public class FirebaseInitializer {
//	private StorageOptions storageOptions;
//	private String bucketName="/bucket";
//	private String nftSubDir="/nfts";
//    private String projectId="esamesiw-9875e";
//	
//	@PostConstruct
//	public void init() throws IOException {
//		
//		try {
//			FileInputStream serviceAccount =
//			        new FileInputStream("/Users/mac.fede/Documents/GitHub/MuseoSIW/esamesiw-9875e-firebase-adminsdk-iry2d-5919f4bd9f.json");
//            
//			FirebaseOptions.Builder options = FirebaseOptions.builder()
//					  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//					  .setStorageBucket("esamesiw-9875e.appspot.com");
//			
//			
//			FirebaseApp.initializeApp();
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//
//	}
//	
//	StorageOptions getOptions() {
//		return this.storageOptions;
//	}
//
//}
