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

package com.vk.crawler.core.exception;

import com.vk.crawler.core.util.Constants.MessageSeverity;

public interface ErrorMessage {
  ViewMessage MSG_FATAL = new ViewMessage(MessageSeverity.FATAL, "Fatal error", "System is unable to recover, please try later.");
  ViewMessage MSG_ERROR = new ViewMessage(MessageSeverity.ERROR, "Error", "There is processing error, please try again.");
  
  ViewMessage NO_RESULT = new ViewMessage(MessageSeverity.INFO, "No result", "No result found for given search criteria.");
  ViewMessage MSG_SAVED = new ViewMessage(MessageSeverity.INFO, "Saved", "Saved successfully.");
}
