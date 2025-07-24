package com.cityfashionpos.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "permissions")
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Role this permission belongs to
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    // Menu this permission refers to
    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private MenuEntity menu;

    @Column(name = "can_view", nullable = false)
    private boolean canView = true;

    @Column(name = "can_edit", nullable = false)
    private boolean canEdit = false;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    // Constructors
    public PermissionEntity() {}

    public PermissionEntity(RoleEntity role, MenuEntity menu, boolean canView, boolean canEdit) {
        this.role = role;
        this.menu = menu;
        this.canView = canView;
        this.canEdit = canEdit;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public RoleEntity getRole() { return role; }

    public void setRole(RoleEntity role) { this.role = role; }

    public MenuEntity getMenu() { return menu; }

    public void setMenu(MenuEntity menu) { this.menu = menu; }

    public boolean isCanView() { return canView; }

    public void setCanView(boolean canView) { this.canView = canView; }

    public boolean isCanEdit() { return canEdit; }

    public void setCanEdit(boolean canEdit) { this.canEdit = canEdit; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PermissionEntity)) return false;
        PermissionEntity that = (PermissionEntity) o;
        return Objects.equals(role, that.role) &&
               Objects.equals(menu, that.menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, menu);
    }
}
