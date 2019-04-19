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

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.TypeExtractor;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import object.Flow;

public class FlowSchema implements DeserializationSchema<Flow> {
	
	private static final Logger _log = LoggerFactory.getLogger(FlowSchema.class);
	
    @Override
    public Flow deserialize(byte[] bytes) {
    	String s = new String(bytes);
//        System.out.println("kafka string:" + s);
//        try{
            Flow flow = new Flow();
        	JSONObject jo = JSONObject.parseObject(s);
        	flow.setSend(Long.valueOf(jo.getString("hdt_send")));
        	flow.setRecv(Long.valueOf(jo.getString("hdt_recv")));
        	flow.setMsec(Long.valueOf(jo.getString("hdt_msec")));
        	flow.setDate(new Date(Long.valueOf(jo.getString("ts")) * 1000L));
//        	System.out.println("kafka date:" + flow.date);
        	flow.setDomain(jo.getString("hdt_tp_domain"));
        	
            return flow;
//        } catch (JSONException e){
//            throw new Exception("Invalid record: " + s);
//        }
    }

//    @Override
//    public byte[] serialize(Flow flow) {
//    	return String.format("%s,%dl,%dl,%dl,%dl", flow.getDomain(), flow.getSend(), flow.getRecv(), flow.getMsec(), flow.getDate().getTime()).getBytes();
//    }
	
	@Override
	public boolean isEndOfStream(Flow nextElement) {
	    return false;
	}
    @Override
    public TypeInformation<Flow> getProducedType() {
        return TypeExtractor.getForClass(Flow.class);
    }
}
