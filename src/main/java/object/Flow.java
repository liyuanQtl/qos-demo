package object;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Flow {
	
	public Long hdt_send = 0L;
	public Long hdt_recv = 0L;
	public Long hdt_msec = 0L;
	public String hdt_tp_domain = "";
	public Date date = new Date();
	private static final Logger _log = LoggerFactory.getLogger(Flow.class);
	
//	public Flow(String domain, Long send, Long recv, Long msec, Date date) {
//		this.hdt_tp_domain = domain;
//		this.hdt_send = send;
//		this.hdt_recv = recv;
//		this.hdt_msec = msec;
//		this.date = date;
//	}
//	
//	public Flow() {
//	}
	
	public void setDomain(String domain) {
		this.hdt_tp_domain = domain;
	}
	public void setSend(Long send) {
		this.hdt_send = send;
	}
	public void setRecv(Long recv) {
		this.hdt_recv = recv;
	}
	public void setMsec(Long msec) {
		this.hdt_msec = msec;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getDomain() {
		return this.hdt_tp_domain;
	}
	public Long getSend() {
		return this.hdt_send;
	}
	public Long getRecv() {
		return this.hdt_recv;
	}
	public Long getMsec() {
		return this.hdt_msec;
	}
	public Date getDate() {
		return this.date;
	}
	public String toString() {
		return "domain:" + hdt_tp_domain + "\tsend:" + hdt_send
				+ "\trecv:" + hdt_recv + "\tmsec:" + hdt_msec
				+ "\tts:" + date.getTime();
	}
}
