package hello.hellospring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;

public class MemoryMemberRepositoryTest {
	
	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	//각 테스트가 종료될 때마다 이 기능을 실행하여, 메모리 DB에 저장된 데이터를 삭제
	@AfterEach
	public void afterEach() {
		repository.clearStore();
	}
	
	public void save() {
		
		//given
		Member member = new Member();
		member.setName("spring");
		
		//when
		repository.save(member);
		
		//then
		Member result = repository.findById(member.getId()).get();
		assertThat(result).isEqualTo(member);
		
	}

	@Test
	public void findByName() {
		
		//given
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		//when
		Member result = repository.findByName("spring1").get();
		
		//then
		assertThat(result).isEqualTo(member1);
	}
	
	@Test
	 public void findAll() {
		
	 //given
	 Member member1 = new Member();
	 member1.setName("spring1");
	 repository.save(member1);
	 
	 Member member2 = new Member();
	 member2.setName("spring2");
	 repository.save(member2);
	 
	 //when
	 List<Member> result = repository.findAll();
	 
	 //then
	 assertThat(result.size()).isEqualTo(2);
	 }
	
}
