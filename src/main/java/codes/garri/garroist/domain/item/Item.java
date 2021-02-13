package codes.garri.garroist.domain.item;

import codes.garri.garroist.domain.label.Label;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

public class Item implements Comparable<Item> {
    private final UUID id;

    private String title;

    private Boolean completed;

    private LocalDateTime completedAt;

    private final Set<Label> labels;

    private final Set<Item> subItems;

    private Item parentItem;

    private Integer order;

    public Item addNewSubItem(String title) {

        if (parentItem != null) {
            throw new IllegalCallerException("Cannot add a subItem for a Item");
        }

        Item subItem = new ItemBuilder()
                .id(UUID.randomUUID())
                .title(title)
                .parentItem(this)
                .order(subItems.size() + 1)
                .build();

        this.subItems.add(subItem);

        return subItem;
    }

    public void addSubItem(Item subItem) {

        if (parentItem != null) {
            throw new IllegalCallerException("Cannot add a subItem for a Item");
        }

        subItems.add(subItem);
    }

    public void moveSubItem(Item newParentItem) {

        if (parentItem == null) {
            throw  new IllegalCallerException("Item is not a subItem");
        }

        this.parentItem.getSubItems().remove(this);

        this.setOrder(newParentItem.getSubItems().size() + 1);
        this.setParentItem(newParentItem);

        newParentItem.addSubItem(this);
    }

    public void setTitle(String title) {
        this.title = Objects.requireNonNull(title);
    }

    public void addLabel(Label label) {
        this.labels.add(label);
    }

    public void setOrder(Integer order) {
        this.order = Objects.requireNonNull(order);
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;

        if(completed) {
            this.completedAt = LocalDateTime.now();
        } else {
            this.completedAt = null;
        }
    }

    public void setParentItem(Item parentItem) {
        this.parentItem = parentItem;
    }

    public Set<Item> getSubItems() {
        return this.subItems;
    }

    public Integer getOrder() {
        return this.order;
    }

    public Item getParentItem() {
        return this.parentItem;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Set<Label> getLabels() {
        return labels;
    }

    public Item(UUID id, String title, Boolean completed, LocalDateTime completedAt, Set<Label> labels, TreeSet<Item> subItems, Item parentItem, Integer order) {
        this.id = Objects.requireNonNullElse(id, UUID.randomUUID());
        this.title = Objects.requireNonNull(title);
        this.completed = Objects.requireNonNullElse(completed, false);
        this.completedAt = completedAt;
        this.labels = Objects.requireNonNullElse(labels, new HashSet<>());
        this.subItems = Objects.requireNonNullElse(subItems, new TreeSet<>());
        this.parentItem = parentItem;
        this.order = Objects.requireNonNull(order);
    }

    @Override
    public int compareTo(Item o) {
        return order - o.order;
    }
}
