package cn.yfyue.sysauth.model;

import java.util.Date;

public class BjButton {
    @Override
	public String toString() {
		return "BjButton [buttonId=" + buttonId + ", funId=" + funId
				+ ", buttonHtml=" + buttonHtml + ", buttonName=" + buttonName
				+ ", regMan=" + regMan + ", regTime=" + regTime + "]";
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_BUTTON.BUTTON_ID
     *
     * @mbggenerated
     */
    private Long buttonId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_BUTTON.FUN_ID
     *
     * @mbggenerated
     */
    private Long funId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_BUTTON.BUTTON_HTML
     *
     * @mbggenerated
     */
    private String buttonHtml;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_BUTTON.BUTTON_NAME
     *
     * @mbggenerated
     */
    private String buttonName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_BUTTON.REG_MAN
     *
     * @mbggenerated
     */
    private Long regMan;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_BUTTON.REG_TIME
     *
     * @mbggenerated
     */
    private Date regTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_BUTTON.BUTTON_ID
     *
     * @return the value of BJ_BUTTON.BUTTON_ID
     *
     * @mbggenerated
     */
    public Long getButtonId() {
        return buttonId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_BUTTON.BUTTON_ID
     *
     * @param buttonId the value for BJ_BUTTON.BUTTON_ID
     *
     * @mbggenerated
     */
    public void setButtonId(Long buttonId) {
        this.buttonId = buttonId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_BUTTON.FUN_ID
     *
     * @return the value of BJ_BUTTON.FUN_ID
     *
     * @mbggenerated
     */
    public Long getFunId() {
        return funId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_BUTTON.FUN_ID
     *
     * @param funId the value for BJ_BUTTON.FUN_ID
     *
     * @mbggenerated
     */
    public void setFunId(Long funId) {
        this.funId = funId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_BUTTON.BUTTON_HTML
     *
     * @return the value of BJ_BUTTON.BUTTON_HTML
     *
     * @mbggenerated
     */
    public String getButtonHtml() {
        return buttonHtml;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_BUTTON.BUTTON_HTML
     *
     * @param buttonHtml the value for BJ_BUTTON.BUTTON_HTML
     *
     * @mbggenerated
     */
    public void setButtonHtml(String buttonHtml) {
        this.buttonHtml = buttonHtml == null ? null : buttonHtml.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_BUTTON.BUTTON_NAME
     *
     * @return the value of BJ_BUTTON.BUTTON_NAME
     *
     * @mbggenerated
     */
    public String getButtonName() {
        return buttonName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_BUTTON.BUTTON_NAME
     *
     * @param buttonName the value for BJ_BUTTON.BUTTON_NAME
     *
     * @mbggenerated
     */
    public void setButtonName(String buttonName) {
        this.buttonName = buttonName == null ? null : buttonName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_BUTTON.REG_MAN
     *
     * @return the value of BJ_BUTTON.REG_MAN
     *
     * @mbggenerated
     */
    public Long getRegMan() {
        return regMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_BUTTON.REG_MAN
     *
     * @param regMan the value for BJ_BUTTON.REG_MAN
     *
     * @mbggenerated
     */
    public void setRegMan(Long regMan) {
        this.regMan = regMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_BUTTON.REG_TIME
     *
     * @return the value of BJ_BUTTON.REG_TIME
     *
     * @mbggenerated
     */
    public Date getRegTime() {
        return regTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_BUTTON.REG_TIME
     *
     * @param regTime the value for BJ_BUTTON.REG_TIME
     *
     * @mbggenerated
     */
    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }
}