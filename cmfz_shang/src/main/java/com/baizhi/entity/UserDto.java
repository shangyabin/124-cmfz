package com.baizhi.entity;

/**
 * Created by 小尚 on 2018/9/5.
 */
public class UserDto {
    private String name;
    private Integer value;

    public UserDto() {
    }

    public UserDto(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
