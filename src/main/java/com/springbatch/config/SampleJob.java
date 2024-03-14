package com.springbatch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.listener.SampleJobListener;
import com.springbatch.processor.FirstItemProcessor;
import com.springbatch.reader.FirstItemReader;
import com.springbatch.service.FirstTasklet;
import com.springbatch.service.SecondTasklet;
import com.springbatch.writer.FirstItemWriter;

@Configuration
public class SampleJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private FirstTasklet firstTasklet;
	
	@Autowired
	private SecondTasklet secondTasklet;
	
	@Autowired
	private SampleJobListener sampleJobListener;
	
	@Autowired
	private FirstItemReader firstItemReader;
	
	@Autowired
	private FirstItemWriter firstItemWriter;
	
	@Autowired
	private FirstItemProcessor firstItemProcessor;
	
	private static final Logger logger = LoggerFactory.getLogger(SampleJob.class);

	@Bean
	public Job firstJob() {
		logger.info("firstJob method is called");
		return jobBuilderFactory
				.get("First Job")
				.start(firstStep())
				.next(secondStep())
				.listener(sampleJobListener)
				.build();

	}

	private Step firstStep() {
		return stepBuilderFactory.get("First Step").tasklet(firstTasklet).build();
	}
	private Step secondStep() {
		return stepBuilderFactory.get("Second Step").tasklet(secondTasklet).build();
	}
     @Bean
	public Job secondJob() {
			return jobBuilderFactory.get("second Job")
					.incrementer(new RunIdIncrementer())
					.start(firstChunkStep()).build();
		}
		
	private Step firstChunkStep() {
		return  stepBuilderFactory.get("First Chunk Step")
				.<Integer,Long>chunk(3)
				.reader(firstItemReader)
				.processor(firstItemProcessor)
				.writer(firstItemWriter).build();
	}
}
