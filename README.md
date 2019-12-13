## thyme-boot


### mybatis-plus项目应用注意
1. 默认扫描此目录下面的dao @MapperScan("com.thyme.*.dao")
2. 使用mybatis-plus对数据库操作尽量使用条件构造器来实现查询（附条件构造器地址：https://mybatis.plus/guide/wrapper.html#abstractwrapper）尽量避免使用xml文件方式来做查询（缺点：后期修改包名会带来极大的影响）


### spring security 配置

