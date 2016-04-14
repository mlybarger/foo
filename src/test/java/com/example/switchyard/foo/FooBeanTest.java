package com.example.switchyard.foo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.Exchange;
import org.switchyard.ExchangeHandler;
import org.switchyard.Message;
import org.switchyard.component.test.mixins.cdi.CDIMixIn;
import org.switchyard.component.test.mixins.naming.NamingMixIn;
import org.switchyard.test.InvocationFaultException;
import org.switchyard.test.Invoker;
import org.switchyard.test.ServiceOperation;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.test.SwitchYardTestKit;

import junit.framework.Assert;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = SwitchYardTestCaseConfig.SWITCHYARD_XML, mixins = {
		CDIMixIn.class, NamingMixIn.class })
public class FooBeanTest {

	private SwitchYardTestKit testKit;
	@ServiceOperation("Foo")
	private Invoker service;
	
	@Before
	public void before() {
//		testKit.registerInOutService("urn:com.example.switchyard:bar:1.0");
//		testKit.getServiceDomain().registerService(new QName("urn:com.example.switchyard:bar:1.0"), new InOutService(),
//				new EH());
	}

	@Test 
	public void testOne() {
		testKit.removeService("Bar");
		testKit.registerInOutService("Bar", new EH());
		System.out.println("start");
		service.operation("service");
		Message message = service.sendInOut("service");
		System.out.println("1");
		service.operation("sample");
		System.out.println("2"); 

		service.operation("sampleThree");
		message = service.sendInOut("service");
		System.out.println("2.5");
		
		// throws InvocationFaultException because exception is throw in service and no exception is marked on interface.
		try  {
			message = service.sendInOut("sample");
		} catch (InvocationFaultException ife)  {
			Assert.fail("should not have exception");
		}
		
		System.out.println("3");
		service.operation("sample");
		System.out.println("4"); 
		try  {
			message = service.sendInOut("sampleTwo");
		} catch (InvocationFaultException ife)  {
			Assert.fail("should not have exception");
		}
		System.out.println("5"); 
		
	}
	
	public class EH implements ExchangeHandler {  
	     @Override  
	     public void handleMessage(Exchange exchange) {
	          if (exchange.getContract().getConsumerOperation().getName().equals("sample")) {  
	               exchange.send(exchange.createMessage().setContent(true));  
	          }  else if (exchange.getContract().getConsumerOperation().getName().equals("sampleTwo")) {  
		           exchange.send(exchange.createMessage().setContent(true));  
	          }  else if (exchange.getContract().getConsumerOperation().getName().equals("sampleThree")) {  
		           exchange.send(exchange.createMessage().setContent(new Boolean(true)));  
		      }  else {
	               exchange.send(exchange.createMessage().setContent("response"));  
	          }
	     }

		@Override
		public void handleFault(Exchange exchange) {
		}  
	}
	
}
