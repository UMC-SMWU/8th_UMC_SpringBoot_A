package umc8.spring.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReviewImg is a Querydsl query type for ReviewImg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewImg extends EntityPathBase<ReviewImg> {

    private static final long serialVersionUID = 1543549985L;

    public static final QReviewImg reviewImg = new QReviewImg("reviewImg");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QReviewImg(String variable) {
        super(ReviewImg.class, forVariable(variable));
    }

    public QReviewImg(Path<? extends ReviewImg> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReviewImg(PathMetadata metadata) {
        super(ReviewImg.class, metadata);
    }

}

