/*
 * Copyright (C) 2016 by Vashistha kumar
 *
 *    Permission is hereby granted, free of charge, to any person obtaining a copy
 *    of this software and associated documentation files (the "Software"), to deal
 *    in the Software without restriction, including without limitation the rights
 *    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *    copies of the Software, and to permit persons to whom the Software is
 *    furnished to do so, subject to the following conditions:
 *
 *    The above copyright notice and this permission notice shall be included in
 *    all copies or substantial portions of the Software.
 *
 *    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *    THE SOFTWARE.
 */E.
 */

package com.vk.crawler.cobol.batch;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;

import com.vk.crawler.cobol.model.FileProperties;

@StepScope
public class FileListReader implements ItemReader<FileProperties> {
  private static final Logger logger = LoggerFactory.getLogger(FileListReader.class);
  @Value("#{jobParameters['rootDirectory']}")
  private String rootDirectory;
  public void setRootDirectory(final String name) {
    this.rootDirectory = name;
  }

  List<FileProperties> filesToBeProcessed;

  /**
   * Returns Item to be processed, one at time.
   */
  public FileProperties read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
    FileProperties file = null;
    if (!filesToBeProcessed.isEmpty()) {
      file = filesToBeProcessed.get(0);
      filesToBeProcessed.remove(0);
    }
    return file;
  }

  @PostConstruct
  public void init() {
    filesToBeProcessed = new ArrayList<FileProperties>();
    listFiles("", filesToBeProcessed);
    System.out.println(filesToBeProcessed);
  }


  /**
   * List the files in a directory and sub-directories. Tested in Windows, it may fail in Linux or Mac OS.
   * @param relativePath
   * @param files
   */
  void listFiles(String relativePath, List<FileProperties> files)  {
    try {
      Path path = Paths.get(rootDirectory+ File.separator + relativePath);
      DirectoryStream<Path> stream = Files.newDirectoryStream(path);
      for (Path entry : stream) {
        if (Files.isDirectory(entry)) {
          listFiles(relativePath + File.separator + entry.getFileName().toString(), files);
        }
        else if (Files.isRegularFile(entry, LinkOption.NOFOLLOW_LINKS)) {
          files.add(new FileProperties(entry.getFileName().toString(), relativePath, ""));
        }
      }
      stream.close();
    }
    catch (IOException e) {
      logger.debug("Exception in reading files", e);
    }
  }
  
  
  /**
   * Main method for testing only. 
   * @param arg
   */
  public static void main(String[] arg) {
    FileListReader obj = new FileListReader();
    obj.setRootDirectory("/home/abcd/devspace/IntelliJwork/crawler/sample");
    obj.init();
  }
}
