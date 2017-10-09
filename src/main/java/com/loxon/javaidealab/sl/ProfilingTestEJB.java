package com.loxon.javaidealab.sl;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.StatelessSession;

import com.loxon.javaidealab.domain.Input;
import com.loxon.javaidealab.domain.Output;
import com.loxon.javaidealab.domain.ZipCode;
import com.loxon.javaidealab.helper.AbstractJsonBuilder;
import com.loxon.javaidealab.helper.StringBuilderJsonBuilder;

/**
 * Stateless session bean to perform various test executions for profiling analysis
 * 
 * @author kissz
 */
@Stateless(name = "ProfilingTestEJB")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProfilingTestEJB {
	
	@PersistenceContext(unitName="JavaIdeaLab")
	private EntityManager em;
	
	/**
	 * load input data and transform to JSON 
	 * @return JSON representation of the input data
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String testLoad()
	{
		final AbstractJsonBuilder builder =
			/*FIXME: #1/1* / new StringJsonBuilder() /**/
			/*FIXME: #1/2*/new StringBuilderJsonBuilder() /**/
			/*FIXME: #1/4* /new StringBuilderJsonBuilder(63356) /**/
			;			
		int cnt = 0;
		for(final Input input : getInputs())
		{
			builder.startNewEntity();
			builder.addNumericAttribute("id", input.getId());
			builder.addNumericAttribute("zipCode", input.getZipCode());
			builder.addAttribute("address", input.getAddress());
			builder.addDateAttribute("contractDate", input.getContractDate());
			builder.addNumericAttribute("buildYear", input.getBuildYear());
			cnt ++;
			/* FIXME#1/3:* /System.out.println("ProfilingTestEJB.testLoad / input#" + cnt + ": " + input);/**/
		}
		return 
			/*FIXME: 1/5* /builder.close()/**/
			/*FIXME: 1/6*/builder.close().substring(0,10) + "..."/**/
			;
	}
	
	/**
	 * delete all output data
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void deleteOutputs()
	{
		em.createQuery("delete from " + Output.class.getSimpleName()).executeUpdate();
	}
	
	/**
	 * copy input data to output using various techniques 
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void testCopy()
	{
		/*FIXME: #2/2* /testCopy1();/**/
		/*FIXME: #2/3*/testCopy2();/**/
	}
	
	/**
	 * copy input data to output using standard JPA features
	 */
	private void testCopy1()
	{
		int cnt = 0;
		for(final Input inp : getInputs())
		{
			processInput(inp);
			cnt ++;
			if(cnt%10000==0)
			{
				System.out.println("processed so far: " + cnt);
				/* FIXME: #2/1 */em.flush(); em.clear();/**/
			}
		}
		System.out.println("processed overall: " + cnt);
	}
	
	/**
	 * copy input data to output using standard JPA features
	 */
	private void testCopy2()
	{
		try
		{
			boolean loadCache = 
				/*FIXME: #2/4* /false/**/
				/*FIXME: #2/5*/true/**/
				;
			StatelessSession session = null;
			ScrollableResults results = null;
			int cnt = 0;
			try
			{
				session = ((Session)em.getDelegate()).getSessionFactory().openStatelessSession();
				org.hibernate.Query q = session.createQuery("select i from Input i order by i.id");
				q.setReadOnly(true);
				results = q.scroll(ScrollMode.FORWARD_ONLY);
				while(results.next())
				{
					if(loadCache)
					{
						loadZipCodesFast();
						loadCache = false;
					}
					processInput((Input)results.get(0));
					cnt ++;
					if(cnt % 
						/*FIXME: #2/6* /10000/**/ 
						/*FIXME: #2/7*/100000/**/ 
							== 0)
					{
						System.out.println("processed so far: " + cnt);
						em.flush(); em.clear();
					}
				}
			} finally
			{
				if(results!=null) results.close();
				if(session!=null) session.close();
			}
			System.out.println("processed overall: " + cnt);
		} finally
		{
			this.zipCode2City = null; 
		}
	}
	
	private void processInput(final Input inp)
	{
		final Output out = new Output();
		out.setAddress(inp.getAddress());
		out.setBuildYear(inp.getBuildYear());
		out.setCity(getCityFor(inp.getZipCode()));
		out.setContractDate(inp.getContractDate());
		em.persist(out);
	}
	
	/**
	 * pre-build zipCode - city cache - slow method
	 * @deprecated unused for live demo due to already presented issue
	 */
	private void loadZipCodesSlow()
	{
		this.zipCode2City = new HashMap<Long, String>(10000);
		for(final ZipCode zipCode : (Iterable<ZipCode>)em.createQuery("select z from ZipCode z").getResultList())
		{
			this.zipCode2City.put(zipCode.getZipCode(), zipCode.getCity());
		}
	}
	
	/**
	 * pre-build zipCode - city cache - fast method
	 */
	private void loadZipCodesFast()
	{
		this.zipCode2City = new HashMap<Long, String>(10000);
		StatelessSession session = null;
		ScrollableResults results = null;
		try
		{
			session = ((Session)em.getDelegate()).getSessionFactory().openStatelessSession();
			org.hibernate.Query q = session.createQuery("select z from ZipCode z");
			q.setReadOnly(true);
			results = q.scroll(ScrollMode.FORWARD_ONLY);
			while(results.next())
			{
				final ZipCode zipCode = (ZipCode)results.get(0);
				this.zipCode2City.put(zipCode.getZipCode(), zipCode.getCity());
			}
		} finally
		{
			if(results!=null) results.close();
			if(session!=null) session.close();
		}
		System.out.println("loadZipCodes finished");
	}
	private Map<Long,String> zipCode2City;
	
	private String getCityFor(final long zipCode)
	{
		if(this.zipCode2City!=null)
		{
			return this.zipCode2City.get(zipCode);
		} else
		{
			final ZipCode zipCodeEntity = this.em.find(ZipCode.class, zipCode);
			return zipCodeEntity.getCity();
		}
	}

	@SuppressWarnings("unchecked")
	private Iterable<Input> getInputs()
	{
		return (Iterable<Input>)em.createQuery("select i from Input i").getResultList();
	}
}
