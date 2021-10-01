package com.example.datasourcetest;

import com.example.datasourcetest.domain.MemberInfo;
import com.example.datasourcetest.domain.MemberInfoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChildMyService {

    private final MemberInfoRepository memberInfoRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
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
}
