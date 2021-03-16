package com.love.house.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * house_repair
 * @author 
 */
public class HouseRepair implements Serializable {
    /**
     * 主键Id
     */
    private Integer id;

    /**
     * 维修名称 维修位置名称
     */
    private String repairName;

    /**
     * 维修类型 1、浴室，2、卧室，3、客厅，4厨房
     */
    private Integer repairType;

    private String repairInfo;

    private Date repairDate;

    private Integer isSolve;

    private Date solveDate;

    private String userId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRepairName() {
        return repairName;
    }

    public void setRepairName(String repairName) {
        this.repairName = repairName;
    }

    public Integer getRepairType() {
        return repairType;
    }

    public void setRepairType(Integer repairType) {
        this.repairType = repairType;
    }

    public String getRepairInfo() {
        return repairInfo;
    }

    public void setRepairInfo(String repairInfo) {
        this.repairInfo = repairInfo;
    }

    public Date getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }

    public Integer getIsSolve() {
        return isSolve;
    }

    public void setIsSolve(Integer isSolve) {
        this.isSolve = isSolve;
    }

    public Date getSolveDate() {
        return solveDate;
    }

    public void setSolveDate(Date solveDate) {
        this.solveDate = solveDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}