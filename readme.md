# demo说明文档

下面是实现个人信息增加、删除、修改、查询功能的SQL命令以及对应的MyBatis Mapper接口和XML配置文件。

首先，我们创建两个表：`person` 和 `department`。

1. 表 `person` 包含字段：`id`, `name`, `gender`, `age`, `occupation`。
2. 表 `department` 包含字段：`id`, `person_id`, `department_name`。

其中，`person` 表和 `department` 表通过 `person_id` 字段建立一对多的关系。

**1. SQL命令：**

```sql
-- 创建 person 表
CREATE TABLE person (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    age INT,
    occupation VARCHAR(100)
);

-- 创建 department 表
CREATE TABLE department (
    id INT PRIMARY KEY AUTO_INCREMENT,
    person_id INT,
    department_name VARCHAR(100) NOT NULL,
    CONSTRAINT fk_person_id FOREIGN KEY (person_id) REFERENCES person(id)
);
```

**2. Mapper接口和XML配置文件：**

假设Mapper接口名为`PersonMapper`。

```java
public interface PersonMapper {
    // 添加个人信息
    int insertPerson(Person person);

    // 删除个人信息（逻辑删除）
    int deletePersonById(int id);

    // 修改个人信息
    int updatePerson(Person person);

    // 根据姓名、性别、年龄、部门（单个）查询个人信息
    List<Person> getPersonsByNameGenderAgeDepartment(@Param("name") String name,
                                                     @Param("gender") String gender,
                                                     @Param("age") Integer age,
                                                     @Param("department") String department);
}
```

`Person` 类为Java对象，包含个人信息的字段。

```java
public class Person {
    private int id;
    private String name;
    private String gender;
    private Integer age;
    private String occupation;
    // Getters and setters
}
```

