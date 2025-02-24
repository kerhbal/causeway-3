package domainapp.modules.simple.dom.so;


import domainapp.modules.simple.utils.LocalDateTimeAdapter;
import jakarta.inject.Named;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.causeway.applib.ViewModel;
import org.apache.causeway.applib.annotation.DomainObject;
import org.apache.causeway.applib.annotation.Nature;
import org.apache.causeway.applib.annotation.Property;
import org.apache.causeway.applib.annotation.Title;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Named("simple.SimpleObjectDto")
@DomainObject(nature = Nature.VIEW_MODEL)
public class SimpleObjectDto implements ViewModel {
    @Getter @Setter
    @Title
    @Property
    private String name;

    @Getter @Setter
    @XmlJavaTypeAdapter(type= LocalDateTime.class,
            value= LocalDateTimeAdapter.class)
    @Property
    private LocalDateTime testTime;

    public SimpleObjectDto(String name, LocalDateTime testTime) {
        this.name = name;
        this.testTime = testTime;
    }

    @Override
    public String viewModelMemento() {
        return name + testTime.toString();
    }
}