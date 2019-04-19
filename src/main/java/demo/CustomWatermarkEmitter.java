package demo;
import org.apache.flink.streaming.api.functions.AssignerWithPunctuatedWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import object.Flow;

public class CustomWatermarkEmitter implements AssignerWithPunctuatedWatermarks<Flow> {
	
	private static final Logger _log = LoggerFactory.getLogger(CustomWatermarkEmitter.class);

    @Override
    public long extractTimestamp(Flow element, long previousElementTimestamp) {
//    	System.out.println("element.getDate().getTime():" + element.getDate().getTime());
        return element.getDate().getTime();
    }

    @Override
    public Watermark checkAndGetNextWatermark(Flow lastElement, long extractedTimestamp) {
//    	System.out.println("extractedTimestamp: " + extractedTimestamp);
        return new Watermark(extractedTimestamp - 60 * 1000) ;
    }
}
