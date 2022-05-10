package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

//@Service
public class MemberService {
	
	//이전 방식 -- 회원 서비스가 메모리 회원 레포지토리를 직접 생성
	//private final MemberRepository memberRepository = new MemoryMemberRepository();
	
	//회원 서비스 코드를 DI 가능하게 변경
	private final MemberRepository memberRepository;
	
	//@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	/**
	 * 회원가입
	 */
	public Long join(Member member) {
		
		validateDuplicateMember(member); //중복 회원 검증
		memberRepository.save(member);
		return member.getId();
	}
	
	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
		.ifPresent(m -> { // 멤버가 있으면 발생
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		});
	}
	
	/*
	 * 전체 회원 조회
	 */
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
}
