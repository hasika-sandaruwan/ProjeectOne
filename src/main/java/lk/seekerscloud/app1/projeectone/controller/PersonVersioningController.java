package lk.seekerscloud.app1.projeectone.controller;

import lk.seekerscloud.app1.projeectone.version.Name;
import lk.seekerscloud.app1.projeectone.version.PersonV1;
import lk.seekerscloud.app1.projeectone.version.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
    @GetMapping("/v1/person")
    public PersonV1 personV1(){
        return new PersonV1("Nimal");
    }
@GetMapping("/v2/person")
    public PersonV2 personV2(){
        return new PersonV2(new Name("Bob","Marly"));
    }
}
