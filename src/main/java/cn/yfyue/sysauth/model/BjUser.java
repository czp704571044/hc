package cn.yfyue.sysauth.model;

import java.util.Date;

public class BjUser {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER.USER_ID
     *
     * @mbggenerated
     */
    private String userId;
    private String orgeName;
    private String roleName;
    private String openUserName;
    public String getOpenUserName() {
		return openUserName;
	}

	public void setOpenUserName(String openUserName) {
		this.openUserName = openUserName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getOrgeName() {
		return orgeName;
	}

	public void setOrgeName(String orgeName) {
		this.orgeName = orgeName;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER.LOGIN_NAME
     *
     * @mbggenerated
     */
    private String loginName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER.PASS_WORD
     *
     * @mbggenerated
     */
    private String passWord;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER.USER_NAME
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER.MOBILE_NO
     *
     * @mbggenerated
     */
    private String mobileNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER.USER_STATUS
     *
     * @mbggenerated
     */
    private Integer userStatus;
    private String userStatusStr;
    public String getUserStatusStr() {
		return userStatusStr;
	}

	public void setUserStatusStr(String userStatusStr) {
		this.userStatusStr = userStatusStr;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER.OPEN_TIME
     *
     * @mbggenerated
     */
    private Date openTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER.OPEN_USER_ID
     *
     * @mbggenerated
     */
    private Long openUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER.ADMIN_LEVEL
     *
     * @mbggenerated
     */
    private Integer adminLevel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER.ORGE_ID
     *
     * @mbggenerated
     */
    private Long orgeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER.ROLE_CODE
     *
     * @mbggenerated
     */
    private String roleCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER.DATA_EXT
     *
     * @mbggenerated
     */
    private String dataExt;
    private Date errTime;
    public Date getErrTime() {
		return errTime;
	}

	public void setErrTime(Date errTime) {
		this.errTime = errTime;
	}
    private Integer errNum=0;
    public Integer getErrNum() {
		return errNum;
	}

	public void setErrNum(Integer errNum) {
		this.errNum = errNum;
	}
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER.DATA_EXT2
     *
     * @mbggenerated
     */
    private String dataExt2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER.UP_TIME
     *
     * @mbggenerated
     */
    private Date upTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER.USER_ID
     *
     * @return the value of BJ_USER.USER_ID
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER.USER_ID
     *
     * @param userId the value for BJ_USER.USER_ID
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER.LOGIN_NAME
     *
     * @return the value of BJ_USER.LOGIN_NAME
     *
     * @mbggenerated
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER.LOGIN_NAME
     *
     * @param loginName the value for BJ_USER.LOGIN_NAME
     *
     * @mbggenerated
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER.PASS_WORD
     *
     * @return the value of BJ_USER.PASS_WORD
     *
     * @mbggenerated
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER.PASS_WORD
     *
     * @param passWord the value for BJ_USER.PASS_WORD
     *
     * @mbggenerated
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER.USER_NAME
     *
     * @return the value of BJ_USER.USER_NAME
     *
     * @mbggenerated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER.USER_NAME
     *
     * @param userName the value for BJ_USER.USER_NAME
     *
     * @mbggenerated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER.MOBILE_NO
     *
     * @return the value of BJ_USER.MOBILE_NO
     *
     * @mbggenerated
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER.MOBILE_NO
     *
     * @param mobileNo the value for BJ_USER.MOBILE_NO
     *
     * @mbggenerated
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo == null ? null : mobileNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER.USER_STATUS
     *
     * @return the value of BJ_USER.USER_STATUS
     *
     * @mbggenerated
     */
    public Integer getUserStatus() {
        return userStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER.USER_STATUS
     *
     * @param userStatus the value for BJ_USER.USER_STATUS
     *
     * @mbggenerated
     */
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER.OPEN_TIME
     *
     * @return the value of BJ_USER.OPEN_TIME
     *
     * @mbggenerated
     */
    public Date getOpenTime() {
        return openTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER.OPEN_TIME
     *
     * @param openTime the value for BJ_USER.OPEN_TIME
     *
     * @mbggenerated
     */
    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER.OPEN_USER_ID
     *
     * @return the value of BJ_USER.OPEN_USER_ID
     *
     * @mbggenerated
     */
    public Long getOpenUserId() {
        return openUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER.OPEN_USER_ID
     *
     * @param openUserId the value for BJ_USER.OPEN_USER_ID
     *
     * @mbggenerated
     */
    public void setOpenUserId(Long openUserId) {
        this.openUserId = openUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER.ADMIN_LEVEL
     *
     * @return the value of BJ_USER.ADMIN_LEVEL
     *
     * @mbggenerated
     */
    public Integer getAdminLevel() {
        return adminLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER.ADMIN_LEVEL
     *
     * @param adminLevel the value for BJ_USER.ADMIN_LEVEL
     *
     * @mbggenerated
     */
    public void setAdminLevel(Integer adminLevel) {
        this.adminLevel = adminLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER.ORGE_ID
     *
     * @return the value of BJ_USER.ORGE_ID
     *
     * @mbggenerated
     */
    public Long getOrgeId() {
        return orgeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER.ORGE_ID
     *
     * @param orgeId the value for BJ_USER.ORGE_ID
     *
     * @mbggenerated
     */
    public void setOrgeId(Long orgeId) {
        this.orgeId = orgeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER.ROLE_CODE
     *
     * @return the value of BJ_USER.ROLE_CODE
     *
     * @mbggenerated
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER.ROLE_CODE
     *
     * @param roleCode the value for BJ_USER.ROLE_CODE
     *
     * @mbggenerated
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER.DATA_EXT
     *
     * @return the value of BJ_USER.DATA_EXT
     *
     * @mbggenerated
     */
    public String getDataExt() {
        return dataExt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER.DATA_EXT
     *
     * @param dataExt the value for BJ_USER.DATA_EXT
     *
     * @mbggenerated
     */
    public void setDataExt(String dataExt) {
        this.dataExt = dataExt == null ? null : dataExt.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER.DATA_EXT2
     *
     * @return the value of BJ_USER.DATA_EXT2
     *
     * @mbggenerated
     */
    public String getDataExt2() {
        return dataExt2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER.DATA_EXT2
     *
     * @param dataExt2 the value for BJ_USER.DATA_EXT2
     *
     * @mbggenerated
     */
    public void setDataExt2(String dataExt2) {
        this.dataExt2 = dataExt2 == null ? null : dataExt2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER.UP_TIME
     *
     * @return the value of BJ_USER.UP_TIME
     *
     * @mbggenerated
     */
    public Date getUpTime() {
        return upTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER.UP_TIME
     *
     * @param upTime the value for BJ_USER.UP_TIME
     *
     * @mbggenerated
     */
    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }
}