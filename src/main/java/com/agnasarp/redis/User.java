package com.agnasarp.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 7156526077883281623L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private long followers;

    public User(String name, Long followers) {
        this.name=name;
        this.followers=followers;
    }

    @Override
    public String toString() {
        return String.format("User{id=%d, name='%s', followers=%d}", id, name, followers);
    }
}
