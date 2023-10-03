package com.example.test.handler;

import com.example.test.dto.RoleDto;
import com.example.test.dto.commen.PaginatedResultDto;
import com.example.test.entity.Role;
import com.example.test.exception.ResourceNotFoundException;
import com.example.test.mapper.PaginationMapper;
import com.example.test.mapper.RoleMapper;
import com.example.test.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;

@Component
@AllArgsConstructor
public class RoleHandler {
    private final RoleMapper roleMapper;
    private final RoleService roleService;
    private final PaginationMapper paginationMapper;

    public ResponseEntity<?> getAll(Integer page, Integer size) {
        Page<Role> rolePage = roleService.getAll(page, size);
        List<RoleDto> roleDtoList = roleMapper.toDto(rolePage.getContent());
        PaginatedResultDto<RoleDto> paginatedResult = new PaginatedResultDto<>();
        paginatedResult.setData(roleDtoList);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(rolePage));
        return ResponseEntity.ok(paginatedResult);
    }


    public ResponseEntity<?> save(RoleDto roleDto) {
        RoleDto roleDtoCreated = roleMapper.toDto(roleService.save(roleMapper.toEntity(roleDto)));
        return ResponseEntity.created(URI.create("/role/" + roleDto.getId())).body(roleDtoCreated);

    }

    public ResponseEntity<?> getById(Integer id) {
        Role role = roleService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class.getSimpleName(), id));
        return ResponseEntity.ok(roleMapper.toDto(role));
    }

    public ResponseEntity<?> delete(Integer id) {
        Role role = roleService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class.getSimpleName(), id));
        roleService.delete(role);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> update(Integer id, RoleDto roleDto) {
        Role role = roleService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class.getSimpleName(), id));
        Role entity = roleMapper.updateEntityFromDto(roleDto, role);
        roleService.update(entity);
        return ResponseEntity.ok().build();
    }


}
