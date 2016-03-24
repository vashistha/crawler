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



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Vashistha Kumar on 22/11/2014.
 */
public class Timer {
    private static final Logger logger = LoggerFactory.getLogger(Timer.class);
    private String className;
    private String message;
    private long startTime = 0;
    private long endTime   = 0;
    public Timer() {

    }
    public Timer(String className) {
        this.className = className;
    }
    public void start(){
        this.startTime = System.currentTimeMillis();
    }
    public void start(String message){
        this.message = message;
        this.startTime = System.currentTimeMillis();
    }
    public void stop() {
        this.endTime   = System.currentTimeMillis();
        if(logger != null) {
            logger.info(className + " - "+ message + " : time taken ="+ (endTime - startTime)+"ms");
        }
    }
    public void end() {
        this.endTime   = System.currentTimeMillis();
    }
    public long getStartTime() {
        return this.startTime;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public long getTotalTime() {
        return this.endTime - this.startTime;
    }
}
