package test;

import javax.swing.text.DateFormatter;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.acmetelecom.Call;
import com.acmetelecom.CallEnd;
import com.acmetelecom.CallEvent;
import com.acmetelecom.CallStart;
import javax.swing.text.DateFormatter;

public class CallTest {
	
	private CallEvent start; 
	private CallEvent end;		
	private Call call;

	@Before
	public void setUp() throws Exception {
		this.start = new CallStart("Caller", "Callee"); 
		this.end = new CallEnd("Caller", "Callee");		
		this.call = new Call(start, end);
	}

	@Test
	public void testCallee() {
		assertEquals(this.call.callee(), "Callee");
	}

}
