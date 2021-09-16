package com.airtnt.common.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "amentity_categories")
public class AmentityCategory extends IdAndName {

    @Builder
    public AmentityCategory(int id, String name) {
        super(id,name);
    }

    public AmentityCategory(int id)  {
        super(id);
    }


}
