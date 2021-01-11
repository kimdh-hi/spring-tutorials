package com.kdh.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Size(min=8, max=15, message = "비밀번호는 8자 이상, 15자 이하 입니다.")
    @Column(nullable = false)
    private String password;

//    @Enumerated(EnumType.STRING)
//    private UserType userType;

    @Email
    private String email;

    @CreationTimestamp
    private Timestamp createTime;
}
