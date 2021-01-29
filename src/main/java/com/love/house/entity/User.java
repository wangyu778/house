package com.love.house.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

/**
 * sys_user
 * @author wy
 */
@Data
@ApiModel
public class User implements UserDetails,Serializable{

    @ApiModelProperty(value = "用户Id")
    private String userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户密码")
    private String passWord;

    @ApiModelProperty(value = "性别", example = "1")
    private int sex;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @ApiModelProperty(value = "账号创建时间")
    private Date createDate;

    @ApiModelProperty(value = "身份证号")
    private String idCardNumber;

    @ApiModelProperty(value = "个性签名")
    private String personSign;

    @ApiModelProperty(value = "是否租房：0否，1是 ", example = "1")
    private int isRental;

    @ApiModelProperty(value = "是否锁定账号： 0否，1是", example = "1")
    private int isLock;

    @ApiModelProperty(value = "账号锁定时间")
    private Date lockTime;

    @ApiModelProperty(value = "头像")
    private MultipartFile headImg;

    @ApiModelProperty(value = "角色列表")
    private List<Role> roleList;

    private List<GrantedAuthority> authorities;

    private static final long serialVersionUID = 1L;

    public int getIsRental() {
        return isRental;
    }

    public void setIsRental(int isRental) {
        this.isRental = isRental;
    }

    public MultipartFile getHeadImg() {
        return headImg;
    }

    public void setHeadImg(MultipartFile headImg) {
        this.headImg = headImg;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getPersonSign() {
        return personSign;
    }

    public void setPersonSign(String personSign) {
        this.personSign = personSign;
    }

    public int getIsLock() {
        return isLock;
    }

    public void setIsLock(int isLock) {
        this.isLock = isLock;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }
}