package com.example.test.controller;

import com.example.test.dto.RoleDto;
import com.example.test.handler.RoleHandler;
import com.example.test.validation.InsertValidation;
import com.example.test.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Role")
@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController {
    private final RoleHandler roleHandler;

    @GetMapping
    @Operation(summary = "Get All  Role Paged")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResponseEntity.ok(roleHandler.getAll(page, size));
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated({InsertValidation.class})@RequestBody RoleDto roleDto) {
        return roleHandler.save(roleDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Role by id")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(roleHandler.getById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Role By Id ")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        roleHandler.delete(id);
        return ResponseEntity.ok().body(true);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Role")
    public ResponseEntity<?> update(@Validated(UpdateValidation.class) @PathVariable Integer id, @RequestBody RoleDto roleDto) {
        return roleHandler.update(id, roleDto);
    }

}
