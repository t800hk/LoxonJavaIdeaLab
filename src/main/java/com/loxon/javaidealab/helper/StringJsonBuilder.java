package com.loxon.javaidealab.helper;

/**
 * String based JSON builder impl
 * @author kissz
 */
public class StringJsonBuilder extends AbstractJsonBuilder {

	private String state;
	
	/**
	 * initializer
	 */
	public StringJsonBuilder()
	{
		this.state = "";
	}

	/* (non-Javadoc)
	 * @see com.loxon.javaidealab.helper.AbstractJsonBuilder#append(java.lang.String)
	 */
	@Override
	protected void append(final String charSeq) {
		this.state = this.state + charSeq;
	}

	/* (non-Javadoc)
	 * @see com.loxon.javaidealab.helper.AbstractJsonBuilder#getString()
	 */
	@Override
	protected String getString() {
		return this.state;
	}
}
