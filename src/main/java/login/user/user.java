package login.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter // Getter 메서드 자동 주입
@Setter
@Table(name="userTBL")
@AllArgsConstructor 
@Entity // 디비와 연결해주겠다. 라는 의미
public class user {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String login_id;
	
	private String password;
	
	private char gender;
	
	private Date birth;

	public user() {
		
	}
	
	public user(String login_id,String password,char gender,Date birth) {
		this.login_id = login_id;
		this.password = password;
		this.gender = gender;
		this.birth = birth;
	}
}
