package resources;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

public class FileManager {
	
	public static void deleteFolder(File folder){
		File[] files = folder.listFiles();
		if(files != null){
			for(File f: files){
				if(f.isDirectory()){
					deleteFolder(f);
				}else{
					f.delete();
				}
			}
			folder.delete();
		}
	}
	
	public static void getFiles(String from, String to){ // TODO Add a check if files already exist and implement git pull
		File localPath = new File(to);
		deleteFolder(localPath);
		
		System.out.println("Cloning "+from+" to "+to);
		
		try(Git repo = Git.cloneRepository()
				.setURI(from)
				.setDirectory(localPath)
				.call()){
			//System.out.println("Created: "+repo.getRepository().getDirectory());
		} catch (InvalidRemoteException e) {
			e.printStackTrace();
		} catch (TransportException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}
	
	public static void replaceFile(String from, String to){
		FileSystem system = FileSystems.getDefault();
		File temp = new File(to);
		temp.delete();
		
		Path update = system.getPath(from);
		Path current = system.getPath(to);
		try {
			Files.copy(update, current);
			//System.out.println("Files Copied!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
