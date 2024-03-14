package com.springbatch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

import com.springbatch.config.SampleJob;

@Component
public class SampleJobListener implements JobExecutionListener{

	private static final Logger logger = LoggerFactory.getLogger(SampleJobListener.class);

	@Override
	public void beforeJob(JobExecution jobExecution) {
		logger.info("before ==>"+jobExecution.getJobInstance().getJobName());
		logger.info("before params==>"+jobExecution.getJobParameters());
		logger.info("before params==>"+jobExecution.getExecutionContext());
		jobExecution.getExecutionContext().put("check", "check value");

		
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		logger.info("after ==>"+jobExecution.getJobInstance().getJobName());
		logger.info("after params==>"+jobExecution.getJobParameters());
		logger.info("after params==>"+jobExecution.getExecutionContext());

	}

}
