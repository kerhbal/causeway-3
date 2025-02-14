package domainapp.modules.simple.dom.so;

import jakarta.inject.Named;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import jakarta.xml.bind.annotation.XmlTransient;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.ActionLayout;
import org.apache.causeway.applib.annotation.Collection;
import org.apache.causeway.applib.annotation.CollectionLayout;
import org.apache.causeway.applib.annotation.DomainObject;
import org.apache.causeway.applib.annotation.Nature;
import org.apache.causeway.applib.annotation.Property;

import org.apache.causeway.applib.annotation.SemanticsOf;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Named("simple.SimpleObjectViewModel")
@DomainObject(nature = Nature.VIEW_MODEL)
@XmlRootElement(name = "SimpleObjectViewModel")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SimpleObjectViewModel {

    @Property
    @Getter
    protected String name;

    public String title() {
        return "my view";
    }

    @XmlTransient
    @CollectionLayout(paged = 5)
    public List<SimpleObjectDto> getSimpleObjectList(){
        return simpleObjectRepository.findAll().stream().map(s -> new SimpleObjectDto(s.getName())).collect(Collectors.toList());
    }

    @Autowired
    @XmlTransient
    private SimpleObjectRepository simpleObjectRepository;

    public SimpleObjectViewModel(String name) {
        this.name = name;
    }

    @Action(
            semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE,
            choicesFrom = "simpleObjectList"
    )
    @ActionLayout(
            associateWith = "simpleObjectList"
    )
    public SimpleObjectViewModel testButton(List<SimpleObjectDto> dtos, String sss) {
        return this;
    }
}