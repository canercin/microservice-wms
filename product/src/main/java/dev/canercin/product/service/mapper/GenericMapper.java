package dev.canercin.product.service.mapper;

import java.util.List;

/**
 * @param <E> The entity type
 * @param <D> The DTO/Data type
 */
public interface GenericMapper<E, D> {
    /**
     * @param source the source object (as DTO) to be converted
     * @return the converted entity object
     * This method converts a DTO object to its corresponding entity object.
     */
    E toEntity(D source);


    /**
     * @param source the source object (as entity) to be converted
     * @return the converted DTO object
     * This method converts an entity object to its corresponding DTO object.
     */
    D toDto(E source);

    /**
     * @param sourceList the source list of objects (as DTO) to be converted
     * @return the converted list of entity objects
     * This method converts a list of DTO objects to a list of their corresponding entity objects.
     */
    default List<E> toEntityList(List<D> sourceList) {
        return sourceList.stream().map(this::toEntity).toList();
    }

    /**
     * @param sourceList the source list of objects (as entity) to be converted
     * @return the converted list of DTO objects
     * This method converts a list of entity objects to a list of their corresponding DTO objects.
     */
    default List<D> toDtoList(List<E> sourceList) {
        return sourceList.stream().map(this::toDto).toList();
    }
}
