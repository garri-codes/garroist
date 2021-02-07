package codes.garri.garroist.domain.item;

import codes.garri.garroist.domain.label.Label;

import java.time.LocalDateTime;
import java.util.List;

public class Item {
    private String title;

    private String description;

    private LocalDateTime deadline;

    private List<Label> labels;

    private List<Item> subItems;
}
