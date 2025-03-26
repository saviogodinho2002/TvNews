package godinho.savio.TvNews.controllers;

import godinho.savio.TvNews.Models.Role;
import godinho.savio.TvNews.dtos.RoleRecordDto;
import godinho.savio.TvNews.services.RoleService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/roles")
    public ResponseEntity<Object> save(@NotNull @RequestBody RoleRecordDto roleRecordDto) {
        Role currentRole = roleService.getByName(roleRecordDto.name());
        if(currentRole != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe uma role com este nome.");

        }
        Role newRole = new Role();
        newRole.setName(roleRecordDto.name());
        newRole = roleService.insert(newRole);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRole);
    }
    @GetMapping("/roles")
    public ResponseEntity<Object> findAll() {
        List<Role> roles = roleService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(roles);
    }

}
