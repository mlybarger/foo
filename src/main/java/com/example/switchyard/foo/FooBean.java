package com.example.switchyard.foo;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.switchyard.component.bean.Reference;
import org.switchyard.component.bean.Service;

import com.example.switchyard.bar.Bar;
import com.example.switchyard.bar.ComplexMessage;

@Service(Foo.class)
public class FooBean implements Foo {

	Logger LOG = Logger.getLogger(FooBean.class);

	@Inject
	@Reference
	private Bar bar;
	
	@Override
	public void service(String message) {
		LOG.info("message:"+ message);
		ComplexMessage c = new ComplexMessage();
		c.setMessage(message);
		bar.complexMessage(c);
	}
	
	@Override
	public void sample(String message) {
		LOG.info("message:"+ message);
		boolean result = bar.sample(message);
		LOG.info("sample() - done:" + result);
	}

	@Override
	public void sampleTwo(String message) throws Exception {
		LOG.info("sampleTwo():"+ message);
		boolean result = bar.sampleTwo(message);
		LOG.info("sampleTwo() - done:" + result);
	}
	
	@Override
	public void sampleThree(String message) throws Exception {
		LOG.info("sampleTwo():"+ message);
		boolean result = bar.sampleThree(message);
		LOG.info("sampleTwo() - done:" + result);
	}
	
}
