package com.vk.crawler;

import com.vk.crawler.core.util.DateUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@ImportResource({"classpath:/jobs/cobol-crawler-batch.xml",
                 "classpath:/persistence-context.xml",
                 "classpath:/spring-context.xml"})

public class InvokeBatch {

  public static void main(String[] args) {

    SpringApplication app = new SpringApplication(InvokeBatch.class);
    app.setWebEnvironment(false);
    ConfigurableApplicationContext ctx= app.run(args);
    
    JobLauncher jobLauncher = (JobLauncher) ctx.getBean("jobLauncher");
    Job job = (Job) ctx.getBean("cobolFileAnalyserJob");

    try {

      JobParametersBuilder paramBuilder = new JobParametersBuilder();
      paramBuilder.addString("rootDirectory", "\\home\\abcd\\devspace\\IntelliJwork\\logs");
      paramBuilder.addLong("triggerId", 1L);
      paramBuilder.addLong("time", DateUtils.getCurrentDate().getTime());
      JobParameters jobParameters = paramBuilder.toJobParameters();
      JobExecution execution = jobLauncher.run(job, jobParameters);
      System.out.println("Exit Status : " + execution.getStatus());

    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("Done");

  }
}
