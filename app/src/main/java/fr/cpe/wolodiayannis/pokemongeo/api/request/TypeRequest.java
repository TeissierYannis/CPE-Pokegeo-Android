package fr.cpe.wolodiayannis.pokemongeo.api.request;

import java.io.IOException;

import fr.cpe.wolodiayannis.pokemongeo.api.TypeAPI;
import fr.cpe.wolodiayannis.pokemongeo.entity.Type;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.TypeList;
import retrofit2.Call;

public class TypeRequest extends BaseRequest {

    /**
     * Get TypeAPI.
     *
     * @return TypeAPI.
     */
    protected static TypeAPI getAPI() {
        return getRetrofit().create(TypeAPI.class);
    }

    /**
     * Get all types.
     *
     * @return List of types.
     */
    public static TypeList getAllTypes() {
        Call<TypeList> call = getAPI().getTypes();
        try {
            TypeList typeList = call.execute().body();
            LogAPI("Types");
            return typeList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
