package hello.hellospring.Repository;

import java.util.List;
import java.util.Optional;

import hello.hellospring.Domain.Member;

public interface MemberRepository
{
	Member save(Member member);
	
	Optional<Member> findById(Long id);
	Optional<Member> findByName(String name);
	
	List<Member> findAll();
}