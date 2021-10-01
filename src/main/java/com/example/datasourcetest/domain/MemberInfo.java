package com.example.datasourcetest.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Setter
@Getter
public class MemberInfo {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String address;

    public MemberInfo(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
