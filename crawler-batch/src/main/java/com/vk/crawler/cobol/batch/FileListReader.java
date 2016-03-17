package com.vk.crawler.cobol.batch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;

import com.vk.crawler.cobol.batch.model.FileProperties;

@StepScope
public class FileListReader implements ItemReader<FileProperties>{

  
  private String rootDirectory;
  
  @Value("#{jobParameters['rootDirectory']}")
  public void setRootDirectory(final String name) {
    this.rootDirectory = name;
  }
  
  @Override
  public FileProperties read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
    
    List<FileProperties> filesToBeProcessed = new ArrayList<FileProperties>();
    listFile("", filesToBeProcessed);
    
    return filesToBeProcessed.iterator().next();
  }
  
  public void listFile(String relativePath, List<FileProperties> files) {
    File directory = new File(rootDirectory+ File.separator + relativePath);

    File[] fList = directory.listFiles();
    for (File file : fList) {
      if (file.isFile()) {
          files.add(new FileProperties(file.getName(), relativePath, ""));
      } 
      else if (file.isDirectory()) {
        listFile(file.getName(), files);
      }
    }
  }
  
}
