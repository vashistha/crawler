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
 */

package com.vk.crawler.core.util;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {
  
  private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
  
  public static String getFileExtension (String fileName) {
    if (StringUtils.isEmpty(fileName)) {
      return null;
    }
    String extension = "";
    int i = fileName.lastIndexOf('.');
// the case that a directory may have a '.', but the filename itself doesn't (like /path/to.a/file), 
    String temp = fileName.substring(i+1);
    if (!temp.contains("/") && !temp.contains("\\")) {
      extension = temp;
    }
    return extension;
  }
  
  /**
   * assuming last word in absolutePath is file name and not a directory.
   * @param absolutePath
   * @return
   */
  public static String getFileName (String absolutePath) {
    if (StringUtils.isEmpty(absolutePath)) {
      return null;
    }
    
    int lastIndex = absolutePath.lastIndexOf("\\");
    if(lastIndex == -1) {
      lastIndex = absolutePath.lastIndexOf("/");
    }
    
    if(lastIndex == -1) {
      return absolutePath;
    }
    return absolutePath.substring(lastIndex+1);
  }
  
  /**
   * Assuming last word in absolute path is file name.
   * @param absolutePath
   * @return
   */
  public static String getFileNameWithoutExtension (String absolutePath) {
    String fileName = getFileName(absolutePath);
    if(fileName != null && fileName.contains(".")) {
      return fileName.substring(0, fileName.lastIndexOf("."));
    }
    return fileName;
  }
  
  /**
   * In real cms file name should be UUID.extension. But here to search file system manually, name of file is appended with UUID
   * @param absolutePath
   * @return
   */
  public static String getUniqueFileName (String absolutePath) {
    String fileName = getFileNameWithoutExtension(absolutePath);
    String extension = getFileExtension(absolutePath);
    return StringUtils.isEmpty(extension) ? fileName + UUID.randomUUID() : fileName + UUID.randomUUID() + "." + extension;
  }
  

  
  public static void saveFile(InputStream inStream, String fileName, String outFilePath) throws Exception {
    
    try {
      createFile(inStream, fileName, outFilePath);
    }
    catch (Exception e) {
      logger.error("saving document "+e);
    }
    finally {
      if(inStream != null) {
        inStream.close();
      }
     
    }
  }
  
  public static List<Boolean> saveFile(InputStream inStream, String fileName, String outFilePath, String tempPath) throws Exception {
    
    List<Boolean> created = new ArrayList<Boolean>();
    try {
      byte[] bytes = IOUtils.toByteArray(inStream);
      boolean createdOnSAN = createFile(bytes, fileName, outFilePath);
      created.add(createdOnSAN);
      boolean createdOnTemp = createFile(bytes, fileName, tempPath);
      created.add(createdOnTemp);
    }
    catch (Exception e) {
      logger.error("saving document "+e);
    }
    finally {
      if(inStream != null) {
        inStream.close();
      }
    }
    return created;
  }
  
  public static boolean createFile(byte[] bytes, String fileName, String outFilePath) {
    boolean rtn = false;
    try {
      if(StringUtils.isEmpty(outFilePath) || StringUtils.isEmpty(fileName)) {
        return rtn;
      }
      Files.createDirectories(Paths.get(outFilePath));
      Files.write(Paths.get(outFilePath + File.separator + fileName), bytes);
      logger.info("File "+fileName+" saved successfully at "+outFilePath);
      rtn = true;
    }
    catch (Exception e) {
    }
    return rtn;
  }

  public static boolean createFile(InputStream inStream, String fileName, String outFilePath) throws Exception {
    //TODO use apache common-io methods. UPDATE- Apache.io is less efficient than Java 7
    OutputStream outStream = null;
    boolean rtn = false;
    try {
      if(StringUtils.isEmpty(outFilePath) || StringUtils.isEmpty(fileName)) {
        return rtn;
      }
      Files.createDirectories(Paths.get(outFilePath));
      logger.info("writing file "+fileName+" to directory "+outFilePath);
      
      outStream = new FileOutputStream(new File(outFilePath, fileName));
      int read = 0;
      byte[] bytes = new byte[1024];
      
      while ((read = inStream.read(bytes)) != -1) {
        outStream.write(bytes, 0, read);
      }
      logger.info("file "+fileName+" saved successfully.");
      rtn = true;
    }
    catch (Exception e) {
      logger.error("saving document "+e);
    }
    finally {
      if(outStream != null) {
        try {
          outStream.flush();
          outStream.close();
        }
        catch (Exception e) {
          logger.error("Error in finally.");
        }
      }
    }
    return rtn;
  }

  public static List<String> readLines(String fileNameWithAbsolutePath) {
    List<String> lines = new ArrayList<String>();
    try {
      lines = org.apache.commons.io.FileUtils.readLines(new File(fileNameWithAbsolutePath), "UTF-8");
    } 
    catch (IOException e) {
      logger.error("", e);
    }
    return lines;
  }
}