**3. XML配置文件 `PersonMapper.xml`：**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.example.mapper.PersonMapper">
    <!-- 添加个人信息 -->
    <insert id="insertPerson" parameterType="com.example.model.Person" useGeneratedKeys="true" keyProperty="id">
        INSERT INTO person (name, gender, age, occupation)
        VALUES (#{name}, #{gender}, #{age}, #{occupation})
    </insert>

    <!-- 删除个人信息（逻辑删除） -->
    <update id="deletePersonById">
        UPDATE person
        SET is_deleted = 1
        WHERE id = #{id}
    </update>

    <!-- 修改个人信息 -->
    <update id="updatePerson">
        UPDATE person
        SET
            <if test="name != null">name = #{name},</if>
            <if test="gender != null">gender = #{gender},</if>
            <if test="age != null">age = #{age},</if>
            <if test="occupation != null">occupation = #{occupation},</if>
            <if test="departments != null">
                id = (
                    SELECT p.id
                    FROM person p
                    LEFT JOIN department d ON p.id = d.person_id
                    WHERE p.name = #{name} AND p.gender = #{gender}
                    LIMIT 1
                ),
            </if>
        WHERE id = #{id}
    </update>

    <!-- 根据姓名、性别、年龄、部门（单个）查询个人信息 -->
    <select id="getPersonsByNameGenderAgeDepartment" resultType="com.example.model.Person">
        SELECT p.id, p.name, p.gender, p.age, p.occupation, d.department_name
        FROM person p
        LEFT JOIN department d ON p.id = d.person_id
        WHERE p.name = #{name}
        <if test="gender != null">AND p.gender = #{gender}</if>
        <if test="age != null">AND p.age = #{age}</if>
        <if test="department != null">AND d.department_name = #{department}</if>
    </select>
</mapper>
```

请注意，为了实现逻辑删除，我们在`person`表中添加了一个`is_deleted`字段，用于标记是否已删除。默认值为0（未删除），当执行删除操作时，将该字段的值设为1。

以上就是实现个人信息的增加、删除、修改、查询功能的SQL命令以及对应的MyBatis Mapper接口和XML配置文件的示例。在实际应用中，你需要根据具体的数据库和业务需求进行适当的调整和扩展。



为了满足要求，需要在插入语句中增加唯一性约束，并在插入时进行重复性检查。唯一性约束可以通过在数据库表中的列上添加`唯一索引`来实现。

下面是对于`person`表的修改，包括添加唯一索引以及在插入时检查重复性。

**1. 修改 `person` 表：**

```sql
sqlCopy code
-- 添加唯一索引
CREATE UNIQUE INDEX idx_person_name_gender ON person (name, gender);
```

**2. SQL命令：**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.example.mapper.PersonMapper">
    <!-- 添加个人信息 -->
    <insert id="insertPerson" parameterType="com.example.model.Person">
        INSERT INTO person (name, gender, age, occupation)
        VALUES (#{name}, #{gender}, #{age}, #{occupation})
    </insert>
</mapper>
```

**3. Mapper接口和Person类：**

```java
public interface PersonMapper {
    // 添加个人信息
    int insertPerson(Person person);
}
javaCopy code
public class Person {
    private int id;
    private String name;
    private String gender;
    private Integer age;
    private String occupation;
    // Getters and setters
}
```

在上述代码中，我们在`name`和`gender`字段上添加了唯一索引 `idx_person_name_gender`，这将确保在`person`表中不会存在相同姓名和性别的记录。当试图插入重复的姓名和性别时，数据库会抛出唯一性约束违反的异常，从而导致插入操作失败。

请注意，这里的实现是基于数据库的唯一索引，这样可以在数据库层面确保唯一性。同时，还需要在应用层面对插入操作的结果进行适当的处理，以便返回新增成功或失败的信息给用户。



当进行查询时，我们可以在Mapper接口中对输入参数进行非空校验，并在XML配置文件中使用MyBatis的动态SQL来处理条件。下面是对查询接口进行非空校验的示例代码：

**1. XML配置文件 `PersonMapper.xml`：**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.example.mapper.PersonMapper">
    <!-- 添加个人信息 -->
    <insert id="insertPerson" parameterType="com.example.model.Person">
        INSERT INTO person (name, gender, age, occupation)
        VALUES (#{name}, #{gender}, #{age}, #{occupation})
    </insert>

    <!-- ... 其他 SQL 配置 ... -->

    <!-- 根据姓名、性别、年龄、部门（单个）查询个人信息 -->
    <select id="getPersonsByNameGenderAgeDepartment" resultType="com.example.model.Person">
        SELECT p.id, p.name, p.gender, p.age, p.occupation, d.department_name
        FROM person p
        LEFT JOIN department d ON p.id = d.person_id
        WHERE 1=1
        <if test="name != null and name != ''">AND p.name = #{name}</if>
        <if test="gender != null and gender != ''">AND p.gender = #{gender}</if>
        <if test="age != null">AND p.age = #{age}</if>
        <if test="department != null and department != ''">AND d.department_name = #{department}</if>
    </select>
</mapper>
```

在上述代码中，我们在查询接口中对输入参数 `name`、`gender`、`age` 和 `department` 进行了非空校验。在XML配置文件中，我们使用MyBatis的动态SQL来根据条件动态拼接SQL查询语句。

请注意，这里的非空校验是基于字符串的非空判断，如果需要对数字等类型进行非空校验，也可以根据具体情况进行相应的处理。

这样，在调用查询接口时，如果输入的参数中有任何一个为null或空字符串，MyBatis会根据条件动态生成SQL语句，只查询满足非空条件的个人信息。这样可以确保查询的结果符合预期，并避免不必要的查询错误。



## 接口的单元测试

为了编写单元测试，你需要一个单元测试框架（例如JUnit）和一个Mock框架（例如Mockito）来模拟数据库的行为。在这个示例中，我将使用JUnit和Mockito来演示单元测试。



下面是对HomeController的单元测试代码，并添加了注释说明：

```java
javaCopy code
import com.example.nowcoder.entity.PersonInfo;
import com.example.nowcoder.service.PersonalInfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HomeControllerTest {

    // 使用Mockito创建 PersonalInfoService 的模拟对象
    @Mock
    private PersonalInfoService personalInfoService;

    // 要测试的 HomeController 对象
    @InjectMocks
    private HomeController homeController;

    // 初始化测试环境
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInsertWithNameAndGender() {
        // 模拟输入参数
        PersonInfo personInfo = new PersonInfo();
        personInfo.setName("John");
        personInfo.setGender("Male");

        // 当调用 personalInfoService.insertPersonInfo() 时返回 true
        when(personalInfoService.insertPersonInfo(personInfo)).thenReturn(true);

        // 调用被测试的方法
        String result = homeController.insert(personInfo);

        // 验证结果是否符合预期
        assertEquals("true", result);
    }

    @Test
    public void testInsertWithNoName() {
        // 模拟输入参数
        PersonInfo personInfo = new PersonInfo();
        personInfo.setGender("Male");

        // 调用被测试的方法
        String result = homeController.insert(personInfo);

        // 验证结果是否符合预期
        assertEquals("No name entered.", result);
    }

    @Test
    public void testInsertWithNoGender() {
        // 模拟输入参数
        PersonInfo personInfo = new PersonInfo();
        personInfo.setName("John");

        // 调用被测试的方法
        String result = homeController.insert(personInfo);

        // 验证结果是否符合预期
        assertEquals("No gender entered", result);
    }

    // 其他方法的测试类似，可以模拟 personalInfoService 的返回值，验证结果是否符合预期
}
```

在这个示例中，使用了Mockito来模拟PersonalInfoService的行为。在每个测试方法中，通过`when()`和`thenReturn()`方法来设置PersonalInfoService的模拟对象的行为，然后调用HomeController的方法进行测试，并使用断言来验证结果是否正确。

注意，这里的单元测试主要测试HomeController的逻辑，不涉及PersonalInfoService的实际连接和数据库操作。通过Mockito的模拟对象，我们可以更加专注于HomeController的行为和逻辑的测试，而无需真正依赖外部服务。在实际项目中，可以根据具体需求编写更全面的测试用例，以覆盖更多的场景和逻辑分支。