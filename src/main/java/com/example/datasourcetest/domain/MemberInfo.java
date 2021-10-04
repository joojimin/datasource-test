package com.example.datasourcetest.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Setter
@Getter
@ToString(of = {"id", "age", "name", "address"})
public class MemberInfo {

    @Id
    @GeneratedValue
    private Long id;

    private int age;
    private String name;
    private String address;

    public MemberInfo(String name, String address) {
        this(0, name, address);
    }

    public MemberInfo(int age, String name, String address) {
        this.age = age;
        this.name = name;
        this.address = address;
    }
}
