package com.example.datasourcetest;

import com.example.datasourcetest.domain.MemberInfo;
import com.example.datasourcetest.domain.MemberInfoRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChildMyService {

    private final MemberInfoRepository memberInfoRepository;

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Transactional
    public void test() {
        System.out.println("hi child service start!!");

        memberInfoRepository.save(new MemberInfo("룰루랄라1", "룰루비데"));
        memberInfoRepository.save(new MemberInfo("룰루랄라2", "룰루비데"));
        memberInfoRepository.save(new MemberInfo("룰루랄라3", "룰루비데"));
        memberInfoRepository.save(new MemberInfo("룰루랄라4", "룰루비데"));

        List<MemberInfo> memberInfos = memberInfoRepository.findAll();
        memberInfos.forEach(System.out::println);

        System.out.println("hi child service end!!");

    }

    @Transactional
    public void rollbackTest() {
        memberInfoRepository.save(new MemberInfo("룰루랄라1", "룰루비데"));
        memberInfoRepository.save(new MemberInfo("룰루랄라2", "룰루비데"));
        memberInfoRepository.save(new MemberInfo("룰루랄라3", "룰루비데"));
        memberInfoRepository.save(new MemberInfo("룰루랄라4", "룰루비데"));

        throw new RuntimeException("자식 트랜잭션 Exception");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiredNewTest() {
        System.out.println("============================= ChildMySerive Start =============================");
        memberInfoRepository.save(new MemberInfo("룰루랄라1", "룰루비데"));
        memberInfoRepository.save(new MemberInfo("룰루랄라2", "룰루비데"));
        memberInfoRepository.save(new MemberInfo("룰루랄라3", "룰루비데"));
        memberInfoRepository.save(new MemberInfo("룰루랄라4", "룰루비데"));

        List<MemberInfo> memberInfos = memberInfoRepository.findAll();
        memberInfos.forEach(System.out::println);
        System.out.println("============================= ChildMySerive End =============================");
    }
}
