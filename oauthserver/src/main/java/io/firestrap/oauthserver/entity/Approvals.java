package io.firestrap.oauthserver.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="OAUTH_APPROVALS")
public class Approvals {
	
	@Id
	@Column(name="USERID")
	private String userId;
	
	@Column(name="CLIENTID")
	private String clientId;
	
	@Column(name="SCOPE")
	private String scope;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="EXPIRESAT")
	private LocalDateTime expireAt;
	
	@Column(name="LASTMODIFIEDAT")
	private LocalDateTime lastModifiedDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getExpireAt() {
		return expireAt;
	}

	public void setExpireAt(LocalDateTime expireAt) {
		this.expireAt = expireAt;
	}

	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@Override
	public String toString() {
		return "Approvals [userId=" + userId + ", clientId=" + clientId + ", scope=" + scope + ", status=" + status
				+ ", expireAt=" + expireAt + ", lastModifiedDate=" + lastModifiedDate + "]";
	}
	
	
	
	
	
}
