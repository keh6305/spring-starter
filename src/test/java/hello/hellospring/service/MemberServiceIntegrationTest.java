package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest
{
	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberRepository memberRepository;

	@Test
	void join()
	{
		// given
		Member member = new Member();
		member.setName("hello");
		
		// when
		Long SaveId = memberService.join(member);
		
		// then
		Member findMember = memberService.findOne(SaveId).get();
		
		Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
	}
	
	@Test
	public void 중복_회원_예외()
	{
		// given
		Member member1 = new Member();
		member1.setName("spring");
		
		Member member2 = new Member();
		member2.setName("spring");
		
		// when
		memberService.join(member1);
		
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		
//		try
//		{
//			memberService.join(member2);
//			
//			fail();
//		}
//		catch (IllegalStateException e)
//		{
//			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//		}
		
		// then
	}
}