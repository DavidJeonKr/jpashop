package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class MemberServiceTest {


    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;


    @Test
    @Rollback(value = false)
    @DisplayName("회원가입")
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("Jeon");

        //when
        Long saveId = memberService.join(member);


        //then
        Assertions.assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    @DisplayName("중복_회원_예외")
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("Jeon");
        Member member2 = new Member();
        member2.setName("Jeon");


        //when

        memberService.join(member1);
        try {
        memberService.join(member2);
        } catch (IllegalStateException e) {
            return;
        }

        //then
        Assertions.fail("예외가 발생해야 한다");

    }

}