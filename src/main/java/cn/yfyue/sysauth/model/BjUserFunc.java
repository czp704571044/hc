package cn.yfyue.sysauth.model;

import java.util.Date;

public class BjUserFunc {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER_FUNC.USER_FUN_ID
     *
     * @mbggenerated
     */
    private Long userFunId;
    private String funcName;
    private String funcId;
    public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

	private String funcUrl;
    private String funcState;
    private String supperFuncId;
    public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getFuncUrl() {
		return funcUrl;
	}

	public void setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl;
	}

	public String getFuncState() {
		return funcState;
	}

	public void setFuncState(String funcState) {
		this.funcState = funcState;
	}

	public String getSupperFuncId() {
		return supperFuncId;
	}

	public void setSupperFuncId(String supperFuncId) {
		this.supperFuncId = supperFuncId;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getFuncAlias() {
		return funcAlias;
	}

	public void setFuncAlias(String funcAlias) {
		this.funcAlias = funcAlias;
	}

	public String getFuncLevel() {
		return funcLevel;
	}

	public void setFuncLevel(String funcLevel) {
		this.funcLevel = funcLevel;
	}

	public String getStyleImg() {
		return styleImg;
	}

	public void setStyleImg(String styleImg) {
		this.styleImg = styleImg;
	}

	private String isLeaf;
    private String funcAlias;
    private String funcLevel;
    private String styleImg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER_FUNC.USER_ID
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER_FUNC.FUN_ID
     *
     * @mbggenerated
     */
    private String funId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER_FUNC.REG_MAN
     *
     * @mbggenerated
     */
    private Long regMan;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BJ_USER_FUNC.REG_TIME
     *
     * @mbggenerated
     */
    private Date regTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER_FUNC.USER_FUN_ID
     *
     * @return the value of BJ_USER_FUNC.USER_FUN_ID
     *
     * @mbggenerated
     */
    public Long getUserFunId() {
        return userFunId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER_FUNC.USER_FUN_ID
     *
     * @param userFunId the value for BJ_USER_FUNC.USER_FUN_ID
     *
     * @mbggenerated
     */
    public void setUserFunId(Long userFunId) {
        this.userFunId = userFunId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER_FUNC.USER_ID
     *
     * @return the value of BJ_USER_FUNC.USER_ID
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER_FUNC.USER_ID
     *
     * @param userId the value for BJ_USER_FUNC.USER_ID
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER_FUNC.FUN_ID
     *
     * @return the value of BJ_USER_FUNC.FUN_ID
     *
     * @mbggenerated
     */
    public String getFunId() {
        return funId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER_FUNC.FUN_ID
     *
     * @param funId the value for BJ_USER_FUNC.FUN_ID
     *
     * @mbggenerated
     */
    public void setFunId(String funId) {
        this.funId = funId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER_FUNC.REG_MAN
     *
     * @return the value of BJ_USER_FUNC.REG_MAN
     *
     * @mbggenerated
     */
    public Long getRegMan() {
        return regMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER_FUNC.REG_MAN
     *
     * @param regMan the value for BJ_USER_FUNC.REG_MAN
     *
     * @mbggenerated
     */
    public void setRegMan(Long regMan) {
        this.regMan = regMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BJ_USER_FUNC.REG_TIME
     *
     * @return the value of BJ_USER_FUNC.REG_TIME
     *
     * @mbggenerated
     */
    public Date getRegTime() {
        return regTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BJ_USER_FUNC.REG_TIME
     *
     * @param regTime the value for BJ_USER_FUNC.REG_TIME
     *
     * @mbggenerated
     */
    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }
}