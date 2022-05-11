package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

@Service
public class MemberService
{
	private final MemberRepository memberRepository;
	
	@Autowired
	public MemberService(MemberRepository memberRepository)
	{
		this.memberRepository = memberRepository;
	}
	
	// 회원가입
	public Long join(Member member)
	{
		// 중복 회원 검사
		validateDuplicateMember(member);
		
		memberRepository.save(member);
		
		return member.getId();
	}
	
	// 같은 이름 중복 확인
	private void validateDuplicateMember(Member member)
	{
//		Optional<Member> result = memberRepository.findByName(member.getName());
//		
//		result.ifPresent(m -> {
//			throw new IllegalStateException("이미 존재하는 회원입니다.");
//		});
		
		memberRepository.findByName(member.getName())
			.ifPresent(m -> {
				throw new IllegalStateException("이미 존재하는 회원입니다.");
			});
	}
	
	// 전체 회원 조회
	public List<Member> findMembers()
	{
		return memberRepository.findAll();
	}
	
	// 단일 회원 조회
	public Optional<Member> findOne(Long memgerId)
	{
		return memberRepository.findById(memgerId);
	}
}