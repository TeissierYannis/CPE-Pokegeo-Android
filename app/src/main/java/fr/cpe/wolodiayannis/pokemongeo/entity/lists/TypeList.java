package fr.cpe.wolodiayannis.pokemongeo.entity.lists;

import com.google.gson.annotations.JsonAdapter;

import java.io.Serializable;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists.TypeListAdapter;
import fr.cpe.wolodiayannis.pokemongeo.entity.Type;

@JsonAdapter(TypeListAdapter.class)
public class TypeList implements Serializable {
    /**
     * List of type
     */
    private List<Type> typeList;

    /**
     * Constructor.
     * @param typeList List of type.
     */
    public TypeList(List<Type> typeList) {
        this.typeList = typeList;
    }

    /**
     * Get the type list.
     * @return List of type.
     */
    public List<Type> getTypeList() {
        return typeList;
    }
}
