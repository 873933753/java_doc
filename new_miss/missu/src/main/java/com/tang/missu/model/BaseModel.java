package com.tang.missu.model;

import javax.persistence.Column;
import java.time.Instant;

public abstract class BaseModel {
    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "update_time")
    private Instant updateTime;

    @Column(name = "delete_time")
    private Instant deleteTime;
}
