package net.the42null.personalwebsite.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class ContentPather {

	private final static String innerDefaultPath = "src/main/resources/static/content/";//TODO: Generate dynamically

	public enum Target {
		INTILLIJ_RUN("server/"+innerDefaultPath),
		RASPBERRY_DIRECT;

		private final String path;

		Target(){
			this.path = "";
		}
		Target(String path){
			this.path = path;
		}

		public String getPath() {
			return path;
		}
	}

	private String buildPath;

	public ContentPather(){//Check directories automaticallyc
		for(int i = 0; i < Target.values().length; i++){
			Path directoryPath = Paths.get(Target.values()[i].getPath());
			System.out.println("directoryPath = "+directoryPath);
			if(Files.isDirectory(directoryPath)){
				buildPath = Target.values()[i].getPath();
				return;
			}else{

			}
		}
		throw new IllegalArgumentException("Auto-pick content directory failure, please provide a location or move files to a enum defined location");
	}

	public ContentPather(Target target){
		switch(target) {
			case INTILLIJ_RUN     -> buildPath = "server/"+innerDefaultPath;
			case RASPBERRY_DIRECT -> buildPath = innerDefaultPath;
			default               -> throw new IllegalArgumentException("Enum argument not found");
		}
	}

	public File generateResourcePath(String addedToPath){
		return Paths.get(buildPath+addedToPath).toFile();
	}

}
