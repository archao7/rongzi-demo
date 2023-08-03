package com.example.nowcoder.controller;//package com.example.nowcoder.controller;
//
//import com.example.nowcoder.dao.PersonInfoMapper;
//import com.example.nowcoder.entity.PersonInfo;
//import com.example.nowcoder.service.PersonalInfoService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.modules.junit4.PowerMockRunner;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.mock.web.MockHttpServletResponse;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.when;
//
//@RunWith(PowerMockRunner.class)
//public class HomeControllerTest {
//
//    @InjectMocks
//    private HomeController homeController;
//
//    private PersonInfoMapper personInfoMapper;
//
//    @Mock
//    private PersonalInfoService personalInfoService;
//
//    private MockHttpServletRequest request;
//    private MockHttpServletResponse response;
//
//    @Before
//    public void setUp() {
//        request = new MockHttpServletRequest();
//        response = new MockHttpServletResponse();
//    }
//
//    @Test
//    public void testInsert() {
//        PersonInfo personInfo = new PersonInfo();
//        personInfo.setName("John");
//        personInfo.setGender("Male");
//
//        // Mocking the service method
//        when(personalInfoService.insertPersonInfo(any(PersonInfo.class))).thenReturn(true);
//
//        // Invoke the insert method
//        String result = homeController.insert(personInfo);
//
//        // Assert the result
//        assertEquals("true", result);
//    }
//
//    @Test
//    public void testInsertNoName() {
//        PersonInfo personInfo = new PersonInfo();
//        personInfo.setGender("Male");
//
//        // Invoke the insert method
//        String result = homeController.insert(personInfo);
//
//        // Assert the result
//        assertEquals("No name entered.", result);
//    }
//
//    @Test
//    public void testInsertNoGender() {
//        PersonInfo personInfo = new PersonInfo();
//        personInfo.setName("John");
//
//        // Invoke the insert method
//        String result = homeController.insert(personInfo);
//
//        // Assert the result
//        assertEquals("No gender entered", result);
//    }
//
//    @Test
//    public void testDelete() {
//        PersonInfo personInfo = new PersonInfo();
//        personInfo.setId(1);
//
//        // Mocking the service method
//        when(personalInfoService.softDeletePerson(any(PersonInfo.class))).thenReturn(1);
//
//        // Invoke the delete method
//        int result = homeController.delete(personInfo);
//
//        // Assert the result
//        assertEquals(1, result);
//    }
//
//    @Test
//    public void testUpdate() {
//        PersonInfo personInfo = new PersonInfo();
//        personInfo.setId(1);
//
//        // Mocking the service method
//        when(personalInfoService.updatePersonInfo(any(PersonInfo.class))).thenReturn(1);
//
//        // Invoke the update method
//        int result = homeController.update(personInfo);
//
//        // Assert the result
//        assertEquals(1, result);
//    }
//
//    @Test
//    public void testSelectPersonsByConditions() {
//        String name = "John";
//        String gender = "Male";
//        Integer age = 30;
//        String department = "Engineering";
//
//        List<PersonInfo> mockPersonList = new ArrayList<>();
//        // Add some mock PersonInfo objects to the list
//
//        // Mocking the service method
//        when(personalInfoService.selectPersonsByConditions(eq(name), eq(gender), eq(age), eq(department))).thenReturn(mockPersonList);
//
//        // Invoke the select method
//        List<PersonInfo> result = homeController.selectPersonsByConditions(name, gender, age, department);
//
//        // Assert the result
//        assertEquals(mockPersonList, result);
//    }
//
//    @Test
//    public void selectPersonsByConditionsLimited() {
//        String name = "John";
//        String gender = "Male";
//        Integer age = 30;
//        String department = "Engineering";
//
//        List<PersonInfo> mockPersonList = new ArrayList<>();
//        // Add some mock PersonInfo objects to the list
//
//        // Mocking the service method
//        when(personalInfoService.selectPersonsByConditions(eq(name), eq(gender), eq(age), eq(department))).thenReturn(mockPersonList);
//
//        // Invoke the select method
//        List<PersonInfo> result = homeController.selectPersonsByConditions(name, gender, age, department);
//
//        // Assert the result
//        assertEquals(mockPersonList, result);
//    }
//
//    @Test
//    public void testSelectPersonsByConditionsLimited() {
//        // 准备测试数据
//        String name = "John";
//        String gender = "Male";
//        Integer age = 30;
//        String department = "IT";
//        Integer pageNum = 1;
//        Integer pageSize = 10;
//
//        // 构造预期的返回结果
//        List<PersonInfo> expectedList = new ArrayList<>();
//        expectedList.add(new PersonInfo(1, "John", "Male", 30, "Engineer", 1));
//        expectedList.add(new PersonInfo(2, "Jane", "Female", 25, "Designer", 2));
//
//        // 模拟personInfoMapper的方法返回预期的结果
//        when(personInfoMapper.selectPersonsByConditionsLimited(name, gender, age, department, pageNum, pageSize))
//                .thenReturn(expectedList);
//
//        // 调用被测试方法
//        List<PersonInfo> result = personalInfoService.selectPersonsByConditionsLimited(name, gender, age, department, pageNum, pageSize);
//
//        // 验证返回结果是否符合预期
//        assertEquals(expectedList, result);
//        // 验证是否调用了personInfoMapper的方法
//        verify(personInfoMapper, times(1)).selectPersonsByConditionsLimited(name, gender, age, department, pageNum, pageSize);
//    }
//}



