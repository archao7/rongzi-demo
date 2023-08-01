package com.example.nowcoder.controller;

import com.example.nowcoder.entity.PersonInfo;
import com.example.nowcoder.service.PersonalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private PersonalInfoService personalInfoService;


    @RequestMapping(path = "/insert", method = RequestMethod.GET)
    @ResponseBody
    public String insert(PersonInfo personInfo){
        if(personInfo.getName() == null)
            return "No name entered.";
        if(personInfo.getGender() == null)
            return "No gender entered";
        return String.valueOf(personalInfoService.insertPersonInfo(personInfo));

    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public int delete(PersonInfo personInfo){
        return personalInfoService.softDeletePerson(personInfo);
    }

    @RequestMapping(path = "/update", method = RequestMethod.GET)
    @ResponseBody
    public int update(PersonInfo personInfo){
        return personalInfoService.updatePersonInfo(personInfo);
    }

    @RequestMapping(path = "/select", method = RequestMethod.GET)
    @ResponseBody
    public List<PersonInfo> selectPersonsByConditions( String name, String gender, Integer age, String department){
        return personalInfoService.selectPersonsByConditions(name, gender, age, department);
    }
}
