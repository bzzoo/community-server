package com.app.community.storage.upvote;


import com.app.community.domain.upvote.Upvote;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long executorId;
    private Long targetId;
    private Upvote.TargetType targetType;

    public static UpvoteEntity fromDomain(Upvote upvote) {
        return UpvoteEntity.builder()
                .executorId(upvote.getExecutorId())
                .targetId(upvote.getTargetId())
                .targetType(upvote.getTargetType())
                .build();
    }

    public Upvote toDomain() {
        return Upvote.builder()
                .id(id)
                .executorId(executorId)
                .targetId(targetId)
                .targetType(targetType)
                .build();
    }
}
