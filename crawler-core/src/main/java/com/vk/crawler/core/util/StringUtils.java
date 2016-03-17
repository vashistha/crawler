package com.vk.crawler.core.util;
/*
 * Copyright (C) 2015 by Vashistha kumar
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */




import java.util.Random;

public class StringUtils {
  
  public static boolean isEmpty(String source) {
    return source == null ? true : "".equals(source.trim());
  }
  
  public static boolean isEqual(String s1, String s2) {
    return (s1 == null && s2 == null) || (s1 != null && s1.equalsIgnoreCase(s2));
  }
  
  public static String replaceIfEmpty(String source, String toBeAssigned) {
    if(isEmpty(source))
      return toBeAssigned;
    else
      return source;
  }
  public static String removeWhiteSpace(String source) {
    return isEmpty(source) ? source : source.replaceAll("\\s","");
  }
  
  public static String preTrimToLength(String source, int toLength) {
    
    if(StringUtils.isEmpty(source) || source.length() <= toLength) {
      return source;
    }
    else {
      return source.substring(source.length() - toLength);
    }
  }
  
  public static String trimToLength (String source, int toLength) {
    if(StringUtils.isEmpty(source) || source.length() <= toLength) {
      return source;
    }
    else {
      return source.substring(0, toLength);
    }
  }

  public static String concat(String firstText, String secondText, String separator) {
    return (firstText != null ? firstText : "")
        + (StringUtils.isEmpty(firstText) || StringUtils.isEmpty(secondText) || separator==null ? "" : separator)
        + (secondText != null ? secondText : "");
  }

  /**
   * Returns <code>length</code> characters long random alphanumeric string.
   * @param len
   * @return
   */
  public static String getRandomAlphanumeric(int len) {
    StringBuffer out=new StringBuffer();

    StringBuffer buf=new StringBuffer(128);
    for(int i=48; i<= 57;i++)buf.append((char)i); // 0-9
    for(int i=65; i<= 90;i++)buf.append((char)i); // A-Z
    for(int i=97; i<=122;i++)buf.append((char)i); // a-z
    char[] alphanumeric = buf.toString().toCharArray();
    while(out.length() < len){
      int idx=Math.abs(new Random().nextInt() % alphanumeric.length);
      out.append(alphanumeric[idx]);
    }
    return out.toString();
  }
}
