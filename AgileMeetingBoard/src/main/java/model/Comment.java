package model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.yaml.snakeyaml.comments.CommentType;

@Entity
@Table(name = "rb_comment")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Comment {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String comment;
    
    @Enumerated(EnumType.STRING)
    private CommentType type;
    
    @CreatedDate
    private Timestamp createdDate;
    
    @CreatedBy
    private String createdBy;

    public void setType(model.CommentType commentType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}