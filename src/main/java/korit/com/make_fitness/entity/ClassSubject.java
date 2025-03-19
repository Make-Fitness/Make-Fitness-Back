package korit.com.make_fitness.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassSubject {
    private int classSubjectId;
    private String classSubjectName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
