package com.sefa.streamreader.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "browser_history")
public class HistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", length = 50)
    private String userId;

    @Column(name = "product_id", length = 50)
    private String productId;

    @Column(name = "context_source")
    private String contextSource;

    @Column(name = "viewed_at")
    private LocalDateTime viewedAt;
}
