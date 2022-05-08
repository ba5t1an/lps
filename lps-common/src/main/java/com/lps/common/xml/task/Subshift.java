/*************************************************************************
 *
 * CONFIDENTIAL
 * __________________
 *
 *  [2016] Bastian Schoettle & Tim Schoettle
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Bastian Schoettle & Tim Schoettle and his suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Bastian Schoettle & Tim Schoettle
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Bastian Schoettle & Tim Schoettle.
 *
 */
package com.lps.common.xml.task;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Duration;

import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author schobast
 */
public class Subshift implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1216572912757969927L;
    private int subshiftID;
    private String subshiftName;
    private int conditionID;
    private DateTime startTime;
    private DateTime endTime;

    @XmlTransient
    private Condition condition;
    @XmlTransient
    private double duration;

    @XmlTransient
    private double realHours;

    /**
     *
     */
    public Subshift() {
        // TODO Auto-generated constructor stub
    }

    /**
     *
     */
    public Subshift(Subshift source) {
        subshiftID = new Integer(source.subshiftID);
        subshiftName = new String(source.subshiftName);
        conditionID = new Integer(source.conditionID);
        startTime = new DateTime(source.getStartTime());
        endTime = new DateTime(source.getEndTime());
        realHours = new Double(source.realHours);
        condition = source.condition;
        duration = new Double(source.duration);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Subshift)) {
            return false;
        }

        Subshift sub = (Subshift) o;

        return sub.subshiftName.equals(subshiftName) && sub.subshiftID == subshiftID;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + subshiftName.hashCode();
        result = 31 * result + subshiftID;
        return result;
    }

    public int getSubshiftID() {
        return subshiftID;
    }

    public void setSubshiftID(int subshiftID) {
        this.subshiftID = subshiftID;
    }

    public String getSubshiftName() {
        return subshiftName;
    }

    public void setSubshiftName(String subshiftName) {
        this.subshiftName = subshiftName;
    }

    public int getConditionID() {
        return conditionID;
    }

    public void setConditionID(int conditionID) {
        this.conditionID = conditionID;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime.toDateTime();
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public double getDuration() {
        if (duration == 0) {
            setDuration();
        }
        return duration;
    }

    public void setDuration() {
        if (endTime.isBefore(startTime)) {
            endTime = endTime.plusDays(1);
        }
        System.out.println(startTime.toString() + " - " + endTime.toString());
        this.duration = BigDecimal.valueOf(new Duration(startTime, endTime).getMillis())
                .divide(BigDecimal.valueOf(DateTimeConstants.MILLIS_PER_HOUR))
                .doubleValue();
    }

    public double getRealHours() {
        return realHours;
    }

    public void setRealHours() {
        if (condition == null) {
            realHours = getDuration();
        } else {
            double realTime = 0;
            if (condition.getMultipleRequiredPercentage() > 0) {
                double percentage = 0;
                if (percentage < 1) {
                    percentage = condition.getMultipleRequiredPercentage();
                } else {
                    percentage = condition.getMultipleRequiredPercentage() / 100;
                }
                realTime = (getDuration() * (percentage)) * condition.getMultipleRequiredCount()
                        + (getDuration() * (1 - percentage));
            } else {
                realTime = (condition.getMultipleRequiredHours() * condition.getMultipleRequiredHours())
                        + (getDuration() - condition.getMultipleRequiredHours());
            }
            this.realHours = realTime;
        }
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

}
