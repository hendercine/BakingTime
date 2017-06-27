/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 8:57 AM
 */

package com.hendercine.android.bakinbuns.data.db.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

@Entity(nameInDb = "step_instructions")
public class RecipeStepDetail {

    @Expose
    @SerializedName("id")
    @Id(autoincrement = true)
    private Long id;

    @Expose
    @SerializedName("step_id")
    @Property(nameInDb = "step_id")
    private Long stepId;

}