import com.example.nowcoder.entity.PersonInfo;
import com.example.nowcoder.service.PersonalInfoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

    @Mock
    private PersonalInfoService personalInfoService;

    @InjectMocks
    private HomeController homeController;

    @Before
    public void setUp() {
        // 可以在这里初始化一些Mock的行为
    }

    @Test
    public void testInsert_NoName() {
        PersonInfo personInfo = new PersonInfo();
        // 没有姓名和性别
        String expectedResult = "No name entered.";
        String result = homeController.insert(personInfo);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testInsert_NoGender() {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setName("John");
        // 没有性别
        String expectedResult = "No gender entered";
        String result = homeController.insert(personInfo);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testInsert_Success() {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setName("John");
        personInfo.setGender("Male");
        // 成功插入
        when(personalInfoService.insertPersonInfo(any(PersonInfo.class))).thenReturn(true);
        String expectedResult = "true";
        String result = homeController.insert(personInfo);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDelete() {
        PersonInfo personInfo = new PersonInfo();
        // 模拟Service层的返回结果
        when(personalInfoService.softDeletePerson(any(PersonInfo.class))).thenReturn(1);

        int expectedResult = 1;
        int result = homeController.delete(personInfo);
        assertEquals(expectedResult, result);

        // 验证Controller是否正确调用了Service层的方法
        verify(personalInfoService, times(1)).softDeletePerson(personInfo);
    }

    @Test
    public void testUpdate() {
        PersonInfo personInfo = new PersonInfo();
        // 模拟Service层的返回结果
        when(personalInfoService.updatePersonInfo(any(PersonInfo.class))).thenReturn(1);

        int expectedResult = 1;
        int result = homeController.update(personInfo);
        assertEquals(expectedResult, result);

        // 验证Controller是否正确调用了Service层的方法
        verify(personalInfoService, times(1)).updatePersonInfo(personInfo);
    }

    @Test
    public void testSelectPersonsByConditions() {
        String name = "John";
        String gender = "Male";
        int age = 30;
        String department = "Engineering";

        // 模拟Service层的返回结果
        List<PersonInfo> mockResult = new ArrayList<>();
        mockResult.add(new PersonInfo("John", "Male", 30, "Engineer", 2));
        when(personalInfoService.selectPersonsByConditions(name, gender, age, department)).thenReturn(mockResult);

        List<PersonInfo> result = homeController.selectPersonsByConditions(name, gender, age, department);
        assertEquals(mockResult, result);

        // 验证Controller是否正确调用了Service层的方法
        verify(personalInfoService, times(1)).selectPersonsByConditions(name, gender, age, department);
    }

    @Test
    public void testSelectPersonsByConditionsLimited() {
        String name = "John";
        String gender = "Male";
        int age = 30;
        String department = "Engineering";
        int pageNum = 1;
        int pageSize = 10;

        // 模拟Service层的返回结果
        List<PersonInfo> mockResult = new ArrayList<>();
        mockResult.add(new PersonInfo("John", "Male", 30, "Engineer", 2));
        when(personalInfoService.selectPersonsByConditionsLimited(name, gender, age, department, pageNum, pageSize)).thenReturn(mockResult);

        List<PersonInfo> result = homeController.selectPersonsByConditionsLimited(name, gender, age, department, pageNum, pageSize);
        assertEquals(mockResult, result);

        // 验证Controller是否正确调用了Service层的方法
        verify(personalInfoService, times(1)).selectPersonsByConditionsLimited(name, gender, age, department, pageNum, pageSize);
    }
}
