package org.example;

import java.math.BigDecimal;
//Класс для передачи пассажиров
public class PassengerDto {
    private Long id;
    private Integer pclass; // Здесь числовое значение
    private String name;
    private String sex;
    private Double age;
    private Integer siblingsSpouses;
    private Integer parentChildren;
    private BigDecimal fare;


    public PassengerDto(Long id,Integer pclass, String name, String sex, Double age, Integer siblingsSpouses,
                        Integer parentChildren, BigDecimal fare ) {
        this.id = id;
        this.pclass = pclass;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.siblingsSpouses = siblingsSpouses;
        this.parentChildren = parentChildren;
        this.fare = fare;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public Integer getSiblingsSpouses() {
        return siblingsSpouses;
    }

    public void setSiblingsSpouses(Integer siblingsSpouses) {
        this.siblingsSpouses = siblingsSpouses;
    }

    public Integer getParentChildren() {
        return parentChildren;
    }

    public void setParentChildren(Integer parentChildren) {
        this.parentChildren = parentChildren;
    }

    public BigDecimal getFare() {
        return fare;
    }

    public void setFare(BigDecimal fare) {
        this.fare = fare;
    }

    public Integer getPclass() {
        return pclass;
    }

    public void setPclass(Integer pclass) {
        this.pclass = pclass;
    }
}
