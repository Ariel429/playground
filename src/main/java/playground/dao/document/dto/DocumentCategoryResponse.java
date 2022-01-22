package playground.dao.document.dto;

import lombok.Getter;
import playground.domain.document.Category;

@Getter
public class DocumentCategoryResponse {
    private final String value;
    private final String text;

    public DocumentCategoryResponse(Category category) {
        this.value = category.name();
        this.text = category.getText();
    }
}
