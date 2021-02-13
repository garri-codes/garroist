package codes.garri.garroist.domain.item;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemTest {

    @Test
    @DisplayName("Add a new subItem in Item model")
    public void addNewSubItem() {
        // given
        Item item = new ItemBuilder()
                .title("Finish to watch Naruto")
                .order(0)
                .build();

        // when
        Item subItem = item.addNewSubItem("Watch Naruto Shippuden");

        // then
        assertTrue(item.getSubItems().contains(subItem));
        assertEquals(item.getSubItems().size(), subItem.getOrder(), "assert that was added at final of list");
    }

    @Test
    @DisplayName("Move subItem to another Item")
    public void moveSubItem() {
        // given
        Item oldParent = new ItemBuilder()
                .title("Finish to watch Naruto")
                .order(0)
                .build();

        Item subItem = oldParent.addNewSubItem("Give a review");

        Item newParent = new ItemBuilder()
                .title("Finish to watch Boku no hero")
                .order(0)
                .build();

        // when
        subItem.moveSubItem(newParent);

        // then
        assertEquals(newParent, subItem.getParentItem());
        assertTrue(newParent.getSubItems().contains(subItem));
        assertFalse(oldParent.getSubItems().contains(subItem));
    }

    @Test
    @DisplayName("Throws InvalidSubItemException if Item is not a subItem")
    public void throwsIfIsNotSubItemWhenMoveSubItem() {
        // given
        Item item = new ItemBuilder()
                .title("Finish to watch Naruto")
                .order(0)
                .build();


        Item newParent = new ItemBuilder()
                .title("Finish to watch Boku no hero")
                .order(0)
                .build();

        // when
        Executable instance = () -> item.moveSubItem(newParent);

        // then
        assertThrows(IllegalCallerException.class, instance);
    }

    @Test
    @DisplayName("Throws IllegalCallerException if try add SubItem in another SubItem in addNewSubItem")
    public void throwsIfIsSubItemWhenAddNewSubItem() {
        // given
        Item item = new ItemBuilder()
                .title("Finish to watch Boku no hero")
                .order(0)
                .build();

        Item subItem = item.addNewSubItem("Give a review");

        // when
        Executable instance = () -> subItem.addNewSubItem("Give 5 stars");

        // then
        assertThrows(IllegalCallerException.class, instance);
    }


    @Test
    @DisplayName("Throws IllegalCallerException if try add SubItem in another SubItem in addSubItem")
    public void throwsIfIsSubItemWhenAddubItem() {
        // given
        Item item = new ItemBuilder()
                .title("Finish to watch Boku no hero")
                .order(0)
                .build();

        Item subItem = item.addNewSubItem("Give a review");

        Item anotherSubItem = item.addNewSubItem("Give 5 stars");

        // when
        Executable instance = () -> subItem.addSubItem(anotherSubItem);

        // then
        assertThrows(IllegalCallerException.class, instance);
    }

    @Test
    @DisplayName("Set completed as true and completedAt as LocalDateTime")
    public void setItemAsCompletedTrue() {
        // given
        Item item = new ItemBuilder()
                .title("Finish to watch Boku no hero")
                .order(0)
                .build();


        // when
        item.setCompleted(true);

        // then
        assertTrue(item.getCompleted());
        assertEquals(LocalDateTime.class, item.getCompletedAt().getClass());
    }

    @Test
    @DisplayName("Set completed as false and completedAt as null")
    public void setItemAsCompletedFalse() {
        // given
        Item item = new ItemBuilder()
                .title("Finish to watch Boku no hero")
                .completed(false)
                .order(0)
                .build();


        // when
        item.setCompleted(false);

        // then
        assertTrue(item.getCompleted());
        assertNull(item.getCompletedAt());
    }
}