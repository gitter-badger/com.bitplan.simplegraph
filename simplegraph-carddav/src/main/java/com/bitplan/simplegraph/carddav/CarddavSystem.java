package com.bitplan.simplegraph.carddav;

import com.bitplan.simplegraph.core.SimpleNode;
import com.bitplan.simplegraph.core.SimpleSystem;
import com.bitplan.simplegraph.impl.SimpleSystemImpl;

public class CarddavSystem extends SimpleSystemImpl{

	/**
	 * initialize me
	*/
	public	CarddavSystem() {
		super.setName("CarddavSystem");
		super.setVersion("0.0.1");
	}
	
	@Override
	public SimpleSystem connect(String... connectionParams) throws Exception {
		// TODO Auto-generated method stub
	    return this;
	}

	@Override
	public SimpleNode moveTo(String nodeQuery, String... keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends SimpleNode> getNodeClass() {
		// TODO Auto-generated method stub
		return null;
	}

}