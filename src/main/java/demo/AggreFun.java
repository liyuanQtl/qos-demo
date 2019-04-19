package demo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import object.Flow;

private static class AggrFun implements AggregateFunction<Flow, Flow, Flow> {
	@Override
	public Flow createAccumulator() {
		Flow flow = new Flow();
		return flow;
	}
	
	@Override
	public Flow add(Flow value, Flow accumulator) {
		Flow flow = new Flow();
		flow.setDomain(value.getDomain());
		flow.setDate(value.getDate());
		flow.setSend(value.getSend() + accumulator.getSend());
		flow.setRecv(value.getRecv() + accumulator.getRecv());
		flow.setMsec(value.getMsec() + accumulator.getMsec());
		return flow;
	}
	
	@Override
	public Flow getResult(Flow accumulator) {
		return accumulator;
	}
	
	@Override
	public Flow merge(Flow a, Flow b) {
		Flow flow = new Flow();
		flow.setDomain(value.getDomain());
		flow.setDate(value.getDate());
		flow.setSend(value.getSend() + accumulator.getSend());
		flow.setRecv(value.getRecv() + accumulator.getRecv());
		flow.setMsec(value.getMsec() + accumulator.getMsec());
		return flow;
	}
}