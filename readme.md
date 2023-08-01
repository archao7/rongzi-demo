# demo说明文档

为了实现上述功能，你需要创建一个包含个人信息的数据库表。假设我们称这个表为"person_info"，包含字段：id、姓名(name)、性别(gender)、年龄(age)、职业(occupation)、部门(department)和逻辑删除标志(is_deleted)。

首先，我们创建该表的SQL命令如下：

```sql
CREATE TABLE person_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    age INT,
    occupation VARCHAR(100),
    department VARCHAR(100),
    is_deleted BOOLEAN DEFAULT false
);
```

相应的’‘个人信息‘’实体类，entity文件夹下

```java
public class PersonInfo {
    private int id;
    private String name;
    private String gender;
    private Integer age;
    private String occupation;
    private String department;
    // Getters and Setters
    private boolean is_deleted;
}
```

相应的mapper(dao文件夹下)和xml(resource->mapper文件夹下)分别如下：

```java
@Mapper
public interface PersonInfoMapper {
    // 增加个人信息，返回1表示成功，0表示失败
    int insertPersonInfo(PersonInfo personInfo);

    // 逻辑删除个人信息，返回1表示成功，0表示失败
    int deletePersonInfoByName(String name);

    // 修改个人信息，返回1表示成功，0表示失败
    int updatePersonInfo(PersonInfo personInfo);

    // 根据姓名查询个人信息，返回PersonInfo对象或null表示失败
    PersonInfo getPersonInfoByName(String name);

    // 根据性别查询个人信息，返回PersonInfo列表或空列表表示失败
    List<PersonInfo> getPersonInfoByGender(String gender);

    // 根据年龄查询个人信息，返回PersonInfo列表或空列表表示失败
    List<PersonInfo> getPersonInfoByAge(int age);

    // 根据部门查询个人信息（单个部门），返回PersonInfo列表或空列表表示失败
    List<PersonInfo> getPersonInfoByDepartment(String department);}
```

然后service里相应的调mapper文件完成业务逻辑：

```java
@Service
public class PersonalInfoService {

    @Autowired
    private PersonInfoMapper personInfoMapper;

    public PersonInfo findPersonInfoByName(String name) {
        return personInfoMapper.getPersonInfoByName(name);
    }

    public int updatePersonInfo(PersonInfo personInfo){
        return personInfoMapper.updatePersonInfo(personInfo);
    }



}
```