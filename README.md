# 有感而发
从bilibili找了一个微服务框架练手,感谢老师[https://space.bilibili.com/98307693]的分享

实际上,微服务的概念已经出来很久了,从2016年开始,就陆陆续续听到很多面试会问到"微服务"相关的问题,但是这么多年来,仍然菜鸟的自己,也没有系统学习过

但是本人在大数据的道路上也摸索过一段时间,现在重新学习java企业级架构开发,对一些已有的服务架构,都大致有了一些"感觉",大致会猜测,对应的中间件的实现逻辑.因此对与我自己来说,感觉还是很轻松的.

一方面是信息,另一方面是恒心,希望自己可以完整做完整个"学习"项目

## 不使用yml的原因
properties文件比yml文件要好,yml在一些场景下,会存在解析异常的情况,如果你坚持要用,可以给yml的值加上双引号


## 安装mariadb
很多中间件依赖数据库,因此不得不装,这里选择almaLinux9自带的mariadb(和mysql的情愫,自行搜索故事)
```shell
# 是不是很方便
yum install mariadb -y
yum install mariadb-server -y
```

### 设置编码
`vim /etc/my.cnf.d/client.cnf`
```shell
[client]
default-character-set=utf8
```
`vim /etc/my.cnf.d/mysql-clients.cnf`
```shell
[mysql]
default-character-set = utf8
```
`vim /etc/my.cnf.d/server.cnf`
```shell
[mysqld]
init_connect='SET NAMES utf8'
character-set-server=utf8
collation-server=utf8_general_ci
sql-mode = TRADITIONAL
# 自动将表名转成小写
lower_case_table_names=1
```
### 启动
`systemctl start mariadb`
### 查看服务状态
`systemctl status mariadb`
### 配置服务自动启动
`systemctl enable mariadb`
### 设置用户名密码
`mysql_secure_installation`

#### 查看编码
```shell
MariaDB [(none)]> show variables like '%char%';
+--------------------------+------------------------------+
| Variable_name            | Value                        |
+--------------------------+------------------------------+
| character_set_client     | utf8                         |
| character_set_connection | utf8                         |
| character_set_database   | utf8                         |
| character_set_filesystem | binary                       |
| character_set_results    | utf8                         |
| character_set_server     | utf8                         |
| character_set_system     | utf8                         |
| character_sets_dir       | /usr/share/mariadb/charsets/ |
+--------------------------+------------------------------+
```

## 安装nacos
jdk版本:1.8(因为后期安装rocketmq也使用jdk1.8,因此先以学习使用为主,不要被配置绊住)

其实配置的背后,包含了很多技术点,值得深入研究

安装路径:`/opt/nacos/`
### 修改数据库连接
`vim /opt/nacos/conf/application.properties`
```properties
# 使用的是mariadb,这里的驱动都是mysql
db.url.0=jdbc:mysql://127.0.0.1:3306/nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
db.user.0=root
db.password.0=root
```
### 启动
```shell
./startup.sh -m standalone

# 注意端口
[root@hdp1 conf]# jps
34560 Jps
30991 nacos-server.jar
[root@hdp1 conf]# netstat -anlp|grep 30991
tcp6       0      0 :::7848                 :::*                    LISTEN      30991/java
tcp6       0      0 :::8848                 :::*                    LISTEN      30991/java
tcp6       0      0 :::9849                 :::*                    LISTEN      30991/java
tcp6       0      0 :::9848                 :::*                    LISTEN      30991/java
tcp6       0      0 192.168.64.81:7848      192.168.64.81:55496     ESTABLISHED 30991/java
tcp6       0      0 192.168.64.81:55496     192.168.64.81:7848      ESTABLISHED 30991/java
udp6       0      0 :::59465                :::*                                30991/java
unix  3      [ ]         STREAM     CONNECTED     71051    30991/java
unix  2      [ ]         STREAM     CONNECTED     69420    30991/java
unix  2      [ ]         STREAM     CONNECTED     69386    30991/java
unix  3      [ ]         STREAM     CONNECTED     71052    30991/java
```

