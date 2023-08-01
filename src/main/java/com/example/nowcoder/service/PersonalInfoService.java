package com.example.nowcoder.service;

import com.example.nowcoder.dao.PersonInfoMapper;
import com.example.nowcoder.entity.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalInfoService {

    @Autowired
    private PersonInfoMapper personInfoMapper;

    public boolean insertPersonInfo(PersonInfo personInfo) {
        try {
            return personInfoMapper.insertPerson(personInfo);
        }catch (Exception e){
            return false;
        }
    }


    public int softDeletePerson(PersonInfo personInfo){
        try {
            return personInfoMapper.softDeletePerson(personInfo);
        }catch (Exception e){
            return 0;
        }
    }

    public int updatePersonInfo(PersonInfo personInfo){
        try {
            return personInfoMapper.updatePerson(personInfo);
        }catch (Exception e){
            return 0;
        }
    }

    public List<PersonInfo> selectPersonsByConditions(String name, String gender, Integer age, String department){
        return personInfoMapper.selectPersonsByConditions(name, gender, age, department);
    }
}
