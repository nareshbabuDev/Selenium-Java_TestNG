package com.medecision.utils;

import java.math.BigInteger;
import java.sql.Blob;
import java.util.Date;

public class NotificationAudit {

	public BigInteger notification_audit_id;
	public String patient_id;
	public BigInteger notification_subscription_id;
	public String disposition;
	public String endpoint;
	public String outgoing_msg;
	public Date CREATION_DATE_TIME;
	public Blob response;
	public Date last_update_date_time;
	public BigInteger COMMUNITY_ID;
	
	public BigInteger getNotification_audit_id() {
		return notification_audit_id;
	}
	public void setNotification_audit_id(BigInteger notification_audit_id) {
		this.notification_audit_id = notification_audit_id;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public BigInteger getNotification_subscription_id() {
		return notification_subscription_id;
	}
	public void setNotification_subscription_id(BigInteger notification_subscription_id) {
		this.notification_subscription_id = notification_subscription_id;
	}
	public String getDisposition() {
		return disposition;
	}
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public String getOutgoing_msg() {
		return outgoing_msg;
	}
	public void setOutgoing_msg(String outgoing_msg) {
		this.outgoing_msg = outgoing_msg;
	}
	public Date getCREATION_DATE_TIME() {
		return CREATION_DATE_TIME;
	}
	public void setCREATION_DATE_TIME(Date cREATION_DATE_TIME) {
		CREATION_DATE_TIME = cREATION_DATE_TIME;
	}
	public Blob getResponse() {
		return response;
	}
	public void setResponse(Blob response) {
		this.response = response;
	}
	public Date getLast_update_date_time() {
		return last_update_date_time;
	}
	public void setLast_update_date_time(Date last_update_date_time) {
		this.last_update_date_time = last_update_date_time;
	}
	public BigInteger getCOMMUNITY_ID() {
		return COMMUNITY_ID;
	}
	public void setCOMMUNITY_ID(BigInteger cOMMUNITY_ID) {
		COMMUNITY_ID = cOMMUNITY_ID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((COMMUNITY_ID == null) ? 0 : COMMUNITY_ID.hashCode());
		result = prime * result + ((CREATION_DATE_TIME == null) ? 0 : CREATION_DATE_TIME.hashCode());
		result = prime * result + ((disposition == null) ? 0 : disposition.hashCode());
		result = prime * result + ((endpoint == null) ? 0 : endpoint.hashCode());
		result = prime * result + ((last_update_date_time == null) ? 0 : last_update_date_time.hashCode());
		result = prime * result + ((notification_audit_id == null) ? 0 : notification_audit_id.hashCode());
		result = prime * result + ((notification_subscription_id == null) ? 0 : notification_subscription_id.hashCode());
		result = prime * result + ((outgoing_msg == null) ? 0 : outgoing_msg.hashCode());
		result = prime * result + ((patient_id == null) ? 0 : patient_id.hashCode());
		result = prime * result + ((response == null) ? 0 : response.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotificationAudit other = (NotificationAudit) obj;
		if (COMMUNITY_ID == null) {
			if (other.COMMUNITY_ID != null)
				return false;
		} else if (!COMMUNITY_ID.equals(other.COMMUNITY_ID))
			return false;
		if (CREATION_DATE_TIME == null) {
			if (other.CREATION_DATE_TIME != null)
				return false;
		} else if (!CREATION_DATE_TIME.equals(other.CREATION_DATE_TIME))
			return false;
		if (disposition == null) {
			if (other.disposition != null)
				return false;
		} else if (!disposition.equals(other.disposition))
			return false;
		if (endpoint == null) {
			if (other.endpoint != null)
				return false;
		} else if (!endpoint.equals(other.endpoint))
			return false;
		if (last_update_date_time == null) {
			if (other.last_update_date_time != null)
				return false;
		} else if (!last_update_date_time.equals(other.last_update_date_time))
			return false;
		if (notification_audit_id == null) {
			if (other.notification_audit_id != null)
				return false;
		} else if (!notification_audit_id.equals(other.notification_audit_id))
			return false;
		if (notification_subscription_id == null) {
			if (other.notification_subscription_id != null)
				return false;
		} else if (!notification_subscription_id.equals(other.notification_subscription_id))
			return false;
		if (outgoing_msg == null) {
			if (other.outgoing_msg != null)
				return false;
		} else if (!outgoing_msg.equals(other.outgoing_msg))
			return false;
		if (patient_id == null) {
			if (other.patient_id != null)
				return false;
		} else if (!patient_id.equals(other.patient_id))
			return false;
		if (response == null) {
			if (other.response != null)
				return false;
		} else if (!response.equals(other.response))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "NotificationAudit [notification_audit_id=" + notification_audit_id + ", patient_id=" + patient_id
				+ ", notification_subscription_id=" + notification_subscription_id + ", disposition=" + disposition
				+ ", endpoint=" + endpoint + ", outgoing_msg=" + outgoing_msg + ", CREATION_DATE_TIME="
				+ CREATION_DATE_TIME + ", response=" + response + ", last_update_date_time=" + last_update_date_time
				+ ", COMMUNITY_ID=" + COMMUNITY_ID + "]";
	}
	
	
	
}
