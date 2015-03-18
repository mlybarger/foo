package com.example.switchyard.foo;

import javax.xml.namespace.QName;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.component.test.mixins.cdi.CDIMixIn;
import org.switchyard.component.test.mixins.naming.NamingMixIn;
import org.switchyard.metadata.InOutService;
import org.switchyard.test.Invoker;
import org.switchyard.test.MockHandler;
import org.switchyard.test.ServiceOperation;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.test.SwitchYardTestKit;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = SwitchYardTestCaseConfig.SWITCHYARD_XML, mixins = {
		CDIMixIn.class, NamingMixIn.class })
public class FooBeanTest {

	private SwitchYardTestKit testKit;
	@ServiceOperation("Foo")
	private Invoker service;
	
	@Before
	public void before() {
		testKit.registerInOutService("urn:com.example.switchyard:bar:1.0");
		testKit.getServiceDomain().registerService(new QName("urn:com.example.switchyard:bar:1.0"), new InOutService(), 
				new MockHandler());
	}
	
	@Test 
	public void testOne() {
		service.sendInOut("message");
	}

}
