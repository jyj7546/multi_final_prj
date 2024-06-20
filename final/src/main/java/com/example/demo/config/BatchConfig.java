// package com.example.demo.config;

// import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.scheduling.annotation.EnableScheduling;
// import org.springframework.scheduling.annotation.Scheduled;

// @Configuration
// @EnableBatchProcessing
// @EnableScheduling
// public class BatchConfig {
//     @Bean
//     public Job crawlingJob(JobBuilderFactory jobBuilderFactory, Step crawlingStep) {
//         return jobBuilderFactory.get("crawlingJob")
//                 .incrementer(new RunIdIncrementer())
//                 .start(crawlingStep)
//                 .build();
//     }

//     @Bean
//     public Step crawlingStep(StepBuilderFactory stepBuilderFactory, Tasklet crawlingTasklet) {
//         return stepBuilderFactory.get("crawlingStep")
//                 .tasklet(crawlingTasklet)
//                 .build();
//     }

//     @Bean
//     public Tasklet crawlingTasklet(CrawlingService crawlingService) {
//         return (contribution, chunkContext) -> {
//             crawlingService.crawlEmart();
//             return RepeatStatus.FINISHED;
//         };
//     }

//     @Scheduled(cron = "0 0 * * * ?") // 매시간 실행 예시
//     public void perform() throws Exception {
//         // 배치 작업 실행 코드
//     }
// }
