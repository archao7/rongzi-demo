package com.example.nowcoder.dao;

import com.example.nowcoder.entity.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    String selectDepartmentById(int id);
}
