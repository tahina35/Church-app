package com.churchofphilippi.webserver.service;

import com.churchofphilippi.webserver.exception.exceptionModel.EmailTakenException;
import com.churchofphilippi.webserver.exception.exceptionModel.MemberNotFoundException;
import com.churchofphilippi.webserver.model.Member;
import com.churchofphilippi.webserver.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService, BaseService<Member> {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final static String MEMBER_NOT_FOUND = "Member with email %s not found";
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new MemberNotFoundException(String.format(MEMBER_NOT_FOUND, email)));
    }

    public String registerMember(Member member) {
        boolean memberExists = memberRepository.findByEmail(member.getEmail()).isPresent();
        if(memberExists) {
            throw new EmailTakenException("Email already Taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);

        memberRepository.save(member);

        return "It works";
    }

    @Override
    public List<Member> getAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member save(Member entity) {
        return memberRepository.save(entity);
    }

    @Override
    public void delete(Member entity) {
        memberRepository.delete(entity);
    }
}
