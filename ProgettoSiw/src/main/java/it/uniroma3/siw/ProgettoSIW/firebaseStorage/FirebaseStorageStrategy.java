//package it.uniroma3.siw.ProgettoSIW.firebaseStorage;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.nio.file.Files;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.google.cloud.storage.Storage;
//
//
//@Service
//public class FirebaseStorageStrategy {
//	
//	FirebaseInitializer fi;
//
//	private String bucketName="/bucket";
//	private String nftSubDir="/nfts";
//    private String projectId="esamesiw-9875e";
//    
//	
//	public String uploadFile(MultipartFile file) throws IOException {
//
//        String objectName = file.toString();
//        Storage storage = fi.getOptions().getService() ;
//        
//        return bucketName+objectName;
//    }
//	
//}
