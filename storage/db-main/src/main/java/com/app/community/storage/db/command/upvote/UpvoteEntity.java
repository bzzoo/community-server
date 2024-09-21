package com.app.community.storage.db.command.upvote;

import com.app.community.domain.agg.upvote.Upvote;
import com.app.community.domain.agg.upvote.UpvoteTarget;
import com.app.community.domain.agg.upvote.UpvoteTargetType;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "upvotes")
@Entity
public class UpvoteEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long executorId;
    private Long targetId;

    @Enumerated(EnumType.STRING)
    private UpvoteTargetType targetType;

    public Upvote toDomain() {
        UpvoteTarget upvoteTarget = new UpvoteTarget(targetId, targetType);
        return new Upvote(id, executorId, upvoteTarget);
    }
}
