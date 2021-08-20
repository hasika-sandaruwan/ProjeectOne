package lk.seekerscloud.app1.projeectone.controller;

import lk.seekerscloud.app1.projeectone.exceptions.UserNotFoundException;
import lk.seekerscloud.app1.projeectone.model.User;
import lk.seekerscloud.app1.projeectone.services.UserDaoService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {


    private UserDaoService userDaoService;

    public UserResource(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    /*    @GetMapping("/user/{id}")
        public User retrieveUser(@PathVariable int id) {
            User user = userDaoService.findOne(id);
            if (user == null) {
                throw new UserNotFoundException("id-" + id);
            }

            Resource<User> resource = new Resource<User>(user);
            ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
            resource.add(linkTo.withRel("all-users"));
            return resource;
        }*/
    @GetMapping("/user/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = userDaoService.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        EntityModel<User> resource = new EntityModel<User>(user);
        Link linkTo = WebMvcLinkBuilder.linkTo(this.getClass())
                .slash(retrieveAllUsers()).withSelfRel();
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = userDaoService.deleteById(id);
        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User saveUser = userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


}
