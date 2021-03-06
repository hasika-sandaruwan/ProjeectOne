package lk.seekerscloud.app1.projeectone.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Primary;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel(description = "All Details about the user")
public class User {
    private Integer id;
    @Size(min = 2, message = "Name Should have at Least one character")
    private String name;
    @Past
    @ApiModelProperty(notes = "Birth day should be before current time")
    private Date birthDate;

    public User(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
