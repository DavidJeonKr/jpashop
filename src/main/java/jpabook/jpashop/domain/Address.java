package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@Getter
// 임베디드 타입, 값 타입은 값을 변경하지 않아야 한다.
@ToString
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {}
}
