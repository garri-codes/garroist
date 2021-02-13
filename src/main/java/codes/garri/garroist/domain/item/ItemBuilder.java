package codes.garri.garroist.domain.item;

import codes.garri.garroist.domain.label.Label;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

public class ItemBuilder {
    private UUID id;
    private String title;
    private Boolean completed;
    private LocalDateTime completedAt;
    private Set<Label> labels;
    private TreeSet<Item> subItems;
    private Item parentItem;
    private Integer order;

    public ItemBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public ItemBuilder title(String title) {
        this.title = title;
        return this;
    }

    public ItemBuilder completed(Boolean completed) {
        this.completed = completed;
        return this;
    }

    public ItemBuilder completedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
        return this;
    }

    public ItemBuilder labels(Set<Label> labels) {
        this.labels = labels;
        return this;
    }

    public ItemBuilder subItems(TreeSet<Item> subItems) {
        this.subItems = subItems;
        return this;
    }

    public ItemBuilder parentItem(Item parentItem) {
        this.parentItem = parentItem;
        return this;
    }

    public ItemBuilder order(Integer order) {
        this.order = order;
        return this;
    }

    public Item build() {
        return new Item(id, title, completed, completedAt, labels, subItems, parentItem, order);
    }
}