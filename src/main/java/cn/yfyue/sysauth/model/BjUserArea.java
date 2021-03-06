package cn.yfyue.sysauth.model;

import java.util.Date;

public class BjUserArea {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER_AREA.AREA_ID
     *
     * @mbggenerated
     */
    private Long areaId;
    private String areaLevel;
    public String getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(String areaLevel) {
		this.areaLevel = areaLevel;
	}

	public String getSupperCode() {
		return supperCode;
	}

	public void setSupperCode(String supperCode) {
		this.supperCode = supperCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	private String supperCode;
    private String areaName;
    private String fullName;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER_AREA.USER_ID
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER_AREA.AREA_CODE
     *
     * @mbggenerated
     */
    private String areaCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER_AREA.REG_MAN
     *
     * @mbggenerated
     */
    private Long regMan;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER_AREA.REG_TIME
     *
     * @mbggenerated
     */
    private Date regTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER_AREA.AREA_ID
     *
     * @return the value of BJ_USER_AREA.AREA_ID
     *
     * @mbggenerated
     */
    public Long getAreaId() {
        return areaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER_AREA.AREA_ID
     *
     * @param areaId the value for BJ_USER_AREA.AREA_ID
     *
     * @mbggenerated
     */
    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER_AREA.USER_ID
     *
     * @return the value of BJ_USER_AREA.USER_ID
     *
     * @mbggenerated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER_AREA.USER_ID
     *
     * @param userId the value for BJ_USER_AREA.USER_ID
     *
     * @mbggenerated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER_AREA.AREA_CODE
     *
     * @return the value of BJ_USER_AREA.AREA_CODE
     *
     * @mbggenerated
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER_AREA.AREA_CODE
     *
     * @param areaCode the value for BJ_USER_AREA.AREA_CODE
     *
     * @mbggenerated
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER_AREA.REG_MAN
     *
     * @return the value of BJ_USER_AREA.REG_MAN
     *
     * @mbggenerated
     */
    public Long getRegMan() {
        return regMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER_AREA.REG_MAN
     *
     * @param regMan the value for BJ_USER_AREA.REG_MAN
     *
     * @mbggenerated
     */
    public void setRegMan(Long regMan) {
        this.regMan = regMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER_AREA.REG_TIME
     *
     * @return the value of BJ_USER_AREA.REG_TIME
     *
     * @mbggenerated
     */
    public Date getRegTime() {
        return regTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER_AREA.REG_TIME
     *
     * @param regTime the value for BJ_USER_AREA.REG_TIME
     *
     * @mbggenerated
     */
    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }
}