package cn.yfyue.yyzx.model;


import java.util.Date;


public class OpOrge {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OP_ORGE.ORGE_ID
     *
     * @mbggenerated
     */
    private String orgeId;
    private String orgeAddr;
    private String orgeTel;
    private String email;
    private String linkMan;
    private String orgeType;
    private Date upTime;
    
	public Date getUpTime() {
		return upTime;
	}

	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}

	public String getOrgeType() {
		return orgeType;
	}

	public void setOrgeType(String orgeType) {
		this.orgeType = orgeType;
	}

	public String getOrgeAddr() {
		return orgeAddr;
	}

	public void setOrgeAddr(String orgeAddr) {
		this.orgeAddr = orgeAddr;
	}

	public String getOrgeTel() {
		return orgeTel;
	}

	public void setOrgeTel(String orgeTel) {
		this.orgeTel = orgeTel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	private String orgeFullName;
    private String orgeState;
    public String getOrgeState() {
		return orgeState;
	}

	public void setOrgeState(String orgeState) {
		this.orgeState = orgeState;
	}

	public String getOrgeFullName() {
		return orgeFullName;
	}

	public void setOrgeFullName(String orgeFullName) {
		this.orgeFullName = orgeFullName;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OP_ORGE.ORGE_NAME
     *
     * @mbggenerated
     */
    private String orgeName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OP_ORGE.ORGE_LEVEL
     *
     * @mbggenerated
     */
    private Integer orgeLevel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OP_ORGE.SUPERIOR_ORGE_ID
     *
     * @mbggenerated
     */
    private String superiorOrgeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OP_ORGE.USER_ID
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OP_ORGE.LOAD_TIME
     *
     * @mbggenerated
     */
    private Date loadTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OP_ORGE.ORGE_ID
     *
     * @return the value of OP_ORGE.ORGE_ID
     *
     * @mbggenerated
     */
    public String getOrgeId() {
        return orgeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OP_ORGE.ORGE_ID
     *
     * @param orgeId the value for OP_ORGE.ORGE_ID
     *
     * @mbggenerated
     */
    public void setOrgeId(String orgeId) {
        this.orgeId = orgeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OP_ORGE.ORGE_NAME
     *
     * @return the value of OP_ORGE.ORGE_NAME
     *
     * @mbggenerated
     */
    public String getOrgeName() {
        return orgeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OP_ORGE.ORGE_NAME
     *
     * @param orgeName the value for OP_ORGE.ORGE_NAME
     *
     * @mbggenerated
     */
    public void setOrgeName(String orgeName) {
        this.orgeName = orgeName == null ? null : orgeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OP_ORGE.ORGE_LEVEL
     *
     * @return the value of OP_ORGE.ORGE_LEVEL
     *
     * @mbggenerated
     */
    public Integer getOrgeLevel() {
        return orgeLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OP_ORGE.ORGE_LEVEL
     *
     * @param orgeLevel the value for OP_ORGE.ORGE_LEVEL
     *
     * @mbggenerated
     */
    public void setOrgeLevel(Integer orgeLevel) {
        this.orgeLevel = orgeLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OP_ORGE.SUPERIOR_ORGE_ID
     *
     * @return the value of OP_ORGE.SUPERIOR_ORGE_ID
     *
     * @mbggenerated
     */
    public String getSuperiorOrgeId() {
        return superiorOrgeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OP_ORGE.SUPERIOR_ORGE_ID
     *
     * @param superiorOrgeId the value for OP_ORGE.SUPERIOR_ORGE_ID
     *
     * @mbggenerated
     */
    public void setSuperiorOrgeId(String superiorOrgeId) {
        this.superiorOrgeId = superiorOrgeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OP_ORGE.USER_ID
     *
     * @return the value of OP_ORGE.USER_ID
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OP_ORGE.USER_ID
     *
     * @param userId the value for OP_ORGE.USER_ID
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OP_ORGE.LOAD_TIME
     *
     * @return the value of OP_ORGE.LOAD_TIME
     *
     * @mbggenerated
     */
    public Date getLoadTime() {
        return loadTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OP_ORGE.LOAD_TIME
     *
     * @param loadTime the value for OP_ORGE.LOAD_TIME
     *
     * @mbggenerated
     */
    public void setLoadTime(Date loadTime) {
        this.loadTime = loadTime;
    }
}