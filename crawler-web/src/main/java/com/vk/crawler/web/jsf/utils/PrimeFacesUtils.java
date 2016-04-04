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

package com.vk.crawler.web.jsf.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

public class PrimeFacesUtils {
  
  public static SelectItem[] createFilterOptions(Map<String, String> data)  {  
    Collection<String> collection = data.values();
    SelectItem[] options = new SelectItem[collection.size() + 1];
    Iterator<String> iter = collection.iterator();
    options[0] = new SelectItem("", "Filter");
    int i = 0;
    while (iter.hasNext()) {
      String itemValue = iter.next();
      options[++i] = new SelectItem(itemValue, itemValue);
    }
    return options;  
  }

  public static List<SelectItem> getGroupSelectOneMenu(Map<String, Map<String, String>> options, String key) {
    List<SelectItem> selectMenu = new ArrayList<SelectItem>();
    Map<String, String> parents = options.get(key);
    for(String parentKey : parents.keySet()) {
      if(options.get(parentKey) == null || options.get(parentKey).isEmpty()) {
        selectMenu.add(new SelectItem(parents.get(parentKey), parents.get(parentKey)));
      }
      else {
        SelectItemGroup group = new SelectItemGroup(parents.get(parentKey));

        Map<String, String> kids = options.get(parentKey);
        SelectItem[] items = new SelectItem[kids.size()];
        int kidIndex=0;
        for (String kidKey : kids.keySet()) {
          items[kidIndex++] = new SelectItem(kids.get(kidKey), kids.get(kidKey));
        }

        group.setSelectItems(items);
        selectMenu.add(group);
      }
    }
    return selectMenu;
  }
}
