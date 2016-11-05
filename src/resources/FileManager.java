package resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import core.Task;

public class FileManager {
	
	public static final String REPO_LOCATION = "https://github.com/Poxleit/StudymeisterFONISC";
	
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
		
		//System.out.println("Cloning "+from+" to "+to);
		
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
		//File temp = new File(to); -not needed with the REPLACE_EXISTING?
		//deleteFolder(temp);
		
		Path update = system.getPath(from);
		Path current = system.getPath(to);
		try {
			Files.copy(update, current, StandardCopyOption.REPLACE_EXISTING);
			//System.out.println("Files Copied!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveTasks(LinkedList<Task> tasksph, File destinationFile){
        
        Task[] tasks = new Task[tasksph.size()];
        tasksph.toArray(tasks);
       
        
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().serializeNulls();
        Gson gson = builder.create();
       
        
        String json = gson.toJson(tasks);
       
      
        try( PrintWriter out = new PrintWriter(destinationFile)){
            out.println(json);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
   
    public static LinkedList<Task> loadTasks(File sourceFile){
        try {
            List<String> list = Files.readAllLines(sourceFile.toPath());
           

            String json = "";
            for(String part : list) json += part;
           
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting().serializeNulls();
            Gson gson = builder.create();
           
            Task[] tasks = gson.fromJson(json, Task[].class);
           
            LinkedList<Task> tasksph = new LinkedList<Task>();
            for(Task task : tasks) tasksph.add(task);
           

            return tasksph;
           
        } catch (IOException e) {
            e.printStackTrace();
        }
       
        return null;
    }
}
