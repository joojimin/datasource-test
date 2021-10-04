package com.example.datasourcetest;

import com.example.datasourcetest.domain.MemberInfo;
import com.example.datasourcetest.domain.MemberInfoRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class ModifyingTest {

    @Autowired
    private MemberInfoRepository memberInfoRepository;

    private MemberInfo memberInfo;

    @BeforeEach
    void setUp() {
        // given
        memberInfoRepository.save(new MemberInfo(10, "주지민1", "서울"));
        memberInfo = memberInfoRepository.save(new MemberInfo(20,"주지민2", "춘천"));
        memberInfoRepository.save(new MemberInfo(30, "주지민3", "판교"));
        memberInfoRepository.save(new MemberInfo(40,"주지민4", "서울대입구"));
        memberInfoRepository.save(new MemberInfo(50,"주지민5", "강남"));
        memberInfoRepository.save(new MemberInfo(60,"주지민6", "건대입구"));
    }


    @Test
    void bulkUpdateMemberTest() {
        // when
        memberInfoRepository.updateNameByAgeGreaterThan("계란한판이상멤버", 30);

        // then
        List<MemberInfo> memberInfos = memberInfoRepository.findAll();
        memberInfos.forEach(System.out::println);
    }
}
