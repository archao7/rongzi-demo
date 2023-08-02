package com.example.nowcoder.controller;

import com.example.nowcoder.entity.PersonInfo;
import com.example.nowcoder.service.PersonalInfoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

class HomeControllerTest {

    @Mock
    private PersonalInfoService personalInfoService;

    @InjectMocks
    private HomeController homeController;

    public HomeControllerTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInsert() {
        // 创建测试用例
        PersonInfo personInfo = new PersonInfo();
        personInfo.setName("John");
        personInfo.setGender("Male");

        // 设置模拟对象的行为
        when(personalInfoService.insertPersonInfo(personInfo)).thenReturn(true);

        // 调用被测试的方法
        String result = homeController.insert(personInfo);

        // 验证结果是否符合预期
        assertEquals("true", result);
    }

    @Test
    void testDelete() {
        // 创建测试用例
        PersonInfo personInfo = new PersonInfo();
        personInfo.setId(1); // 假设要删除id为1的人员信息

        // 设置模拟对象的行为
        when(personalInfoService.softDeletePerson(personInfo)).thenReturn(1); // 假设删除成功，返回受影响的行数

        // 调用被测试的方法
        int result = homeController.delete(personInfo);

        // 验证结果是否符合预期
        assertEquals(1, result);
    }

    @Test
    void testUpdate() {
        // 创建测试用例
        PersonInfo personInfo = new PersonInfo();
        personInfo.setId(1); // 假设要更新id为1的人员信息
        personInfo.setAge(30); // 假设要修改年龄为30

        // 设置模拟对象的行为
        when(personalInfoService.updatePersonInfo(personInfo)).thenReturn(1); // 假设更新成功，返回受影响的行数

        // 调用被测试的方法
        int result = homeController.update(personInfo);

        // 验证结果是否符合预期
        assertEquals(1, result);
    }

    @Test
    void testSelectPersonsByConditions() {
        // 创建测试用例
        String name = "John";
        String gender = "Male";
        Integer age = 30;
        String department = "IT";

        // 创建模拟的查询结果
        List<PersonInfo> mockResult = new ArrayList<>();
        // 假设数据库查询结果
        mockResult.add(new PersonInfo(1, "John", "Male", 30, "Engineer", 1));
        mockResult.add(new PersonInfo(2, "John", "Male", 25, "Developer", 2));

        // 设置模拟对象的行为
        when(personalInfoService.selectPersonsByConditions(name, gender, age, department)).thenReturn(mockResult);

        // 调用被测试的方法
        List<PersonInfo> result = homeController.selectPersonsByConditions(name, gender, age, department);

        // 验证结果是否符合预期
        assertEquals(mockResult, result);
    }
}


//import com.example.nowcoder.entity.PersonInfo;
//import com.example.nowcoder.service.PersonalInfoService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class HomeControllerTest {
//
//    // 使用Mockito创建 PersonalInfoService 的模拟对象
//    @Mock
//    private PersonalInfoService personalInfoService;
//
//    // 要测试的 HomeController 对象
//    @InjectMocks
//    private HomeController homeController;
//
//    // 初始化测试环境
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testInsertWithNameAndGender() {
//        // 模拟输入参数
//        PersonInfo personInfo = new PersonInfo();
//        personInfo.setName("John");
//        personInfo.setGender("Male");
//
//        // 当调用 personalInfoService.insertPersonInfo() 时返回 true
//        when(personalInfoService.insertPersonInfo(personInfo)).thenReturn(true);
//
//        // 调用被测试的方法
//        String result = homeController.insert(personInfo);
//
//        // 验证结果是否符合预期
//        assertEquals("true", result);
//    }
//
//    @Test
//    public void testInsertWithNoName() {
//        // 模拟输入参数
//        PersonInfo personInfo = new PersonInfo();
//        personInfo.setGender("Male");
//
//        // 调用被测试的方法
//        String result = homeController.insert(personInfo);
//
//        // 验证结果是否符合预期
//        assertEquals("No name entered.", result);
//    }
//
//    @Test
//    public void testInsertWithNoGender() {
//        // 模拟输入参数
//        PersonInfo personInfo = new PersonInfo();
//        personInfo.setName("John");
//
//        // 调用被测试的方法
//        String result = homeController.insert(personInfo);
//
//        // 验证结果是否符合预期
//        assertEquals("No gender entered", result);
//    }
//
//    // 其他方法的测试类似，可以模拟 personalInfoService 的返回值，验证结果是否符合预期
//}
