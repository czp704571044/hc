package cn.yfyue.sysauth.model;

import java.util.Date;

public class BjUserButton {
    @Override
	public String toString() {
		return "BjUserButton [userButtonId=" + userButtonId + ", buttonHtml="
				+ buttonHtml + ", buttonName=" + buttonName + ", funId="
				+ funId + ", buttonId=" + buttonId + ", userId=" + userId
				+ ", logTime=" + logTime + "]";
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER_BUTTON.USER_BUTTON_ID
     *
     * @mbggenerated
     */
    private Long userButtonId;
    private String buttonHtml;
    private String buttonName;
    public String getButtonHtml() {
		return buttonHtml;
	}

	public void setButtonHtml(String buttonHtml) {
		this.buttonHtml = buttonHtml;
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER_BUTTON.FUN_ID
     *
     * @mbggenerated
     */
    private String funId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER_BUTTON.BUTTON_ID
     *
     * @mbggenerated
     */
    private String buttonId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER_BUTTON.USER_ID
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER_BUTTON.LOG_TIME
     *
     * @mbggenerated
     */
    private Date logTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER_BUTTON.USER_BUTTON_ID
     *
     * @return the value of BJ_USER_BUTTON.USER_BUTTON_ID
     *
     * @mbggenerated
     */
    public Long getUserButtonId() {
        return userButtonId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER_BUTTON.USER_BUTTON_ID
     *
     * @param userButtonId the value for BJ_USER_BUTTON.USER_BUTTON_ID
     *
     * @mbggenerated
     */
    public void setUserButtonId(Long userButtonId) {
        this.userButtonId = userButtonId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER_BUTTON.FUN_ID
     *
     * @return the value of BJ_USER_BUTTON.FUN_ID
     *
     * @mbggenerated
     */
    public String getFunId() {
        return funId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER_BUTTON.FUN_ID
     *
     * @param funId the value for BJ_USER_BUTTON.FUN_ID
     *
     * @mbggenerated
     */
    public void setFunId(String funId) {
        this.funId = funId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER_BUTTON.BUTTON_ID
     *
     * @return the value of BJ_USER_BUTTON.BUTTON_ID
     *
     * @mbggenerated
     */
    public String getButtonId() {
        return buttonId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER_BUTTON.BUTTON_ID
     *
     * @param buttonId the value for BJ_USER_BUTTON.BUTTON_ID
     *
     * @mbggenerated
     */
    public void setButtonId(String buttonId) {
        this.buttonId = buttonId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER_BUTTON.USER_ID
     *
     * @return the value of BJ_USER_BUTTON.USER_ID
     *
     * @mbggenerated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER_BUTTON.USER_ID
     *
     * @param userId the value for BJ_USER_BUTTON.USER_ID
     *
     * @mbggenerated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER_BUTTON.LOG_TIME
     *
     * @return the value of BJ_USER_BUTTON.LOG_TIME
     *
     * @mbggenerated
     */
    public Date getLogTime() {
        return logTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER_BUTTON.LOG_TIME
     *
     * @param logTime the value for BJ_USER_BUTTON.LOG_TIME
     *
     * @mbggenerated
     */
    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }
}