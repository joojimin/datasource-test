package com.example.datasourcetest;

import com.example.datasourcetest.domain.MemberInfo;
import com.example.datasourcetest.domain.MemberInfoRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MyService {

    private final EntityManagerFactory emf;
    private final ChildMyService childMyService;
    private final MemberInfoRepository memberInfoRepository;

    public void originalJpa() {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

//            ...

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }

        EntityTransaction transaction1 = entityManager.getTransaction();

        entityManager.close();
        emf.close();
    }

    @Transactional
    public void checkTransactional() {
        System.out.println("hi before child");

        MemberInfo findMember1 = memberInfoRepository.save(new MemberInfo("주지민1", "123123"));
        memberInfoRepository.save(new MemberInfo("주지민2", "123123"));
        memberInfoRepository.save(new MemberInfo("주지민3", "123123"));

        childMyService.test();

        List<MemberInfo> memberInfos = memberInfoRepository.findAll();
        memberInfos.forEach(System.out::println);
        System.out.println("hi after child");
    }

    @Transactional
    public void checkRollback() {
        memberInfoRepository.save(new MemberInfo("주지민2", "123123"));
        memberInfoRepository.save(new MemberInfo("주지민3", "123123"));

        childMyService.rollbackTest();

        memberInfoRepository.save(new MemberInfo("주지민2", "123123"));
        memberInfoRepository.save(new MemberInfo("주지민3", "123123"));
    }

    public void checkRequiredNew() {
        System.out.println("============================= MyService Start =============================");
        memberInfoRepository.save(new MemberInfo("주지민2", "123123"));
        memberInfoRepository.save(new MemberInfo("주지민3", "123123"));

        childMyService.requiredNewTest();

        memberInfoRepository.save(new MemberInfo("주지민2", "123123"));
        memberInfoRepository.save(new MemberInfo("주지민3", "123123"));
        System.out.println("============================= MyService End =============================");
    }
}
