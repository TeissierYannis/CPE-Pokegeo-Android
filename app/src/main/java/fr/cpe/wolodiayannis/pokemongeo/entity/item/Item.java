package fr.cpe.wolodiayannis.pokemongeo.entity.item;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

import fr.cpe.wolodiayannis.pokemongeo.Enum.BACKGROUND_COLOR;
import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.ItemAdapter;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;

/**
 * Item class.
 */
@JsonAdapter(ItemAdapter.class)
public class Item implements Serializable {
    /**
     * Item id.
     */
    @SerializedName("id")
    private final int id;
    /**
     * Item name.
     */
    @SerializedName("name")
    private final String name;

    /**
     * Item price.
     */
    @SerializedName("price")
    private final int price;

    /**
     * Item Image.
     */
    private int imageID;


    /**
     * Item constructor.
     * @param id Item id.
     * @param name Item name.
     */
    public Item(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    /**
     * Item constructor.
     * !! just for init purpose !!
     * @param id Item id.
     */
    public Item(int id) {
        this.id = id;
        this.name = "todo";
        this.price = 0;
    }

    /**
     * Get item id.
     * @return Item id.
     */
    public int getId() {
        return id;
    }

    /**
     * Get item name.
     * @return Item name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get item price.
     * @return Item price.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Get image id.
     *
     * @return int image ID.
     */
    public int getImageID() {
        return this.imageID;
    }

    /**
     * Set image ID.
     *
     * @param drawable int image ID.
     */
    public void setImageID(int drawable) {
        this.imageID = drawable;
    }

    /**
     * Check if two items are equals.
     * @param o Item to compare.
     * @return True if equals, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return getId() == item.getId() && getName().equals(item.getName()) && getPrice() == item.getPrice();
    }

    /**
     * Hash the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice());
    }

    /**
     * Get the item background color.
     * @return int color.
     */
    public int getBackgroundColor() {
        if (this instanceof ItemPotion) {
            return R.color.itemPotion;
        } else if (this instanceof ItemBall) {
            return R.color.itemBall;
        } else if (this instanceof ItemRevive) {
            return R.color.itemRevive;
        } else {
            return R.color.Unknown;
        }
    }
}

