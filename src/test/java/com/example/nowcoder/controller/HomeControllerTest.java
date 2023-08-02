package com.example.nowcoder.controller;

import com.example.nowcoder.entity.PersonInfo;
import com.example.nowcoder.service.PersonalInfoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class HomeControllerTest {

    @InjectMocks
    private HomeController homeController;

    @Mock
    private PersonalInfoService personalInfoService;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void testInsert() {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setName("John");
        personInfo.setGender("Male");

        // Mocking the service method
        when(personalInfoService.insertPersonInfo(any(PersonInfo.class))).thenReturn(true);

        // Invoke the insert method
        String result = homeController.insert(personInfo);

        // Assert the result
        assertEquals("true", result);
    }

    @Test
    public void testInsertNoName() {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setGender("Male");

        // Invoke the insert method
        String result = homeController.insert(personInfo);

        // Assert the result
        assertEquals("No name entered.", result);
    }

    @Test
    public void testInsertNoGender() {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setName("John");

        // Invoke the insert method
        String result = homeController.insert(personInfo);

        // Assert the result
        assertEquals("No gender entered", result);
    }

    @Test
    public void testDelete() {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setId(1);

        // Mocking the service method
        when(personalInfoService.softDeletePerson(any(PersonInfo.class))).thenReturn(1);

        // Invoke the delete method
        int result = homeController.delete(personInfo);

        // Assert the result
        assertEquals(1, result);
    }

    @Test
    public void testUpdate() {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setId(1);

        // Mocking the service method
        when(personalInfoService.updatePersonInfo(any(PersonInfo.class))).thenReturn(1);

        // Invoke the update method
        int result = homeController.update(personInfo);

        // Assert the result
        assertEquals(1, result);
    }

    @Test
    public void testSelectPersonsByConditions() {
        String name = "John";
        String gender = "Male";
        Integer age = 30;
        String department = "Engineering";

        List<PersonInfo> mockPersonList = new ArrayList<>();
        // Add some mock PersonInfo objects to the list

        // Mocking the service method
        when(personalInfoService.selectPersonsByConditions(eq(name), eq(gender), eq(age), eq(department))).thenReturn(mockPersonList);

        // Invoke the select method
        List<PersonInfo> result = homeController.selectPersonsByConditions(name, gender, age, department);

        // Assert the result
        assertEquals(mockPersonList, result);
    }
}
