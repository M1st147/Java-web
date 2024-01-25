# Java-web-
开发的Java web项目
![3fa0593b8044e3039351e76d780b68a](https://github.com/Chencihai/Java-web-/assets/95452629/46feea86-2e90-4620-ac5c-339a1846a7c3)

业务逻辑层，针对数据访问层查出来的数据进行功能方面的使用

![8101ce7677fed60bb6abb163aeab44a](https://github.com/Chencihai/Java-web-/assets/95452629/a48525d4-bbe7-4860-9226-a4151a419bd5)

查询全部学生id
![badb651989b33f24787b960ae9e976e](https://github.com/Chencihai/Java-web-/assets/95452629/e3ef7280-7f57-423e-af9d-d9a9bc4ead6c)
//A.java连接mysql数据库查询所有数据
//1.导入mysql驱动jar包;
//2. 注册驱动 javase 反射机制Class.forName()
Class.forName("com.mysql.cj.jdbc.Driver");
//3. 获取数据库连接
connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mayikt?serverTimezone=UTC", "root", "root");
//4. 获取执行者对象
statement = connection.createStatement();
//5. 执行sql语句并获取返回结果
resultSet = statement.executeQuery("select  * from mayikt_student");
ArrayList<StudentEntity> studentEntities = new ArrayList<>();
//6. 对结果进行处理
while (resultSet.next()) { // 如果false结束该循环
    // 获取该行数据的第一列 id
    Long id = resultSet.getLong("id");
    // 获取该行数据的第二列 name
    String name = resultSet.getString("name");
    // 获取该行数据的第三列 age
    Integer age = resultSet.getInt("age");
    // 获取该行数据的第四列 address
    String address = resultSet.getString("address");
    // 将db中查询到数据封装成java学生对象
    StudentEntity studentEntity = new StudentEntity(id, name, age, address);
    // 将该对象存入到集合中
    studentEntities.add(studentEntity);
}
//  7. 释放jdbc资源
![image](https://github.com/Chencihai/Java-web-/assets/95452629/f223a1ae-386e-4ed1-9fd9-470e1385b9e3)

![11a2364402dc3182f5bdac334b876b7](https://github.com/Chencihai/Java-web-/assets/95452629/0a725bde-6218-4a55-9289-edd22b633216)

![554f53b05a6afa6beb2638408eaf8a8](https://github.com/Chencihai/Java-web-/assets/95452629/f6204fea-e34c-42a4-be99-320e7cef497a)

