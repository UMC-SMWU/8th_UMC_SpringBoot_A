package umc8.spring.web.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ReviewReplyDto(
   Long reviewId,
   String nickname,
   Float rate,
   String content,
   LocalDateTime createdAt,
   List<String> imageUrls,
   String ownerReply
) {}