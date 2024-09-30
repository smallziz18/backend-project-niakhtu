package com.niakhtu.backend.niakhtu.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class CommentaireId implements Serializable {
    private static final long serialVersionUID = -2783509032739911565L;
    @NotNull
    @Column(name = "COMMENT_ID", nullable = false)
    private Long commentId;

    @NotNull
    @Column(name = "COMPLAINTE_ID", nullable = false)
    private Long complainteId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CommentaireId entity = (CommentaireId) o;
        return Objects.equals(this.commentId, entity.commentId) &&
                Objects.equals(this.complainteId, entity.complainteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, complainteId);
    }

}