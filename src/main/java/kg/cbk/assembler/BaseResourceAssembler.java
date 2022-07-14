package kg.cbk.assembler;

import java.util.List;
import java.util.stream.Collectors;

abstract public class BaseResourceAssembler<E, R> {

    public abstract R toResource(E entity);

    public List<R> toResources(List<E> entityList) {
        return entityList.stream().map(this::toResource).collect(Collectors.toList());
    }

}
