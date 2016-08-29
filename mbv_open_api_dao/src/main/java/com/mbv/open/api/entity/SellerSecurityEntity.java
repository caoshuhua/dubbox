package com.mbv.open.api.entity;

import java.util.Date;

public class SellerSecurityEntity {
    private Integer id;

    private String ip;

    private String createUser;

    private Date createDate;

    private String lastModifiedUser;

    private Date lastModifiedDate;

    private String appKey;

    private String token;

    private String sellerCode;
    
    private String userCode;
    
    private Integer requestLimit;
    
    private Integer status;
    
    private Integer isLimit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(String lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser == null ? null : lastModifiedUser.trim();
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey == null ? null : appKey.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Integer getRequestLimit() {
		return requestLimit;
	}

	public Integer getStatus() {
		return status;
	}

	public Integer getIsLimit() {
		return isLimit;
	}

	public void setRequestLimit(Integer requestLimit) {
		this.requestLimit = requestLimit;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setIsLimit(Integer isLimit) {
		this.isLimit = isLimit;
	}

	@Override
	public String toString() {
		return "SellerSecurityEntity [id=" + id + ", ip=" + ip + ", appKey="
				+ appKey + ", token=" + token + ", sellerCode=" + sellerCode
				+ ", userCode=" + userCode + ", requestLimit=" + requestLimit
				+ ", status=" + status + ", isLimit=" + isLimit + "]";
	}
	
}