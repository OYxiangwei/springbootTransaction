# springbootTransaction
springboot事务控制及Aop运用
1、修改 pom.xml  parent>properties>dependencies>build>
2、创建实体类 User.java对应数据库中表user-@Table@Entity@GeneratedValue@Id@Column
3、新建UserRepository.java 作为 dao 层继承CrudRepository
4、UserService.java 作为 service 层保存传递过来的 User 对象 顺便修改密码 在save方法上添加@Transactional(rollbackFor = Exception.class)发生异常数据会回滚
5、新建ServerTest.java实现ApplicationRunner
6、建立SpringbootAop.java注解@Aspect@Pointcut @Before @After @AfterThrowing@AfterReturning @Around，就会在切入点起作用、注入事务管理器、设置事务拦截器
