package pwd.initializr.account.persistence.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (AdminEntity)实体类
 *
 * @author makejava
 * @since 2020-04-25 16:18:28
 */
public class AdminEntity implements Serializable {
    private static final long serialVersionUID = -54153826190401267L;
    /**
    * 主键
    */
    private Integer id;
    /**
    * 登录名
    */
    private String loginName;
    /**
    * 登录密码
    */
    private String loginPassword;
    /**
    * 姓名
    */
    private String name;
    /**
    * 性别
    */
    private String gender;
    /**
    * 简介
    */
    private String summary;
    /**
    * 等级；0运维，1超管，2普通
    */
    private String level;
    /**
    * 状态；-1删除，0正常，1禁用
    */
    private String status;
    /**
    * 首次创建时间
    */
    private Date createTime;
    /**
    * 最近更新时间
    */
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}