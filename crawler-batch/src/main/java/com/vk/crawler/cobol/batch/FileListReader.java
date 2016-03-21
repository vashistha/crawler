package com.vk.crawler.cobol.batch;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;

import com.vk.crawler.cobol.batch.model.FileProperties;

import javax.annotation.PostConstruct;

@StepScope
public class FileListReader implements ItemReader<FileProperties> {
  private static final Logger logger = LoggerFactory.getLogger(FileListReader.class);
  private String rootDirectory;
  @Value("#{jobParameters['rootDirectory']}")
  public void setRootDirectory(final String name) {
    this.rootDirectory = name;
  }

  List<FileProperties> filesToBeProcessed;

  public FileProperties read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
    return filesToBeProcessed.iterator().next();
  }

  @PostConstruct
  public void init() {
    filesToBeProcessed = new ArrayList<FileProperties>();
    listFiles("", filesToBeProcessed);
  }


  public static void main(String[] arg) {
    FileListReader obj = new FileListReader();
    obj.setRootDirectory("\\home\\abcd\\devspace\\IntelliJwork\\logs");
    obj.init();
  }
  void listFiles(String relativePath, List<FileProperties> files)  {
    try {
      Path path = Paths.get(rootDirectory+ File.separator + relativePath);
      DirectoryStream<Path> stream = Files.newDirectoryStream(path);
      for (Path entry : stream) {
        if (Files.isDirectory(entry)) {
          listFiles(entry.getFileName().toString(), files);
        }
        files.add(new FileProperties(entry.getFileName().toString(), relativePath, ""));
      }
    }
    catch (IOException e) {
      logger.debug("Exception in reading files", e);
    }
  }
}
