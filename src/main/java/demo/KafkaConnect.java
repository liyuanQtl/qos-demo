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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;

import object.Flow;
import demo.FlowSchema;
import demo.PropertyReader;
import demo.CustomWatermarkEmitter;

public class KafkaConnect {
	
	private static final Logger _log = LoggerFactory.getLogger(KafkaConnect.class);
	
	public final static FlinkKafkaConsumer010<Flow> getKafkaConsumer(String propertyFilePath) throws Exception {
		Properties properties = PropertyReader.getProp(propertyFilePath);
		System.out.println("topic:"+properties.getProperty("topic"));
		_log.info("topic:"+properties.getProperty("topic"));
//		if (null == schema) 
//			schema = new SimpleStringSchema();
		FlinkKafkaConsumer010<Flow> myConsumer =
			    new FlinkKafkaConsumer010<Flow>(properties.getProperty("topic"), new FlowSchema(), properties);
		myConsumer.assignTimestampsAndWatermarks(new CustomWatermarkEmitter());
		return myConsumer;
	}
}
