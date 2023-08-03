package com.example.nowcoder.dao;

import com.example.nowcoder.entity.PersonInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PersonInfoMapper {
    // 新增个人信息
    boolean insertPerson(PersonInfo person);

    // 删除个人信息（逻辑删除）
    int softDeletePerson(PersonInfo person);

    // 修改个人信息
    int updatePerson(PersonInfo person);

    // 根据姓名、性别、年龄、部门（单个）查询个人信息
    List<PersonInfo> selectPersonsByConditions(String name, String gender, Integer age, String department);

    // 根据姓名、性别、年龄、部门（单个）查询个人信（分页）
    List<PersonInfo> selectPersonsByConditionsLimited(String name, String gender, Integer age, String department,
                                                      Integer offSet, Integer pagesize);

}

