package com.example.demo.batch;
// import java.util.List;

// import org.springframework.batch.core.Job;
// import org.springframework.batch.core.JobExecution;
// import org.springframework.batch.core.JobParametersBuilder;
// import org.springframework.batch.core.Step;
// import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
// import org.springframework.batch.core.job.builder.JobBuilder;
// import org.springframework.batch.core.launch.JobLauncher;
// import org.springframework.batch.core.launch.support.RunIdIncrementer;
// import org.springframework.batch.core.repository.JobRepository;
// import org.springframework.batch.core.step.builder.StepBuilder;
// import org.springframework.batch.core.step.tasklet.Tasklet;
// import org.springframework.batch.repeat.RepeatStatus;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.scheduling.annotation.EnableScheduling;
// import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
// import org.springframework.scheduling.support.CronTrigger;
// import org.springframework.transaction.PlatformTransactionManager;

// import com.example.demo.service.crawling.CrawlingLogicService;

// import jakarta.annotation.PostConstruct;

// @Configuration
// @EnableBatchProcessing
// @EnableScheduling
// public class BatchConfig {

//     @Autowired
//     private JobRepository jobRepository;

//     @Autowired
//     private PlatformTransactionManager transactionManager;

//     @Autowired
//     private JobLauncher jobLauncher;

//     @Autowired
//     private BatchJobMapper batchJobMapper;

// 	// 배치 돌릴 메소드 모아놓은 클래스로 변경 필요
//     @Autowired
//     private CrawlingLogicService crawlingLogicService;

//     @Autowired
//     private ThreadPoolTaskScheduler taskScheduler;

//     @Bean
//     public Job createJob(String jobName, Step step) {
//         return new JobBuilder(jobName, jobRepository)
//                 .start(step)
//                 .incrementer(new RunIdIncrementer())
//                 .build();
//     }

//     @Bean
//     public Step createStep(String stepName, Tasklet tasklet) {
//         return new StepBuilder(stepName, jobRepository)
//                 .tasklet(tasklet, transactionManager)
//                 .build();
//     }

//     @Bean
//     public Tasklet createTasklet(Runnable taskletLogic) {
//         return (contribution, chunkContext) -> {
//             taskletLogic.run();
//             return RepeatStatus.FINISHED;
//         };
//     }

//     @PostConstruct
//     public void scheduleBatchJobs() {
//         List<BatchJobVO> jobs = batchJobMapper.findAll();	// 전체 배치잡 가져오기

//         for (BatchJobVO job : jobs) {	// 반복하여 전부 등록
//             if (job.getStatus().equals("ENABLED")) {
//                 Tasklet tasklet = null;
				
// 				// DB의 배치잡 이름 확인하여 해당하는 실제 메소드를 스케줄 태스크에 등록
// 				// 배치 메소드를 한군데로 모으면 굳이 체크해서 등록할 필요 없을 듯
//                 if (job.getJobName().equals("emartCrawlingJob")) {
//                     tasklet = createTasklet(() -> crawlingLogicService.crawlingEmart());
//                 } else if (job.getJobName().equals("homeplusCrawlingJob")) {
//                     tasklet = createTasklet(() -> crawlingLogicService.crawlingHomeplus());
//                 } else if (job.getJobName().equals("lottemartCrawlingJob")) {
//                     tasklet = createTasklet(() -> crawlingLogicService.crawlingLotte());
//                 }
				
//                 Step step = createStep(job.getStepName(), tasklet);
//                 Job batchJob = createJob(job.getJobName(), step);

//                 taskScheduler.schedule(() -> {
//                     try {
//                         JobExecution execution = jobLauncher.run(
//                                 batchJob,
//                                 new JobParametersBuilder().toJobParameters()
//                         );
//                     } catch (Exception e) {
//                         e.printStackTrace();
//                     }
//                 }, new CronTrigger(job.getCronExpression()));
//             }
//         }
//     }

//     @Bean
//     public ThreadPoolTaskScheduler taskScheduler() {
//         return new ThreadPoolTaskScheduler();
//     }
// }