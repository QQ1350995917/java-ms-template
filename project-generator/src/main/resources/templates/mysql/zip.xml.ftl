<?xml version="1.0" encoding="UTF-8"?>
<assembly xmlns="http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2
  http://maven.apache.org/xsd/assembly-1.1.2.xsd">
  <id>bin</id>
  <formats>
    <format>zip</format>
  </formats>
  <fileSets>
    <fileSet>
      <directory>target/config</directory>
      <outputDirectory>config</outputDirectory>
      <includes>
        <include>**/*.*</include>
      </includes>
    </fileSet>
    <fileSet>
      <directory>bin</directory>
      <outputDirectory>/</outputDirectory>
      <includes>
        <include>*.sh</include>
      </includes>
    </fileSet>
    <fileSet>
      <directory>target</directory>
      <outputDirectory>/</outputDirectory>
      <includes>
        <include>*.jar</include>
      </includes>
    </fileSet>
    <fileSet>
      <directory>target/lib</directory>
      <outputDirectory>/lib</outputDirectory>
      <includes>
        <include>*.jar</include>
      </includes>
    </fileSet>
  </fileSets>
</assembly>