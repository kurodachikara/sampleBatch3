package jp.kuroda.sampleBatch3;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Data
@Entity
@Validated
public class MemberInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@NotBlank(message="名前を入力してください")
	@NotNull
	private String username;
	@Email(message="正しいメールアドレスを入力してください")
	@NotBlank(message="メールアドレスを入力してください")
	@NotNull
	private String mailadress;
	@NotNull
	private String telNumber;
	@NotNull
	private String address;
	private Boolean flag;
	
}
