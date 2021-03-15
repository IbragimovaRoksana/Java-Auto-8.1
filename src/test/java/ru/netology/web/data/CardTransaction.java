package ru.netology.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardTransaction {
    private String id;
    private String source;
    private String target;
    private int amountInKopecks;
    private String timestamp;
}
