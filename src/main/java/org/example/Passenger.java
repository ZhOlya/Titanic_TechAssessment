package org.example;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity //Класс это сущность в БД
@Table(name = "passengers") //Имя таблицы в БД
public class Passenger {
    @Id //Указывает, что поле является первичным ключом
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Стратегия генерации значений первичного ключа
    private Long id;
    @Column(name = "survived")
    private Boolean survived;
    @Convert(converter = PclassConverter.class)//указывает конвертер для преобразования значений
    @Column(name = "pclass")
    private Pclass pclass;
    @Column(name ="name")
    private String name;
    @Column(name = "sex")
    private String sex;
    @Column (name = "age")
    private Double age;
    @Column(name = "siblings_spouses_aboard")
    private Integer siblingsSpouses;
    @Column(name = "parent_children_aboard")
    private Integer parentChildren;
    @Column(name = "fare")
    private BigDecimal fare;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getSurvived() {
        return survived;
    }

    public void setSurvived(Boolean survived) {
        this.survived = survived;
    }

    public Pclass getPclass() {
        return pclass;
    }

    public void setPclass(Pclass pclass) {
        this.pclass = pclass;
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


}
