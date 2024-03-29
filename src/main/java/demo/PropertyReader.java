/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package demo;

import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyReader {
	
	private static final Logger _log = LoggerFactory.getLogger(PropertyReader.class);
	
	public final static Properties getProp(String propertyFilePath) {
	    if (propertyFilePath == null) {  
		    _log.error("propertyFilePath is null!");  
		    return null;  
		}  
		Properties prop = loadPropertyFile(propertyFilePath);
		return prop;
	}
	/** 
	 *  
	 * @discription: add properties  
	 *  
	 * @param propertyFilePath 
	 * @return properties
	 * @author lillian create：2019-04-10 
	 * @author lillian update：2019-04-10 
	 */  
	private static Properties loadPropertyFile(String propertyFilePath) {  
		InputStream is = PropertyReader.class.getResourceAsStream(propertyFilePath);
		System.out.println("is:"+is);
	    if (is == null) {
	        return loadPropertyFileByFileSystem(propertyFilePath);  
	    }  
	    Properties ppts = new Properties();  
	    try { 
	        ppts.load(is);  
	        return ppts;  
	    } catch (Exception e) { 
	        _log.debug("load prop file error:" + propertyFilePath, e);  
	        return null;  
	    }  
	}
  
	/** 
	 *  
	 * @discription: add properties from system 
	 *  
	 * @param propertyFilePath 
	 * @return properties
	 * @author lillian create：2019-04-10 
	 * @author lillian update：2019-04-10 
	 */  
    private static Properties loadPropertyFileByFileSystem(final String propertyFilePath) {  
	    try {  
	        Properties ppts = new Properties();  
	        ppts.load(new FileInputStream(propertyFilePath));  
	        return ppts;  
	    } catch (FileNotFoundException e) {  
	        _log.error("FileInputStream(\"" + propertyFilePath  
	                 + "\")! FileNotFoundException: " + e);  
	        return null;  
	    } catch (IOException e) {  
	        _log.error("Properties.load(InputStream)! IOException: " + e);  
	        return null;  
	    }
    }
    
//    public static void main(String[] args) throws Exception {
//    	Properties prop = getProp("/kafka.properties");
//    	System.out.println(prop.getProperty("topic"));
//    }
}
