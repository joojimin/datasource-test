package com.example.datasourcetest.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberInfoRepository extends JpaRepository<MemberInfo, Long> {

    @Query("UPDATE MemberInfo m "
        + "SET m.name = :name "
        + "WHERE m.age >= :age")
    @Modifying(clearAutomatically = true)
    void updateNameByAgeGreaterThan(@Param("name") String name, @Param("age") int age);

    @Query("SELECT m FROM MemberInfo m")
    List<MemberInfo> selectAll();
}
