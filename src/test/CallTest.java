package test;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.DateFormatter;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.acmetelecom.Call;
import com.acmetelecom.CallEnd;
import com.acmetelecom.CallStart;

public class CallTest {
	
	private CallStart start; 
	private CallEnd end;		
	private Call call;
	private static int duration = 1;

	@Before
	public void init() throws Exception {
		this.start = new CallStart("Caller", "Callee"); 
		Thread.sleep(this.duration * 1000);
		this.end = new CallEnd("Caller", "Callee");		
		this.call = new Call(start, end);
	}

	@Test
	public void testCallee() {
		assertEquals(this.call.callee(), "Callee");
	}
	
	@Test
	public void testDuration(){
		assertEquals(this.call.durationSeconds(), this.duration);
	}
	
	@Test
	public void testDate(){
		assertEquals(this.call.date(), SimpleDateFormat.getInstance().format(new Date(start.time())));
	}
	
	@Test
	public void testStartTime(){
		assertEquals(this.call.date(), SimpleDateFormat.getInstance().format(new Date(start.time())));
	}
	
}
