package jp.kuroda.sampleBatch3;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix="member")
@Data
public class MemberContext {
	private int writeSize;
}
