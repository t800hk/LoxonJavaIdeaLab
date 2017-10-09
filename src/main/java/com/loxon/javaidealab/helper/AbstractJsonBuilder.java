package com.loxon.javaidealab.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * helper class to build JSON array with zero or more entity having zero or more attributes with values
 * @author kissz
 */
public abstract class AbstractJsonBuilder {
	
	private boolean hasEntity;
	private boolean hasEntityAttr;
	private boolean closed;
	
	/**
	 * initializer
	 */
	protected AbstractJsonBuilder()
	{
		this.hasEntity = false;
		this.hasEntityAttr = false;
		this.closed = false;
	}
	
	/**
	 * @param charSeq append char sequence to the underlying buffer
	 */
	protected abstract void append(final String charSeq);
	
	/**
	 * @return string representation of the underlying buffer
	 */
	protected abstract String getString();
	
	/**
	 * start new entity
	 */
	public final void startNewEntity()
	{
		assertNotClosed();
		if(this.hasEntity)
		{
			append("}, ");
		} else
		{
			append("[");
		}
		append("{");
		this.hasEntity = true;
		this.hasEntityAttr = false;
	}
	
	/**
	 * add attribute with value to the current entity in the array
	 * @param name name ot attr
	 * @param value value of attr
	 */
	public final void addAttribute(final String name, final String value)
	{
		assertNotClosed();
		if(!this.hasEntity)
		{
			throw new IllegalStateException("call startNewEntity first");
		}
		if(this.hasEntityAttr)
		{
			append(",");
		}
		append("\"");
		append(name);
		append("\"");
		append(": ");
		append("\"");
		append(value);
		append("\"");
		this.hasEntityAttr = true;
	}
	
	/**
	 * add numeric attribute
	 * @param name numeric attribute name
	 * @param value number
	 */
	public final void addNumericAttribute(final String name, final Number value)
	{
		addAttribute(name, String.valueOf(value));
	}
	
	/**
	 * add date attribute
	 * @param name date attribute name
	 * @param value date to be added
	 */
	public final void addDateAttribute(final String name, final Date value)
	{
		final DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
		addAttribute(name, df.format(value));
	}
	
	/**
	 * @return final JSON string built
	 */
	public final String close()
	{
		assertNotClosed();
		if(this.hasEntity)
		{
			append("}");
		} else
		{
			append("[");
		}
		append("]");
		this.closed = true;
		return getString();
	}
	
	private void assertNotClosed()
	{
		if(this.closed)
		{
			throw new IllegalStateException("cannot modify a closed builder");
		}
	}
}
