package jp.kuroda.sampleBatch3;

import java.io.IOException;
import java.io.Writer;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Component
@EnableBatchProcessing
public class BatchConfiguration {
	@Autowired
	private JobBuilderFactory jobFactory;
	@Autowired
	private StepBuilderFactory stepFactory;
	
	@Autowired
	public void BatchConfig(@Lazy InfoTasklet infoTasklet) {
		this.infoTasklet=infoTasklet;
	}
	private InfoTasklet infoTasklet;
	
	private Resource outputResource=new FileSystemResource("output/data.csv");
	
	@Bean
	public MemberContext createMemberContext() {
		return new MemberContext();
	}
	
	@Bean
	public FlatFileItemWriter<MemberInfo> writer(){
		FlatFileItemWriter<MemberInfo> writer=new FlatFileItemWriter<>();
		writer.setResource(outputResource);
		writer.setEncoding("UTF-8");
		writer.setLineSeparator("\r\n");
		writer.setAppendAllowed(false);
		writer.setHeaderCallback(new FlatFileHeaderCallback() {
			
			@Override
			public void writeHeader(Writer writer) throws IOException {
				writer.append("\"id\",\"username\",\"mailaddress\",\"telnumber\",\"memberaddress\",\"flag\"");
			}
		});
		writer.setLineAggregator(new CsvLineAggregator<MemberInfo>() {
			{
				setFieldExtractor(new BeanWrapperFieldExtractor<MemberInfo>() {
					{
						setNames(new String[] {"id","username","mailaddress","telNumber","memberaddress","flag"});
					}
				});
			}
		});
		return writer;
	}
	@Bean
	public Job job(@Qualifier("step1") Step step1) {
		return jobFactory.get("myjob").incrementer(new RunIdIncrementer()).start(step1).build();
	}
	@Bean
	protected Step step1() {
		return stepFactory.get("step1").tasklet(infoTasklet).build();
	}
}
