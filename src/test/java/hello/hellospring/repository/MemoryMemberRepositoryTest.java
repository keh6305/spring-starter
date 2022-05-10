package hello.hellospring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
// import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

class MemoryMemberRepositoryTest
{
	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	// 테스트가 끝나고 실행되는 메소드
	@AfterEach
	public void afterEach()
	{
		repository.clearStore();
	}
	
	@Test
	public void save()
	{
		Member member = new Member();
		member.setName("spring");
		
		repository.save(member);
		
		Member result = repository.findById(member.getId()).get();
		
		System.out.println("result = " + (result == member));
		
		// 동일 여부 확인
		// Assertions.assertEquals(result, member);
		Assertions.assertThat(member).isEqualTo(result);
		assertThat(member).isEqualTo(result);
	}
	
	@Test
	public void findByName()
	{
		Member member1 = new Member();
		member1.setName("spring1");
		
		Member member2 = new Member();
		member2.setName("spring2");
		
		repository.save(member1);
		repository.save(member2);
		
		Member result = repository.findByName("spring1").get();
		
		assertThat(result).isEqualTo(member1);
	}
	
	@Test
	public void findAll()
	{
		Member member1 = new Member();
		member1.setName("spring1");
		
		Member member2 = new Member();
		member2.setName("spring2");
		
		repository.save(member1);
		repository.save(member2);
		
		List<Member> result = repository.findAll();
		
		assertThat(result.size()).isEqualTo(2);
	}
}