package com.yart.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.yart.app.domain.enums.DatabaseType;
import com.yart.app.domain.enums.DatasourceType;

@Entity
@Table(name = "b_datasource")
public class DatasourceEntity extends PO
{

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_active")
    @Type(type = "yes_no")
    private boolean active;

    @Column(name = "ds_type")
    @Enumerated(EnumType.STRING)
    private DatasourceType dsType;

    @Column(name = "db_type")
    @Enumerated(EnumType.STRING)
    private DatabaseType dbType;

    @Column(name = "jdbc_driver")
    private String jdbcDriver;

    @Column(name = "db_url")
    private String url;

    @Column(name = "user_name")
    private String username;

    @Column(name = "passwd")
    private String passwd;

    @Column(name = "sql_test")
    private String sqlTest;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public DatasourceType getDsType()
    {
        return dsType;
    }

    public void setDsType(DatasourceType dsType)
    {
        this.dsType = dsType;
    }

    public DatabaseType getDbType()
    {
        return dbType;
    }

    public void setDbType(DatabaseType dbType)
    {
        this.dbType = dbType;
    }

    public String getJdbcDriver()
    {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver)
    {
        this.jdbcDriver = jdbcDriver;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPasswd()
    {
        return passwd;
    }

    public void setPasswd(String passwd)
    {
        this.passwd = passwd;
    }

    public String getSqlTest()
    {
        return sqlTest;
    }

    public void setSqlTest(String sqlTest)
    {
        this.sqlTest = sqlTest;
    }

}
