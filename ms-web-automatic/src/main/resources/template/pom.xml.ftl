<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <parent>
    <artifactId>parent</artifactId>
    <groupId>pwd.initializr</groupId>
    <version>${projectVersion}</version>
  </parent>
  <modelVersion>4.0.0</modelVersion>

  <artifactId>${projectName}</artifactId>
  <packaging>jar</packaging>

  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
      <groupId>pwd.initializr</groupId>
      <artifactId>lib-common-web</artifactId>
      <version>0.0.1-SNAPSHOT</version>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-websocket</artifactId>
    </dependency>
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.48</version>
    </dependency>
    <dependency>
      <groupId>org.freemarker</groupId>
      <artifactId>freemarker</artifactId>
      <version>2.3.30</version>
    </dependency>
  </dependencies>


  <build>
    <resources>
      <!--将resources下的配置文件拷贝到target/config目录下 -->
      <resource>
        <directory>src/main/resources</directory>
        <targetPath>${project.build.directory}/config</targetPath>
      </resource>
      <!--编译的时候,类路径下也复制一份配置文件(防止开发启动的时候读取不到配置的情况) -->
      <resource>
        <directory>src/main/resources</directory>
        <targetPath>${project.build.directory}/classes</targetPath>
      </resource>
    </resources>
    <plugins>
      <plugin>
        <!-- (true)skip test    -->
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <configuration>
          <skip>true</skip>
        </configuration>
      </plugin>
      <!-- =========================== -->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
        </configuration>
      </plugin>
      <!-- 将所依赖的第三方jar包打入target下的lib目录 -->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-dependency-plugin</artifactId>
        <executions>
          <execution>
            <id>copy-dependencies</id>
            <phase>package</phase>
            <goals>
              <goal>copy-dependencies</goal>
            </goals>
            <configuration>
              <outputDirectory>${project.build.directory}/lib</outputDirectory>
            </configuration>
          </execution>
        </executions>
      </plugin>
      <!-- 解决资源文件的编码问题 -->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-resources-plugin</artifactId>
        <configuration>
          <encoding>UTF-8</encoding>
          <nonFilteredFileExtensions>
            <nonFilteredFileExtension>xls</nonFilteredFileExtension>
            <nonFilteredFileExtension>xlsx</nonFilteredFileExtension>
          </nonFilteredFileExtensions>
        </configuration>
      </plugin>
      <!-- 打jar包的main方法配置 -->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-jar-plugin</artifactId>
        <configuration>
          <archive>
            <manifest>
              <addClasspath>true</addClasspath>
              <classpathPrefix>lib/</classpathPrefix>
              <mainClass>${projectPackage}.${applicationName}Application</mainClass>
              <useUniqueVersions>false</useUniqueVersions>
            </manifest>
            <!-- 给清单文件添加键值对(配置文件外置) -->
            <manifestEntries>
              <Class-Path>config/</Class-Path>
            </manifestEntries>
          </archive>
          <!--打包的时候忽略classes 路径下的配置文件 -->
          <includes>
            <include>**/pwd/**</include>
          </includes>
          <!-- 
          <excludes> 
          <include>**/*.txt</include> 
          <exclude>**/entity/*</exclude> 
          <exclude>**/ddl/*</exclude> 
          <exclude>*.properties</exclude> 
          <exclude>*.xml</exclude> 
          <exclude>*.txt</exclude> 
          <exclude>*.xls</exclude> 
          <exclude>*.xlsx</exclude> 
          </excludes> 
          -->
        </configuration>
      </plugin>
      <!-- 打zip包 -->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-assembly-plugin</artifactId>
        <configuration>
          <descriptors>
            <descriptor>zip.xml</descriptor>
          </descriptors>
        </configuration>
        <executions>
          <execution>
            <id>make-assembly</id>
            <phase>package</phase>
            <goals>
              <goal>single</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>

</project>
