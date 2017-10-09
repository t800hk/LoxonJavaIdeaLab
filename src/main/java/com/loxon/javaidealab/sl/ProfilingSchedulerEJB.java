package com.loxon.javaidealab.sl;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 * Singleton startup session bean to scheduler periodic test execution for profiling
 * 
 * @author kissz
 */
@Singleton(name = "ProfilingEJB")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Startup
public class ProfilingSchedulerEJB {
	
	@Resource
	private TimerService timerService;
	
	@EJB
	private ProfilingTestEJB tester;
	
	/**
	 * init scheduler
	 */
	@PostConstruct
	public void init()
	{
		System.out.println("ProfilingEJB.init - started");
		reschedule();
		System.out.println("ProfilingEJB.init - finished");
	}
	
	/**
	 * reschedule this bean for execution 
	 */
	private void reschedule()
	{
		this.timerService.createSingleActionTimer(5000L, new TimerConfig("non-persistent one-time timer callback", false));
	}
	
	/**
	 * method invoked with 5 seconds delay periodically to perform various tests for profiler analysis 
	 */
	@Timeout
	public void runPeriodic()
	{
		System.out.println("ProfilingEJB.runPeriodic - started");
		long startMs = System.currentTimeMillis(); 

		/*FIXME: PROBLEM#1 * / System.out.println("ProfilingEJB.runPeriodic - testLoad: " + this.tester.testLoad());/**/
		
		/*FIXME: PROBLEM#2,3 */
		this.tester.deleteOutputs();
		System.out.println("ProfilingEJB.runPeriodic - deleteOutputs finished in " + getExecTime(startMs) + " mins");
		startMs = System.currentTimeMillis();
		this.tester.testCopy();
		/**/
		
		reschedule();
		System.out.println("ProfilingEJB.runPeriodic - testCopy finished in " + getExecTime(startMs) + " mins");
	}
	
	private String getExecTime(final long startMs)
	{
		final BigDecimal start = new BigDecimal(startMs);
		final BigDecimal end = new BigDecimal(System.currentTimeMillis());
		final BigDecimal len = end.subtract(start).divide(new BigDecimal(1000L*60L), 2, BigDecimal.ROUND_HALF_EVEN);
		return len.toPlainString();
	}
}
