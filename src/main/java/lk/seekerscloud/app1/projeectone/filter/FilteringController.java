package lk.seekerscloud.app1.projeectone.filter;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lk.seekerscloud.app1.projeectone.model.SomeBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean(){
        SomeBean someBean = new SomeBean("Value1", "Value2", "Value3");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");

        FilterProvider filters = new SimpleFilterProvider().addFilter(
                "SomeBeanFilter", filter
        );

        MappingJacksonValue mapping= new MappingJacksonValue(someBean);
        mapping.setFilters(filters);
        return mapping;
    }

    @GetMapping("/filtering-list")
    public List<SomeBean> retrieveListOfSomeBeans(){
        return Arrays.asList(
                new SomeBean("Value1", "Value2", "Value3"),
                new SomeBean("Value3", "Value44", "Value4"),
                new SomeBean("Value44", "Value44", "Value44")
        );
    }

}
