package org.sid.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;

/*@Entity*/
/*@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString*/

public class AppUser {

    /*    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    private Long id;
    //    @Column(unique = true)
    private String userName;
    private String passWord;
    private boolean actived;
    //    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRole> roles = new ArrayList<>();

    public Long getId() {
        return id;
    }


    public String getUserName() {
        return userName;
    }
    @JsonIgnore
    public String getPassWord() {
        return passWord;
    }

    public boolean isActived() {
        return actived;
    }

    public Collection<AppRole> getRoles() {
        return roles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @JsonProperty
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


    public void setActived(boolean actived) {
        this.actived = actived;
    }

    public void setRoles(Collection<AppRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", actived=" + actived +
                ", roles=" + roles +
                '}';
    }
}



