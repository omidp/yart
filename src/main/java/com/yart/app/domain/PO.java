package com.yart.app.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


/**
 *
 * @author Omid Pourhadi
 *
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class PO extends BasePO
{

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date", length = 29)
    @LastModifiedDate
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "updated_user")
    @LastModifiedBy
    private User updateUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", length = 29)
    @CreatedDate
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "created_user")
    @CreatedBy
    private User createUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "delete_date", length = 29)
    private Date deleteDate;

    public Date getUpdateDate()
    {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate)
    {
        this.updateDate = updateDate;
    }

    public Date getCreateDate()
    {
        return this.createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public User getUpdateUser()
    {
        return updateUser;
    }

    public void setUpdateUser(User updateUser)
    {
        this.updateUser = updateUser;
    }

    public User getCreateUser()
    {
        return createUser;
    }

    public void setCreateUser(User createUser)
    {
        this.createUser = createUser;
    }

    public Date getDeleteDate()
    {
        return this.deleteDate;
    }

    public void setDeleteDate(Date deleteDate)
    {
        this.deleteDate = deleteDate;
    }

}
