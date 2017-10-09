package com.loxon.javaidealab.helper;

/**
 * String based JSON builder impl
 * @author kissz
 */
public class StringBuilderJsonBuilder extends AbstractJsonBuilder {

	private StringBuilder state;
	
	/**
	 * initializer with default bufer size
	 */
	public StringBuilderJsonBuilder()
	{
		this(128);
	}

	/**
	 * initializer with starting buffer size
	 */
	public StringBuilderJsonBuilder(final int bufferSize)
	{
		this.state = new StringBuilder(bufferSize);
	}

	/* (non-Javadoc)
	 * @see com.loxon.javaidealab.helper.AbstractJsonBuilder#append(java.lang.String)
	 */
	@Override
	protected void append(final String charSeq) {
		this.state.append(charSeq);
	}

	/* (non-Javadoc)
	 * @see com.loxon.javaidealab.helper.AbstractJsonBuilder#getString()
	 */
	@Override
	protected String getString() {
		return this.state.toString();
	}
}
