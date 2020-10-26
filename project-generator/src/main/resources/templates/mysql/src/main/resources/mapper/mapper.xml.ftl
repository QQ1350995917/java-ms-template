<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${projectPackage}.persistence.dao.${className}Dao">

  <resultMap id="${className}Map" type="${projectPackage}.persistence.entity.${className}Entity">
<#if columns?exists>
  <#list columns as column>
    <result column="${column.jdbcName}" jdbcType="${column.mybatisType?upper_case}" property="${column.javaName}"/>
  </#list>
</#if>
  </resultMap>

  <!--根据主键查询查询-->
  <select id="queryById" resultMap="${className}Map">
    SELECT
<#if columns?exists>
  <#list columns as column>
    <#if column_index == (columns?size -1)>
      `${column.jdbcName}`
    <#else>
      `${column.jdbcName}`,
    </#if>
  </#list>
</#if>
    FROM
      ${tableName}
    <#noparse>
    WHERE
      id = #{id}
    </#noparse>
  </select>

  <!--查询指定行数据-->
  <select id="countByCondition" resultType="java.lang.Long">
    SELECT COUNT(*) FROM ${tableName}
    <include refid="pwd.initializr.common.web.persistence.entity.ScopeEntity.entityQueryScope"></include>
  </select>

  <!--通过实体作为筛选条件查询-->
  <select id="queryByCondition" resultMap="${className}Map">
    SELECT
<#if columns?exists>
  <#list columns as column>
    <#if column_index == (columns?size -1)>
      `${column.jdbcName}`
    <#else>
      `${column.jdbcName}`,
    </#if>
  </#list>
</#if>
    FROM
      ${tableName}
    <include refid="pwd.initializr.common.web.persistence.entity.ScopeEntity.entityQueryScope"></include>
    <include refid="pwd.initializr.common.web.persistence.entity.SortEntity.entityQuerySort"></include>
    <#noparse>
    LIMIT
      #{offset}, #{limit}
    </#noparse>
  </select>

  <!--新增所有列-->
  <insert id="insert" keyProperty="id" useGeneratedKeys="true">
    INSERT INTO ${tableName}
      (
  <#if columns?exists>
    <#list columns as column>
      <#if column_index == (columns?size -1)>
        `${column.jdbcName}`
      <#else>
        `${column.jdbcName}`,
      </#if>
    </#list>
  </#if>
      )
    VALUES
      (
  <#if columns?exists>
    <#list columns as column>
      <#if column_index == (columns?size -1)>
        <#noparse>#{</#noparse>entity.${column.javaName}<#noparse>}</#noparse>
      <#else>
        <#noparse>#{</#noparse>entity.${column.javaName}<#noparse>}</#noparse>,
      </#if>
    </#list>
  </#if>
      )
  </insert>

  <!--新增所有列（批量新增）-->
  <insert id="insertByBatch" keyProperty="id" useGeneratedKeys="true">
    INSERT INTO ${tableName}
    (
  <#if columns?exists>
    <#list columns as column>
      <#if column_index == (columns?size -1)>
        `${column.jdbcName}`
      <#else>
        `${column.jdbcName}`,
      </#if>
    </#list>
  </#if>
    )
    VALUES
    <foreach collection="entities" item="entity" separator=",">
    (
  <#if columns?exists>
    <#list columns as column>
      <#if column_index == (columns?size -1)>
        <#noparse>#{</#noparse>entity.${column.javaName}<#noparse>}</#noparse>
      <#else>
        <#noparse>#{</#noparse>entity.${column.javaName}<#noparse>}</#noparse>,
      </#if>
    </#list>
  </#if>
    )
    </foreach>
  </insert>

  <!--新增或者替换所有列-->
  <insert id="insertOrReplace" keyProperty="id" useGeneratedKeys="true">
    REPLACE INTO ${tableName}
    (
  <#if columns?exists>
    <#list columns as column>
      <#if column_index == (columns?size -1)>
        `${column.jdbcName}`
      <#else>
        `${column.jdbcName}`,
      </#if>
    </#list>
  </#if>
    )
    VALUES
    (
  <#if columns?exists>
    <#list columns as column>
      <#if column_index == (columns?size -1)>
        <#noparse>#{</#noparse>entity.${column.javaName}<#noparse>}</#noparse>
      <#else>
        <#noparse>#{</#noparse>entity.${column.javaName}<#noparse>}</#noparse>,
      </#if>
    </#list>
  </#if>
    )
  </insert>

  <!--新增或者替换所有列（批量新增或者替换）-->
  <insert id="insertOrReplaceByBatch" keyProperty="id" useGeneratedKeys="true">
    REPLACE INTO ${tableName}
    (
  <#if columns?exists>
    <#list columns as column>
      <#if column_index == (columns?size -1)>
        `${column.jdbcName}`
      <#else>
        `${column.jdbcName}`,
      </#if>
    </#list>
  </#if>
    )
    VALUES
    <foreach collection="entities" item="entity" separator=",">
      (
  <#if columns?exists>
    <#list columns as column>
      <#if column_index == (columns?size -1)>
        <#noparse>#{</#noparse>entity.${column.javaName}<#noparse>}</#noparse>
      <#else>
        <#noparse>#{</#noparse>entity.${column.javaName}<#noparse>}</#noparse>,
      </#if>
    </#list>
  </#if>
      )
    </foreach>
  </insert>

  <!--通过主键修改数据-->
  <update id="updateById">
    UPDATE ${tableName}
    <set>
  <#if columns?exists>
    <#list columns as column>
      <if test="entity.${column.javaName} != null and entity.${column.javaName} != ''">
        `${column.jdbcName}` = <#noparse>#{</#noparse>entity.${column.javaName}<#noparse>}</#noparse>,
      </if>
    </#list>
  </#if>
    </set>
    WHERE
      id = <#noparse>#{entity.id}</#noparse>
  </update>

  <!--通过主键删除-->
  <update id="deleteById">
    UPDATE ${tableName}
    SET
      del = 1,
      update_time = now()
    WHERE
      id = <#noparse>#{id}</#noparse>
  </update>

  <!--通过主键批量删除-->
  <update id="deleteByIds">
    UPDATE ${tableName}
    SET
      del = 1,
      update_time = now()
    WHERE
      id in
    <foreach close=")" collection="ids" index="index" item="id" open="(" separator=",">
    <#noparse>
      #{id}
    </#noparse>
    </foreach>
  </update>

  <!--通过主键启禁-->
  <update id="ableById">
    UPDATE ${tableName}
    <#noparse>
    SET
      able = #{able},
      update_time = now()
    WHERE
      id = #{id}
    </#noparse>
  </update>

  <!--通过主键批量启禁-->
  <update id="ableByIds">
    UPDATE ${tableName}
    <#noparse>
    SET
      able = #{able},
      update_time = now()
    WHERE
      id in
    <foreach close=")" collection="ids" index="index" item="id" open="(" separator=",">
      #{id}
    </foreach>
    </#noparse>
  </update>

</mapper>
