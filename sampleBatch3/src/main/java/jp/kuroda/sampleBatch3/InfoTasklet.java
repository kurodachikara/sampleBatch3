package jp.kuroda.sampleBatch3;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component()
public class InfoTasklet implements Tasklet{
	private Logger logger=LoggerFactory.getLogger(InfoTasklet.class);
	
	@Autowired
	private MemberInfoRepository memberRepository;
	
	@Autowired
	private ItemStreamWriter<MemberInfo> writer;
	
	@Autowired
	private MemberContext memberContext;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		logger.debug("InfoTasklet 開始");
		writer.open(chunkContext.getStepContext().getStepExecution().getExecutionContext());
		List<MemberInfo> result=memberRepository.selectMemberInfoFlagTrue();
		List<MemberInfo> data=new ArrayList<>();
		for(MemberInfo dto:result) {
			data.add(dto);
			if(data.size()>=memberContext.getWriteSize()) {
				writer.write(data);
				data.clear();
			}
		}
		if(data.size()>0)writer.write(data);
		writer.close();
		logger.debug("InfoTasklet 修了");
		return RepeatStatus.FINISHED;
	}
	
	
}
